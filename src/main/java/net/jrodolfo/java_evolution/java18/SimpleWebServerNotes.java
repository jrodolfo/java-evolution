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
 * The executable example in the {@code simple_web_server} subpackage uses the
 * Java API behind the same feature: {@code SimpleFileServer}. It starts a local
 * server on an ephemeral port, serves a temporary directory, and keeps the
 * example focused on local static-file serving.
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

	/**
	 * Points to the executable example and detailed explanation.
	 *
	 * @return project-relative path to the detailed explanation
	 */
	public String detailedExplanation() {
		return "src/main/java/net/jrodolfo/java_evolution/java18/simple_web_server/README.md";
	}
}
