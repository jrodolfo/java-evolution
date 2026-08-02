package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyPreviewNotesTest {

	private final StructuredConcurrencyPreviewNotes notes = new StructuredConcurrencyPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencyPreviewStatus() {
		assertThat(notes.purpose()).contains("single unit");
		assertThat(notes.previewStatus()).contains("Java 21");
	}
}
