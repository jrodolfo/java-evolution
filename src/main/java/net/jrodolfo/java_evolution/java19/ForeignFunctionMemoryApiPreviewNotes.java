package net.jrodolfo.java_evolution.java19;

/**
 * Explains the Foreign Function and Memory API as a Java 19 preview feature.
 *
 * <p>
 * Native integration in Java traditionally meant JNI, which is powerful but
 * complex and easy to make unsafe. Java developers used it when Java code
 * needed to call operating-system functions, C libraries, or other native
 * libraries that were not written for the Java Virtual Machine.
 * </p>
 *
 * <p>
 * The Foreign Function and Memory API was introduced to provide a supported
 * model for calling native functions and working with memory outside the Java
 * heap. Java 19 was an early preview step. The API evolved through later
 * releases before becoming final in Java 22.
 * </p>
 *
 * <p>
 * This repository keeps the Java 19 entry as notes because preserving every
 * historical preview API shape would make the project harder to run. The Java
 * 22 package contains the executable learning module for the final API.
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
	 * Explains why native integration matters.
	 *
	 * @return a short explanation
	 */
	public String commonUseCase() {
		return "Java code sometimes needs to call operating-system functions or libraries written in languages such as C";
	}

	/**
	 * Explains why this repository keeps it as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 19 API was preview and changed later, so the repository avoids release-specific native code";
	}

	/**
	 * Points learners to the executable final API module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 22 foreign_function module for an executable example of the final API";
	}
}
