package net.jrodolfo.java_evolution.java18;

/**
 * Explains the simple web server introduced in Java 18.
 *
 * <p>
 * Before Java 18, serving static files usually meant installing another tool or
 * writing a small server. Java 18 added the {@code jwebserver} command-line
 * tool for the simpler case: quickly serving a directory during local
 * development, documentation review, or demos.
 * </p>
 *
 * <p>
 * Unit tests do not launch it because that would introduce port and
 * process-management concerns. This is a tooling feature, so notes communicate
 * the practical usage without making the test suite depend on a background
 * process.
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
