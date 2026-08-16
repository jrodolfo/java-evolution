package net.jrodolfo.java_evolution.java23.class_file_api;

/**
 * Explains the Class-File API as a Java 23 second preview feature.
 *
 * <p>
 * Tools and frameworks often need to inspect or transform Java class files.
 * Before the standard Class-File API, that work usually depended on
 * third-party bytecode libraries. The JDK API gives tools a standard model that
 * can evolve with the class-file format.
 * </p>
 */
public class ClassFileApiSecondPreviewNotes {

	/**
	 * Explains the API goal.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "tools need a standard API to parse, generate, and transform Java class files";
	}

	/**
	 * Explains why a JDK API is useful.
	 *
	 * @return a short explanation
	 */
	public String platformReason() {
		return "a standard JDK model can evolve together with the Java class-file format";
	}

	/**
	 * Explains the Java 22 connection.
	 *
	 * @return a short explanation
	 */
	public String java22Connection() {
		return "Java 22 introduced the Class-File API as a first preview";
	}

	/**
	 * Points learners to the final Java 24 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 24 class_file module for the final executable Class-File API example";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 23 and final in Java 24";
	}
}
