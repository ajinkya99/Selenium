package tutorial.selenium;

import org.apache.log4j.*;

/**
 * Trace
 * Debug
 * Info
 * Warn
 * Error
 * Fatal
 * VM Argument: -Dlog4j.debug=true
 */
public class LoggingDemo {
	static Logger log = Logger.getLogger(LoggingDemo.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		log.info("This message is a info");
		runMethod();
		log.debug("This message is a debug");
	}

	public static void runMethod() {
		log.info("This info message is from runMethod");
	}
}