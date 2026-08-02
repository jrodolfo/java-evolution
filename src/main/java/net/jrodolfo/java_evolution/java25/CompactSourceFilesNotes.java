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
	 * Explains why this project does not include compact source files directly.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the repository documents source-launcher behavior instead of mixing compact source files into the Spring Boot source tree";
	}
}
