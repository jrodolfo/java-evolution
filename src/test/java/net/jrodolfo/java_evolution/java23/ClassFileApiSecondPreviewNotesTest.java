package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiSecondPreviewNotesTest {

	private final ClassFileApiSecondPreviewNotes notes = new ClassFileApiSecondPreviewNotes();

	@Test
	void notesIdentifyClassFileToolingAsTheProblemSpace() {
		assertThat(notes.purpose())
				.as("The Class-File API note should identify bytecode/class-file tooling as the problem space")
				.contains("class files");
		assertThat(notes.status())
				.as("The feature should be documented as preview before becoming final")
				.contains("preview");
	}
}
