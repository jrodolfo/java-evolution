package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyThirdPreviewNotesTest {

	private final StructuredConcurrencyThirdPreviewNotes notes = new StructuredConcurrencyThirdPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyMarkedAsEvolving() {
		assertThat(notes.purpose())
				.as("Structured concurrency should explain grouping related tasks")
				.contains("tasks");
		assertThat(notes.status())
				.as("Structured concurrency should remain marked as preview in this release range")
				.contains("fifth preview");
	}
}
