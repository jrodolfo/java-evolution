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
 *
 * <p>
 * The executable inspection example lives in the {@code class_file}
 * subpackage. These notes keep the high-level purpose easy to find from the
 * main Java 24 package.
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

	/**
	 * Points to the detailed executable example.
	 *
	 * @return project-relative path to the detailed explanation
	 */
	public String detailedExplanation() {
		return "src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md";
	}
}
