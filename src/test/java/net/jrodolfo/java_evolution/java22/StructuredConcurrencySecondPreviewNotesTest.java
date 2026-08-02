package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySecondPreviewNotesTest {

	private final StructuredConcurrencySecondPreviewNotes notes = new StructuredConcurrencySecondPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencySecondPreview() {
		assertThat(notes.purpose()).contains("concurrent subtasks").contains("cancellation");
		assertThat(notes.secondPreviewStatus()).contains("second preview").contains("Java 22");
	}
}
