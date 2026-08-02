package net.jrodolfo.java_evolution.java18;

/**
 * Explains the simple web server introduced in Java 18.
 *
 * <p>
 * Java 18 added the {@code jwebserver} command-line tool for serving static
 * files during development or demos. Unit tests do not launch it because that
 * would introduce port and process-management concerns.
 * </p>
 */
public class SimpleWebServerNotes {

	/**
	 * Names the command-line tool.
	 *
	 * @return the tool name
	 */
	public String command() {
		return "jwebserver";
	}

	/**
	 * Gives a simple command example.
	 *
	 * @return a sample command
	 */
	public String exampleCommand() {
		return "jwebserver --port 8000 --directory public";
	}

	/**
	 * Explains the intended use case.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "serve static files with the JDK during local development or simple demos";
	}
}
