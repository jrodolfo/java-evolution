package net.jrodolfo.java_evolution.java22;

/**
 * Explains the Foreign Function and Memory API, finalized in Java 22.
 */
public class ForeignFunctionMemoryApiNotes {

	public String purpose() {
		return "interoperate with native code and memory outside the Java heap using supported APIs";
	}

	public String replacesManyUseCasesFor() {
		return "JNI";
	}

	public String projectDecision() {
		return "the repository keeps this as notes to avoid platform-specific native library examples";
	}
}
