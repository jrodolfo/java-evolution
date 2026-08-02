package net.jrodolfo.java_evolution.java22;

/**
 * Explains launching multi-file source-code programs, introduced in Java 22.
 *
 * <p>
 * Java's source launcher lowers the barrier for small programs because a build
 * tool is not required for the first experiment. Java 22 expanded this idea to
 * multi-file source programs, which helps small examples grow a little before
 * they need Maven or Gradle.
 * </p>
 *
 * <p>
 * This repository keeps the feature as notes because testing launcher behavior
 * would require creating temporary source trees and spawning separate Java
 * processes.
 * </p>
 */
public class LaunchMultiFileSourceProgramsNotes {

	/**
	 * Explains the launcher goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "run small Java programs from source files without setting up a build tool first";
	}

	/**
	 * Shows the basic command shape.
	 *
	 * @return sample command
	 */
	public String exampleCommand() {
		return "java Main.java";
	}

	/**
	 * Explains why this project documents the behavior instead of launching it.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository documents the launcher behavior instead of spawning separate java processes in tests";
	}
}
