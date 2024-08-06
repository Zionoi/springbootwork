package com.study.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner {

//	private static final 생략시 자동으로 붙음
	private static final Logger logger = LoggerFactory.getLogger(Ex03LoggerApplication.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.trace("Trace 레벨 로그");
		logger.debug("Debug 레벨 로그");
		logger.info("Info 레벨 로그");
		logger.warn("Warn 레벨 로그");
		logger.error("Error 레벨 로그");

	}

}
