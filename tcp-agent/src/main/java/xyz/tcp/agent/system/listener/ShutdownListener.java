package xyz.tcp.agent.system.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import xyz.tcp.agent.system.TcpSocketServer;

public class ShutdownListener implements ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

	/**
	 * ApplicationContext
	 */
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		try {
			applicationContext.getBean(TcpSocketServer.class).stopAll();
		} catch (Throwable e) {
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
