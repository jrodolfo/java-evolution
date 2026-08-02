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
 * This repository keeps the feature as notes because a realistic native example
 * would require platform-specific libraries or setup.
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
		return "the repository keeps this as notes to avoid platform-specific native library examples";
	}
}
