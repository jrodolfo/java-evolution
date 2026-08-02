package net.jrodolfo.java_evolution.java20;

/**
 * Explains the Foreign Function and Memory API second preview in Java 20.
 *
 * <p>
 * Java code has long been able to call native code through JNI, but JNI is
 * difficult to use safely and cleanly. The Foreign Function and Memory API aims
 * to provide supported native calls and off-heap memory access with clearer
 * Java-level APIs.
 * </p>
 *
 * <p>
 * Java 20 was still a preview release for this API, so this repository avoids a
 * native-code example and keeps the focus on the problem the feature solves.
 * </p>
 */
public class ForeignFunctionMemorySecondPreviewNotes {

	/**
	 * Explains the high-level goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "call native functions and access memory outside the Java heap using supported APIs";
	}

	/**
	 * Describes the Java 20 maturity level.
	 *
	 * @return a short status note
	 */
	public String java20Status() {
		return "the Foreign Function and Memory API was in second preview in Java 20";
	}

	/**
	 * Names the release where the API became final.
	 *
	 * @return a short release note
	 */
	public String finalRelease() {
		return "the API became final in Java 22";
	}
}
