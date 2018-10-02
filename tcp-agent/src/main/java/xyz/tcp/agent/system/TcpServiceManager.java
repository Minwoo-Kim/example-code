package xyz.tcp.agent.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpServiceManager {

	@Autowired
	TcpSocketServer tcpSocketServer;

	@Autowired
	TcpSocketClientFactory tcpSocketClientFactory;

	/**
	 * TCP Server Start
	 * 
	 * @param port
	 * @return
	 */
	public boolean startServer(int port) {
		return tcpSocketServer.start(port);
	}

	/**
	 * TCP Server Stop
	 * 
	 * @param port
	 */
	public void stopServer(int port) {
		tcpSocketServer.stop(port);
	}
	
	/**
	 * ALL TCP Server Stop
	 */
	public void stopAll() {
		tcpSocketServer.stopAll();
	}

	/**
	 * Server 별 접속 Client 정보 가져오기 실행.
	 * 
	 * @return
	 */
	public Map<Integer, List<String>> getClientList() {
		return tcpSocketServer.getClientList();
	}

	/**
	 * 접속 중인 모든 Client로 데이터 전송.
	 * 
	 * @param port
	 * @param message
	 */
	public void publish(int port, String message) {
		tcpSocketServer.publish(port, message);
	}
}
