package com.qa.opencart.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	private static final Logger logger = LogManager.getLogger(Log.class);
	
	public static void info(Object message)
	{
		logger.info(message);
	}
	
	public static void error(Object message)
	{
		logger.error(message);
	}
	
	public static void error(Object message, Exception e)
	{
		logger.error(message,e);
	}
	
	public static void debug(Object message)
	{
		logger.debug(message);
	}
	
	public static void warn(Object message)
	{
		logger.warn(message);
	}
}