package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Class-File API, finalized in Java 24.
 *
 * <p>
 * Bytecode tools, frameworks, compilers, and analysis utilities often need to
 * parse, generate, or transform {@code .class} files. Java 24 finalized a
 * standard API for that work, allowing the JDK and tools to evolve with the
 * class-file format together.
 * </p>
 */
public class ClassFileApiNotes {
	/**
	 * Explains the API goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "standard API for parsing, generating, and transforming Java class files";
	}

	/**
	 * Names the main audience.
	 *
	 * @return likely users of the API
	 */
	public String audience() {
		return "bytecode tools, frameworks, compilers, and analysis utilities";
	}
}
