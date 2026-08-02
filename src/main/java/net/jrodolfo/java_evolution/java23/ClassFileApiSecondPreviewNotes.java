package net.jrodolfo.java_evolution.java23;

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
	public String purpose() {
		return "parse, generate, and transform Java class files with a standard API";
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
