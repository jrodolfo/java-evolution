package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiNotesTest {

	private final ClassFileApiNotes notes = new ClassFileApiNotes();

	@Test
	void notesIdentifyStandardBytecodeTooling() {
		assertThat(notes.purpose())
				.as("The final Class-File API should be framed as standard class-file tooling")
				.contains("class files");
		assertThat(notes.audience())
				.as("The note should identify who benefits from this API")
				.contains("tools");
		assertThat(notes.detailedExplanation())
				.as("The notes should point learners to the executable Class-File API example")
				.endsWith("class_file/README.md");
	}
}
