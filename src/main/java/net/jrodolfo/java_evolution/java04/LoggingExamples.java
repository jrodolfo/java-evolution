package net.jrodolfo.java_evolution.java04;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Demonstrates {@code java.util.logging}, introduced in J2SE 1.4.
 */
public class LoggingExamples {

	/**
	 * Logs to a custom in-memory handler.
	 *
	 * @return captured log message
	 */
	public String captureLogMessage() {
		Logger logger = Logger.getLogger("net.jrodolfo.java_evolution.java04");
		logger.setUseParentHandlers(false);
		CapturingHandler handler = new CapturingHandler();
		logger.addHandler(handler);

		try {
			logger.info("java util logging");
			return handler.message();
		}
		finally {
			logger.removeHandler(handler);
		}
	}

	static class CapturingHandler extends Handler {
		private String message;

		public void publish(LogRecord record) {
			if (record.getLevel().intValue() >= Level.INFO.intValue()) {
				message = record.getMessage();
			}
		}

		public void flush() {
		}

		public void close() {
		}

		String message() {
			return message;
		}
	}
}
