package xyz.tcp.agent.system;

import org.apache.mina.core.session.IoSession;

public interface ITcpServerMessageHandler {
	public void receive(String serverAddress, IoSession clientSession, String message);
}
