package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LaunchMultiFileSourceProgramsNotesTest {

	private final LaunchMultiFileSourceProgramsNotes notes = new LaunchMultiFileSourceProgramsNotes();

	@Test
	void notesExplainMultiFileSourceLauncher() {
		assertThat(notes.purpose())
				.as("The note should explain the source-launcher convenience goal")
				.contains("source files")
				.contains("build tool");
		assertThat(notes.exampleCommand())
				.as("The launcher still starts from the main source file")
				.isEqualTo("java Main.java");
		assertThat(notes.exampleFileLayout())
				.as("The note should show that the launched program can involve more than one source file")
				.contains("Main.java")
				.contains("Greeting.java");
		assertThat(notes.multiFileMeaning())
				.as("The note should explain that the extra source file is discovered without a build project")
				.contains("Greeting.java")
				.contains("Maven or Gradle");
		assertThat(notes.projectDecision())
				.as("The note should explain why the repository does not spawn a separate launcher process")
				.contains("launcher behavior");
	}
}
