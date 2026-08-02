package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyFourthPreviewNotesTest {

	private final StructuredConcurrencyFourthPreviewNotes notes = new StructuredConcurrencyFourthPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyAsPreviewInJava25() {
		assertThat(notes.status())
				.as("Structured concurrency should stay marked as preview in Java 25")
				.contains("fifth preview");
	}
}
