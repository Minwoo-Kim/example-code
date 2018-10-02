package xyz.tcp.agent.system.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xyz.elidom.util.ThreadUtil;
import xyz.elidom.util.ValueUtil;
import xyz.tcp.agent.constants.ConfigConstants;
import xyz.tcp.agent.system.TcpServiceManager;

@Service
public class SystemInitializer {

	@Autowired
	Environment env;

	@Autowired
	TcpServiceManager tcpServiceManager;

	@EventListener({ ContextRefreshedEvent.class })
	public void ready(ContextRefreshedEvent event) {
	}

	@EventListener({ ApplicationReadyEvent.class })
	public void contextRefreshedEvent(ApplicationReadyEvent event) {
		this.tcpServerStart();
	}

	/**
	 * TCP Server 구동.
	 */
	private void tcpServerStart() {
		boolean isAutoStart = ValueUtil.toBoolean(env.getProperty(ConfigConstants.TCP_SERVER_AUTO_START), true);

		if (!isAutoStart)
			return;

		String ports = env.getProperty(ConfigConstants.TCP_SERVER_PORT_LIST);
		if (ValueUtil.isEmpty(ports))
			return;

		String[] portArr = StringUtils.tokenizeToStringArray(ports, ",");
		if (ValueUtil.isEmpty(portArr))
			return;

		for (String port : portArr) {
			Integer serverPort = ValueUtil.toInteger(port);
			if (serverPort != null) {
				ThreadUtil.doAsynch(() -> tcpServiceManager.startServer(serverPort));
			}
		}
	}
}