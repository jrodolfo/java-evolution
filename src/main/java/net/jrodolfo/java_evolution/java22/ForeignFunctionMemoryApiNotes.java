package net.jrodolfo.java_evolution.java22;

/**
 * Explains the Foreign Function and Memory API, finalized in Java 22.
 *
 * <p>
 * JNI has long allowed Java to call native code, but it is complex and easy to
 * make unsafe. The Foreign Function and Memory API provides a supported
 * Java-level model for native calls and off-heap memory access.
 * </p>
 *
 * <p>
 * The executable native-call examples live in the {@code foreign_function}
 * subpackage. These notes keep the high-level purpose easy to find from the
 * main Java 22 package.
 * </p>
 */
public class ForeignFunctionMemoryApiNotes {

	/**
	 * Explains the high-level goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "interoperate with native code and memory outside the Java heap using supported APIs";
	}

	/**
	 * Names the older technology this API can replace for many use cases.
	 *
	 * @return older technology name
	 */
	public String replacesManyUseCasesFor() {
		return "JNI";
	}

	/**
	 * Explains why this repository avoids a live native example.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the runnable FFM examples live in the foreign_function subpackage";
	}

	/**
	 * Points readers to the detailed Markdown explanation.
	 *
	 * @return the local documentation file for the full explanation
	 */
	public String detailedExplanation() {
		return "src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/README.md";
	}
}
