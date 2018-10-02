package xyz.tcp.agent.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.tcp.agent.system.TcpServiceManager;

@RestController
@RequestMapping("/tcp")
public class TcpSampleService {
	@Autowired
	TcpServiceManager tcpServiceManager;

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public boolean start() {
		tcpServiceManager.startServer(4333);
		return tcpServiceManager.startServer(4335);
	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public void stop() {
		tcpServiceManager.stopAll();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<Integer, List<String>> clientList() {
		return tcpServiceManager.getClientList();
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public void send() {
		tcpServiceManager.publish(4335, "TCP TEST-!!");
	}
}
