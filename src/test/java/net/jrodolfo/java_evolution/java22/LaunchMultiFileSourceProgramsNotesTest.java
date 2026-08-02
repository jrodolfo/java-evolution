package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LaunchMultiFileSourceProgramsNotesTest {

	private final LaunchMultiFileSourceProgramsNotes notes = new LaunchMultiFileSourceProgramsNotes();

	@Test
	void notesExplainMultiFileSourceLauncher() {
		assertThat(notes.purpose()).contains("source files").contains("build tool");
		assertThat(notes.exampleCommand()).isEqualTo("java Main.java");
		assertThat(notes.projectDecision()).contains("launcher behavior");
	}
}
