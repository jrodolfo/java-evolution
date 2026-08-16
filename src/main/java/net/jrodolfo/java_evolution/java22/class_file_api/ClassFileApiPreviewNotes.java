package net.jrodolfo.java_evolution.java22.class_file_api;

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
 * This Java 22 package keeps the feature explanatory because the API was still
 * preview in Java 22. The final runnable example lives in the Java 24
 * {@code class_file} module, where the Class-File API became final.
 * </p>
 */
public class ClassFileApiPreviewNotes {

	/**
	 * Explains the API goal.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "tools need a supported way to parse, generate, and transform Java class files";
	}

	/**
	 * Names the main audience.
	 *
	 * @return likely users of the API
	 */
	public String audience() {
		return "frameworks, compilers, agents, analysis tools, and bytecode libraries";
	}

	/**
	 * Explains why the API belongs in the JDK.
	 *
	 * @return a short explanation
	 */
	public String platformReason() {
		return "a standard API can evolve together with the Java class-file format";
	}

	/**
	 * Explains the preview status.
	 *
	 * @return a short preview-status note
	 */
	public String previewStatus() {
		return "the Class-File API was preview in Java 22 and became final in Java 24";
	}

	/**
	 * Points learners to the final Java 24 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 24 class_file module for the final executable Class-File API example";
	}
}
