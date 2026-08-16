package net.jrodolfo.java_evolution.java25;

/**
 * Explains compact source files and instance main methods, finalized in Java 25.
 *
 * <p>
 * Tiny Java programs traditionally required a class declaration and static
 * {@code main} method. Compact source files and instance main methods reduce
 * that startup ceremony for learning, scripts, and small utilities.
 * </p>
 *
 * <p>
 * A compact source file can contain methods and fields without wrapping them in
 * an explicit class declaration. For example:
 * </p>
 *
 * <pre>{@code
 * void main() {
 *     IO.println("Hello, Java 25");
 * }
 * }</pre>
 *
 * <p>
 * This repository documents the feature as notes because compact source files
 * belong naturally to source-launcher examples rather than the Spring Boot
 * source tree.
 * </p>
 */
public class CompactSourceFilesNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "let small programs start without an explicit class declaration or static main ceremony";
	}

	/**
	 * Shows the natural source-launcher command shape for a compact source file.
	 *
	 * @return sample launcher command
	 */
	public String sourceLauncherCommand() {
		return "java HelloWorld.java";
	}

	/**
	 * Explains why this project does not include compact source files directly.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the repository documents source-launcher behavior because compact source files naturally live in the unnamed package rather than this Spring Boot package tree";
	}
}
