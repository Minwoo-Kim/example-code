package com.example.code.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExample {
	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(LogExample.class);
	
	public void sample() {
		logger.debug("A:{}, B:{}, C:{}", new Object[] {"A", "B", "C"});
	}
}
