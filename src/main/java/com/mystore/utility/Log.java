package com.mystore.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	public static Logger logger = LogManager.getLogger(Log.class);

	public static void startTestCase(String testCaseName) {
		logger.info("-------------------------" + testCaseName + " TEST START ---------------------");
	}

	public static void endTestCase(String testCaseName) {
		logger.info("-------------------------" + testCaseName + " TEST End ---------------------");
	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error(message);
	}

	public static void fatal(String message) {
		logger.fatal(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}
}
