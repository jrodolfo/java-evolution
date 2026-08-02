package net.jrodolfo.java_evolution.java19;

/**
 * Explains the Foreign Function and Memory API as a Java 19 preview feature.
 *
 * <p>
 * The API evolved through later releases before finalization. A serious example
 * normally calls native code or manages off-heap memory, so this repository
 * keeps the Java 19 entry as notes.
 * </p>
 */
public class ForeignFunctionMemoryApiPreviewNotes {

	/**
	 * Explains the high-level goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "call native functions and access memory outside the Java heap with supported APIs";
	}

	/**
	 * Names the older technology this API can replace for many use cases.
	 *
	 * @return the older technology name
	 */
	public String alternativeTo() {
		return "JNI";
	}

	/**
	 * Explains why this repository keeps it as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 19 API was preview and changed later, so the repository avoids release-specific native code";
	}
}
