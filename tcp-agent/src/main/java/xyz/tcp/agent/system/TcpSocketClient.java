package xyz.tcp.agent.system;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.elidom.util.ThreadUtil;
import xyz.elidom.util.ValueUtil;
import xyz.tcp.agent.util.TcpUtil;

public class TcpSocketClient extends IoHandlerAdapter {
	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 현재 실행 중인지 여부
	 */
	private boolean isRunning;

	/**
	 * 세션
	 */
	private IoSession session;

	void start(String address, int port, long connectionTimeOut, long retryWaitTime) {
		if (this.isRunning)
			return;

		ThreadUtil.doAsynch(() -> {
			NioSocketConnector connector = new NioSocketConnector();
			connector.setConnectTimeoutMillis(connectionTimeOut);
			connector.getFilterChain().addLast("logger", new LoggingFilter());
			connector.setHandler(this);

			this.isRunning = true;

			while (this.isRunning) {
				try {
					ConnectFuture future = connector.connect(new InetSocketAddress(address, port));
					future.awaitUninterruptibly();
					this.setSession(future.getSession());
					break;

				} catch (RuntimeIoException e) {
					this.logger.error("TCP Connection Error.", e);
					ThreadUtil.sleep(retryWaitTime);
				}
			}

			if (session != null) {
				session.getCloseFuture().awaitUninterruptibly();
			}

			connector.dispose();
		});
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public boolean isConnected() {
		return this.session == null ? false : this.session.isConnected();
	}

	void stop() {
		this.isRunning = false;
		if (ValueUtil.isNotEmpty(this.session))
			this.session.closeNow();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		this.setSession(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		this.setSession(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		this.setSession(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		this.setSession(session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		this.setSession(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		this.setSession(session);

		String value = TcpUtil.parseReceivedMessage(message);
		System.out.println(value);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		this.setSession(session);
		this.logger.info(message.toString());
	}

	/**
	 * 데이터를 서버로 전송.
	 *
	 * @param value
	 */
	public void send(String value) {
		this.send(value.getBytes());
	}

	/**
	 * byte[] raw 데이터로 서버에 전송
	 *
	 * @param rawMsg
	 */
	public void send(byte[] rawMsg) {
		if (this.session != null && this.session.isConnected()) {
			TcpUtil.send(this.session, rawMsg);
		}
	}

	public IoSession getSession() {
		return this.session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	/**
	 * Check Connection.
	 * 
	 * @param address
	 * @param port
	 * @return
	 */
	boolean checkConnection(String address, int port) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.setHandler(this);

		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(address, port));
			future.awaitUninterruptibly();
			session = future.getSession();
			return session.isConnected();
		} catch (RuntimeIoException e) {
			return false;
		} finally {
			if (session != null) {
				session.closeNow();
				connector.dispose();
			}
		}
	}
}