package net.jrodolfo.java_evolution.java22.launch_multi_file_source_programs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LaunchMultiFileSourceProgramsNotesTest {

	private final LaunchMultiFileSourceProgramsNotes notes = new LaunchMultiFileSourceProgramsNotes();

	@Test
	void notesExplainMultiFileSourceLauncher() {
		String purpose = notes.purpose();
		String singleFileBaseline = notes.singleFileBaseline();
		String exampleCommand = notes.exampleCommand();
		String exampleFileLayout = notes.exampleFileLayout();
		String multiFileMeaning = notes.multiFileMeaning();
		String buildToolBoundary = notes.buildToolBoundary();
		String projectDecision = notes.projectDecision();

		assertThat(purpose)
				.as("The note should explain the source-launcher convenience goal")
				.contains("source files")
				.contains("build tool");
		assertThat(singleFileBaseline)
				.as("The learner should understand the single-file launcher baseline first")
				.contains("source launcher")
				.contains("Main.java");
		assertThat(exampleCommand)
				.as("The launcher still starts from the main source file")
				.isEqualTo("java Main.java");
		assertThat(exampleFileLayout)
				.as("The note should show that the launched program can involve more than one source file")
				.contains("Main.java")
				.contains("Greeting.java");
		assertThat(multiFileMeaning)
				.as("The note should explain that the extra source file is discovered without a build project")
				.contains("Greeting.java")
				.contains("Maven or Gradle");
		assertThat(buildToolBoundary)
				.as("The note should explain when a full build tool becomes the better fit")
				.contains("dependencies")
				.contains("repeatable build lifecycle");
		assertThat(projectDecision)
				.as("The note should explain why the repository does not spawn a separate launcher process")
				.contains("launcher behavior")
				.contains("spawning separate java processes");
	}
}
