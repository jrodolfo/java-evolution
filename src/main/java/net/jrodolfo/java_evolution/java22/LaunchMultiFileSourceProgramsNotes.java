package net.jrodolfo.java_evolution.java22;

/**
 * Explains launching multi-file source-code programs, introduced in Java 22.
 */
public class LaunchMultiFileSourceProgramsNotes {

	public String purpose() {
		return "run small Java programs from source files without setting up a build tool first";
	}

	public String exampleCommand() {
		return "java Main.java";
	}

	public String projectDecision() {
		return "this repository documents the launcher behavior instead of spawning separate java processes in tests";
	}
}
