package xyz.tcp.agent.handler;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import xyz.tcp.agent.system.ITcpServerMessageHandler;
import xyz.tcp.agent.util.TcpUtil;

@Service
public class TcpServerMessgeHandler implements ITcpServerMessageHandler {

	@Override
	public void receive(String serverAddress, IoSession clientSession, String message) {
		TcpUtil.send(clientSession, message);
		System.out.println("DDD");
	}
}
