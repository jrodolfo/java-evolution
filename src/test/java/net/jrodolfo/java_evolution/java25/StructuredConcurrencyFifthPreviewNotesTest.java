package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyFifthPreviewNotesTest {

	private final StructuredConcurrencyFifthPreviewNotes notes = new StructuredConcurrencyFifthPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyMarkedAsPreview() {
		assertThat(notes.status())
				.as("Structured concurrency should stay marked as preview")
				.contains("fifth preview");
	}
}
