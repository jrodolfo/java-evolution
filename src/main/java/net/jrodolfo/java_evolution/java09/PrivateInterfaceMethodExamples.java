package net.jrodolfo.java_evolution.java09;

/**
 * Demonstrates private methods in interfaces, introduced in Java 9.
 *
 * <p>
 * Java 8 introduced default methods, which allowed interfaces to provide
 * behavior. The missing piece was private helper logic: without Java 9, common
 * code shared by multiple default methods had to be duplicated or exposed as a
 * public interface method.
 * </p>
 *
 * <p>
 * Java 9 solved this by allowing private and private static methods in
 * interfaces. Default methods can now share implementation details without
 * adding those helpers to the public API.
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
