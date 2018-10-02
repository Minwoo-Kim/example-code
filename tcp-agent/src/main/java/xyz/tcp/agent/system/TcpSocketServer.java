package xyz.tcp.agent.system;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import xyz.elidom.util.ExceptionUtil;
import xyz.elidom.util.ThreadUtil;
import xyz.elidom.util.ValueUtil;
import xyz.tcp.agent.constants.ConfigConstants;
import xyz.tcp.agent.util.TcpUtil;

@Service
public class TcpSocketServer extends IoHandlerAdapter {

	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment env;

	@Autowired
	ITcpServerMessageHandler tcpServiceManager;

	private Map<Integer, Map<Long, IoSession>> PORT_SESSION_MAP = new ConcurrentHashMap<Integer, Map<Long, IoSession>>();

	private IoAcceptor acceptor;
	private Integer prefixSize;

	/**
	 * TCP Server Start
	 * 
	 * @param serviceInfo
	 * @param port
	 */
	public boolean start(int port) {
		this.stop(port);

		int retryCount = ValueUtil.toInteger(env.getProperty(ConfigConstants.TCP_SERVER_START_RETRY_COUNT), 3);

		for (int i = 0; i < retryCount; i++) {
			try {
				PORT_SESSION_MAP.put(port, new HashMap<Long, IoSession>());
				this.getIoAcceptor().bind(new InetSocketAddress(port));
				logger.info("STARTED [Port : " + port + "]");
				return true;
			} catch (IOException e) {
				if ((i + 1) == retryCount) {
					logger.error(e.getMessage());
				}

				ThreadUtil.sleep(20000);
			}
		}

		return false;
	}

	/**
	 * TCP Server Stop
	 * 
	 * @param port
	 */
	public void stop(int port) {
		// Port Unbind
		SocketAddress bindSocketAddress = this.getBindSocketAddress(port);
		if (bindSocketAddress != null) {
			this.getIoAcceptor().unbind(bindSocketAddress);
			logger.info("STOPED [Port : " + port + "]");
		}

		// Session Close
		List<IoSession> sessions = this.listClientSession(port);
		sessions.forEach(session -> session.closeNow());
	}

	/**
	 * 모든 서버 종료
	 */
	public void stopAll() {
		Set<Integer> ports = this.getServerPorts();
		for (Integer port : ports) {
			this.stop(port);
		}
	}

	/**
	 * 접속 중인 모든 Client로 데이터 전송.
	 * 
	 * @param port
	 * @param data
	 */
	public void publish(int port, String data) {
		if (data == null || data.isEmpty())
			return;

		List<IoSession> sessions = this.listClientSession(port);
		sessions.forEach(session -> TcpUtil.send(session, data.getBytes()));
	}

	/**
	 * Server Port 가져오기 실행.
	 * 
	 * @return
	 */
	public Set<Integer> getServerPorts() {
		return PORT_SESSION_MAP.keySet();
	}

	/**
	 * Server 별 접속 Client 정보 가져오기 실행.
	 * 
	 * @return
	 */
	public Map<Integer, List<String>> getClientList() {
		Map<Integer, List<String>> clientListMap = new HashMap<Integer, List<String>>();

		Set<Integer> ports = this.getServerPorts();
		for (Integer port : ports) {
			List<String> list = this.getClientList(port);
			if (ValueUtil.isNotEmpty(list)) {
				clientListMap.put(port, list);
			}
		}

		return clientListMap;
	}

	/**
	 * Server에 해당하는 Client 정보 가져오기 실행.
	 * 
	 * @param port
	 * @return
	 */
	public List<String> getClientList(int port) {
		List<String> list = new ArrayList<String>();

		Map<Long, IoSession> map = PORT_SESSION_MAP.get(port);
		map.forEach((id, session) -> list.add(this.getRemoteAddress(session)));

		return list;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		Map<Long, IoSession> ioSessionMap = this.getSessionMap(session);
		if (ioSessionMap != null) {
			ioSessionMap.put(session.getId(), session);
		}
		logger.info("CREATED[{}]: {}", this.getServerAddress(session), this.getRemoteAddress(session));
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("OPENED[{}] : {}", this.getServerAddress(session), this.getRemoteAddress(session));
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		Map<Long, IoSession> ioSessionMap = this.getSessionMap(session);
		if (ioSessionMap != null) {
			ioSessionMap.remove(session.getId());
		}

		logger.info("CLOSED[{}] : {}", this.getServerAddress(session), this.getRemoteAddress(session));
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		String trace = ExceptionUtil.getAllErrorStackTraceToString(cause);
		logger.info("EXCEPTION[{}] : {} \nDetail : \n {}", this.getServerAddress(session), cause.getMessage(), trace);
		TcpUtil.closeSession(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		List<String> valueList = TcpUtil.parseReceivedMessage(message, this.getPrefixSize());
		String serverAddress = this.getServerAddress(session);

		valueList.forEach(value -> {
			logger.info("RECEIVE[{} <- {}]: {}", serverAddress, this.getRemoteAddress(session), value);
			ThreadUtil.doAsynch(() -> tcpServiceManager.receive(serverAddress, session, value));
		});
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		String value = TcpUtil.parseReceivedMessage(message);
		logger.info("SENT[{} -> {}] : {}", this.getServerAddress(session), this.getRemoteAddress(session), value);
	}

	/**
	 * Server에 접속되어 있는, Client Session 객체 가져오기 실행.
	 * 
	 * @param port
	 * @return
	 */
	private List<IoSession> listClientSession(int port) {
		List<IoSession> sessionList = new ArrayList<IoSession>();

		Map<Long, IoSession> ioSessionMap = PORT_SESSION_MAP.get(port);
		if (ValueUtil.isNotEmpty(ioSessionMap)) {
			ioSessionMap.forEach((sessionId, session) -> sessionList.add(session));
		}

		return sessionList;
	}

	/**
	 * IoAcceptor 가져오기 실행.
	 * 
	 * @return
	 */
	private IoAcceptor getIoAcceptor() {
		if (this.acceptor == null) {
			int bufferSize = ValueUtil.toInteger(env.getProperty(ConfigConstants.TCP_SERVER_READ_BUFFER_SIZE), 2048);
			int idleTime = ValueUtil.toInteger(env.getProperty(ConfigConstants.TCP_SERVER_IDLE_TIME), 10);

			acceptor = new NioSocketAcceptor();
			// acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			acceptor.setHandler(this);
			acceptor.getSessionConfig().setReadBufferSize(bufferSize);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, idleTime);
		}
		return this.acceptor;
	}

	/**
	 * Port별 Session 정보 가져오기 실행.
	 * 
	 * @param session
	 * @return
	 */
	private Map<Long, IoSession> getSessionMap(IoSession session) {
		InetSocketAddress inetSocketAddress = (InetSocketAddress) session.getLocalAddress();
		int port = inetSocketAddress.getPort();
		return PORT_SESSION_MAP.get(port);
	}

	/**
	 * Port에 Binding된 Socket Address 정보 가져오기 실행.
	 * 
	 * @param port
	 * @return
	 */
	private SocketAddress getBindSocketAddress(int port) {
		Set<SocketAddress> addressList = this.getIoAcceptor().getLocalAddresses();

		for (SocketAddress address : addressList) {
			InetSocketAddress bindAddress = (InetSocketAddress) address;
			int bindPort = bindAddress.getPort();

			if (ValueUtil.isEqual(bindPort, port)) {
				return address;
			}
		}
		return null;
	}

	/**
	 * Session이 접속하고 있는 Server의 접속 주소 가져오기.
	 * 
	 * @param session
	 * @return
	 */
	private String getServerAddress(IoSession session) {
		return this.getAddress(session, true);
	}

	/**
	 * Session의 Remote 접속 주소 가져오기 실행.
	 * 
	 * @param session
	 * @return
	 */
	private String getRemoteAddress(IoSession session) {
		return this.getAddress(session, false);
	}

	private String getAddress(IoSession session, boolean isServer) {
		if (ValueUtil.isEmpty(session))
			return null;

		String addressInfo = isServer ? session.getLocalAddress().toString() : session.getRemoteAddress().toString();
		String address = addressInfo.substring(addressInfo.indexOf("/") + 1);
		return address;
	}

	/**
	 * Message의 Prefix 설정 사이즈 가져오기 실행.
	 * 
	 * @return
	 */
	private Integer getPrefixSize() {
		if (this.prefixSize == null) {
			this.prefixSize = ValueUtil.toInteger(env.getProperty(ConfigConstants.TCP_MESSAGE_PREFIX_SIZE));
		}
		return this.prefixSize;
	}
}