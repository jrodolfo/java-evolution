package net.jrodolfo.java_evolution.java22.launch_multi_file_source_programs;

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
 * The important idea is that {@code Main.java} can refer to another source file
 * next to it, such as {@code Greeting.java}, and the launcher can compile the
 * small source tree for that run. This is different from this Maven project,
 * where source files are compiled through the normal build lifecycle.
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
	 * Explains the single-file launcher baseline.
	 *
	 * @return a short baseline explanation
	 */
	public String singleFileBaseline() {
		return "the source launcher can start from one source file such as Main.java";
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
	 * Shows the conceptual file layout for a multi-file source launch.
	 *
	 * @return sample source file layout
	 */
	public String exampleFileLayout() {
		return """
				Main.java
				Greeting.java
				""";
	}

	/**
	 * Explains what changed from the single-source launcher mental model.
	 *
	 * @return a short explanation
	 */
	public String multiFileMeaning() {
		return "Main.java can reference another source file such as Greeting.java without creating a Maven or Gradle project first";
	}

	/**
	 * Explains when a build tool becomes the better fit.
	 *
	 * @return a short build-tool boundary note
	 */
	public String buildToolBoundary() {
		return "use Maven or Gradle when the program needs dependencies, packaging, or a repeatable build lifecycle";
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
