package net.jrodolfo.java_evolution.java09;

/**
 * Demonstrates private methods in interfaces, introduced in Java 9.
 *
 * <p>
 * Java 8 introduced default methods. Java 9 completed that idea by allowing
 * interfaces to hide shared helper logic in private methods instead of
 * duplicating it across default methods.
 * </p>
 */
public class PrivateInterfaceMethodExamples {

	/**
	 * Creates a formatter that inherits default methods using private interface
	 * helpers.
	 *
	 * @return a formatter implementation
	 */
	public MessageFormatter formatter() {
		return new DefaultMessageFormatter();
	}

	/**
	 * Interface with default methods that share private helper logic.
	 */
	public interface MessageFormatter {
		/**
		 * Formats a normal message.
		 *
		 * @param message the raw message
		 * @return a normalized message with a standard prefix
		 */
		default String standard(String message) {
			return prefix() + normalize(message);
		}

		/**
		 * Formats an urgent message.
		 *
		 * @param message the raw message
		 * @return an uppercase normalized message with a standard prefix
		 */
		default String urgent(String message) {
			return prefix() + normalize(message).toUpperCase();
		}

		private String normalize(String message) {
			return message == null ? "" : message.trim();
		}

		private static String prefix() {
			return "[java 9] ";
		}
	}

	/**
	 * Empty implementation that receives behavior from interface default methods.
	 */
	public static class DefaultMessageFormatter implements MessageFormatter {
	}
}
