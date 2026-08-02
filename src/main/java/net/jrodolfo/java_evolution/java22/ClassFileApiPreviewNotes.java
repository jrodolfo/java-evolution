package net.jrodolfo.java_evolution.java22;

/**
 * Explains the Class-File API, introduced as a Java 22 preview feature.
 *
 * <p>
 * Tools such as frameworks, compilers, agents, and bytecode libraries often
 * need to read, generate, or transform {@code .class} files. Java 22 introduced
 * a standard API that can evolve together with the class-file format.
 * </p>
 *
 * <p>
 * The Java 22 API was preview, so this repository keeps the entry as notes
 * instead of depending on a preview API shape.
 * </p>
 */
public class ClassFileApiPreviewNotes {

	/**
	 * Explains the API goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "parse, generate, and transform Java class files with a standard API";
	}

	/**
	 * Names the main audience.
	 *
	 * @return likely users of the API
	 */
	public String audience() {
		return "tools, frameworks, compilers, and bytecode libraries";
	}

	/**
	 * Explains why this package keeps the feature as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 22 API was preview, so the repository keeps this entry as notes";
	}
}
