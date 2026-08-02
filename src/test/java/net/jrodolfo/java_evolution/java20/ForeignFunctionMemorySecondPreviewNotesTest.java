package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForeignFunctionMemorySecondPreviewNotesTest {

	private final ForeignFunctionMemorySecondPreviewNotes notes = new ForeignFunctionMemorySecondPreviewNotes();

	@Test
	void notesExplainFfmPreviewStatus() {
		assertThat(notes.purpose()).contains("native functions");
		assertThat(notes.java20Status()).contains("second preview");
		assertThat(notes.finalRelease()).contains("Java 22");
	}
}
