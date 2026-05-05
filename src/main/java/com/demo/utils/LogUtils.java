package com.demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    // Initialize Log4j instance
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    // Info Level Logs
    public static void info(String message) {
        logger.info(message);
    }

    // Warn Level Logs
    public static void warn(String message) {
        logger.warn(message);
    }

    // Error Level Logs
    public static void error(String message) {
        logger.error(message);
    }

    // Fatal Level Logs
    public static void fatal(String message) {
        logger.fatal(message);
    }

    // Debug Level Logs
    public static void debug(String message) {
        logger.debug(message);
    }
}
