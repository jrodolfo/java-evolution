package net.jrodolfo.java_evolution.java06.console_api;

import java.io.Console;
import java.util.Arrays;

/**
 * Demonstrates the Java 6 {@link Console} API at a testable process boundary.
 */
public class ConsoleApiExamples {

	public ConsoleAvailability currentConsoleAvailability() {
		return consoleAvailability(JavaConsoleSession.current());
	}

	public ConsoleAvailability consoleAvailability(ConsoleSession session) {
		if (session == null) {
			return new ConsoleAvailability(false, "System.console() is not available in this process");
		}
		return new ConsoleAvailability(true, "interactive console is available");
	}

	public String greetUser(ConsoleSession session) {
		if (session == null) {
			return "console unavailable";
		}

		String name = session.readLine("name: ");
		session.printf("hello, %s%n", name);
		return "hello, " + name;
	}

	public int readPasswordLengthAndClear(ConsoleSession session) {
		if (session == null) {
			return 0;
		}

		char[] password = session.readPassword("password: ");
		if (password == null) {
			return 0;
		}

		try {
			return password.length;
		} finally {
			clearSecret(password);
		}
	}

	public void clearSecret(char[] secret) {
		if (secret != null) {
			Arrays.fill(secret, '\0');
		}
	}

	public String passwordGuidance() {
		return "Console.readPassword returns char[] so callers can clear sensitive data after use";
	}

	public interface ConsoleSession {

		String readLine(String prompt, Object... args);

		char[] readPassword(String prompt, Object... args);

		void printf(String format, Object... args);
	}

	public static final class JavaConsoleSession implements ConsoleSession {

		private final Console console;

		private JavaConsoleSession(Console console) {
			this.console = console;
		}

		static ConsoleSession current() {
			Console console = System.console();
			if (console == null) {
				return null;
			}
			return new JavaConsoleSession(console);
		}

		@Override
		public String readLine(String prompt, Object... args) {
			return console.readLine(prompt, args);
		}

		@Override
		public char[] readPassword(String prompt, Object... args) {
			return console.readPassword(prompt, args);
		}

		@Override
		public void printf(String format, Object... args) {
			console.printf(format, args);
		}
	}

	public static final class ConsoleAvailability {

		private final boolean available;
		private final String message;

		private ConsoleAvailability(boolean available, String message) {
			this.available = available;
			this.message = message;
		}

		public boolean available() {
			return available;
		}

		public String message() {
			return message;
		}
	}
}
