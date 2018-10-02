package xyz.tcp.agent.handler;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import xyz.tcp.agent.system.ITcpClientMessageHandler;

@Service
public class TcpClientMessgeHandler implements ITcpClientMessageHandler {

	@Override
	public void receive(String serverAddress, IoSession serverSession, String message) {
		// TODO Auto-generated method stub
	}
}
