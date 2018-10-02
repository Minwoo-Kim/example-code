package xyz.tcp.agent;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import xyz.tcp.agent.system.listener.ShutdownListener;

@SpringBootApplication
@ComponentScan(basePackages = { "xyz.*" })
public class ApplicationMain {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationMain.class).listeners(new ShutdownListener()).run(args);
		// SpringApplication.run(ApplicationMain.class, args);
	}
}