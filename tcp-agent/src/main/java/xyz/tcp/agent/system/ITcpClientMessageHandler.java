package xyz.tcp.agent.system;

import org.apache.mina.core.session.IoSession;

public interface ITcpClientMessageHandler {
	public void receive(String serverAddress, IoSession serverSession, String message);
}
