package xyz.tcp.agent.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import xyz.elidom.exception.server.ElidomServiceException;
import xyz.elidom.util.ThreadUtil;
import xyz.elidom.util.ValueUtil;
import xyz.tcp.agent.constants.ConfigConstants;

@Service
public class TcpSocketClientFactory {

	@Autowired
	Environment env;

	Map<String, TcpSocketClient> CLIENT_MAP = new HashMap<String, TcpSocketClient>();

	/**
	 * Client Data Send
	 * 
	 * @param serviceId
	 * @param value
	 * @param isSourceType
	 */
	public void send(String address, int port, String value) {
		String clientId = this.generateId(address, port);
		TcpSocketClient client = this.getTcpSocketClient(clientId);
		if (ValueUtil.isEmpty(client)) {
			this.getClientSession(address, port);
			client = this.getTcpSocketClient(clientId);
		}

		client.send(value);
	}

	/**
	 * 접속 정보를 통해 Client Session 객체 가져오기 실행.
	 * 
	 * @param adress
	 * @param port
	 * @return
	 */
	private IoSession getClientSession(String adress, int port) {
		// 유효 Session이 존재 할 경우
		TcpSocketClient client = this.getTcpSocketClient(adress, port);
		if (ValueUtil.isNotEmpty(client) && ValueUtil.isNotEmpty(client.getSession()) && client.getSession().isConnected())
			return client.getSession();

		// Session은 존재하지만 유효하지 않을 경우
		if (ValueUtil.isNotEmpty(client)) {
			client.stop();
		}

		client = new TcpSocketClient();

		long connectionTimeOut = ValueUtil.toLong(env.getProperty(ConfigConstants.TCP_CONNECTION_TIMEOUT, "30000"));
		long retryWaitTime = ValueUtil.toLong(env.getProperty(ConfigConstants.TCP_RETRY_WAIT_TIME, "5000"));
		
		// Start Client.
		this.start(client, adress, port, connectionTimeOut, retryWaitTime);

		long start = System.currentTimeMillis();

		while (true) {
			if (System.currentTimeMillis() - start > connectionTimeOut) {
				throw new ElidomServiceException("Connection time out.");
			}

			IoSession session = client.getSession();
			if (ValueUtil.isNotEmpty(session))
				return session;

			ThreadUtil.sleep(1000);
		}
	}

	/**
	 * Client Start
	 * 
	 * @param service
	 * @param connectionId
	 * @param isSourceType
	 */
	private void start(TcpSocketClient client, String address, Integer port, long connectionTimeOut, long retryWaitTime) {
		CLIENT_MAP.put(this.generateId(address, port), client);

		if (ValueUtil.isEmpty(address) || ValueUtil.isEmpty(port)) {
			throw new RuntimeException("Empty adrress or port value is not allowed.");
		}

		client.start(address, port, connectionTimeOut, retryWaitTime);
	}

	/**
	 * TCP Socket Client 객체 가져오기 실행.
	 * 
	 * @param serviceId
	 * @param isSourceType
	 * @return
	 */
	private TcpSocketClient getTcpSocketClient(String id) {
		return CLIENT_MAP.get(id);
	}

	private TcpSocketClient getTcpSocketClient(String address, int port) {
		return this.getTcpSocketClient(this.generateId(address, port));
	}

	/**
	 * Address, Port 정보를 통해 키 값을 설정.
	 * ex)127.0.0.1:9090
	 * 
	 * @param address
	 * @param port
	 * @return
	 */
	private String generateId(String address, int port) {
		return new StringBuilder().append(address).append(":").append(port).toString();
	}

	/**
	 * 접속 여부 확인.
	 * 
	 * @param address
	 * @param port
	 * @return
	 */
	public boolean isConnected(String address, Integer port) {
		return new TcpSocketClient().checkConnection(address, ValueUtil.toInteger(port));
	}

	/**
	 * 접속 해제.
	 * 
	 * @param address
	 * @param port
	 * @return
	 */
	public boolean disconnect(String address, int port) {
		String connectionId = this.generateId(address, port);
		TcpSocketClient client = CLIENT_MAP.get(connectionId);
		if (ValueUtil.isNotEmpty(client)) {
			CLIENT_MAP.remove(connectionId);
			client.stop();
		}
		return true;
	}
}