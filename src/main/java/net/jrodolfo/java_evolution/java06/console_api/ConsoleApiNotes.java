package net.jrodolfo.java_evolution.java06.console_api;

/**
 * Explains the Java 6 {@code java.io.Console} API.
 */
public class ConsoleApiNotes {

	/**
	 * @return the purpose of the console API
	 */
	public String purpose() {
		return "java.io.Console gives command-line programs line input, formatted prompts, writers, and password reading";
	}

	/**
	 * @return why console tests are environment-sensitive
	 */
	public String nullConsoleCaveat() {
		return "System.console() can return null under Maven, IDEs, CI, background jobs, or redirected streams";
	}

	/**
	 * @return the password-handling lesson
	 */
	public String passwordGuidance() {
		return "readPassword avoids echoing secrets and returns char[] so callers can clear sensitive data after use";
	}
}
