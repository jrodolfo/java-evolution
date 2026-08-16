package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyPreviewNotesTest {

	private final StructuredConcurrencyPreviewNotes notes = new StructuredConcurrencyPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencyPreviewStatus() {
		assertThat(notes.purpose())
				.as("The Java 21 notes should preserve the main structured-concurrency idea")
				.contains("single unit");
		assertThat(notes.previewStatus())
				.as("The notes should identify Java 21 as a preview release for structured concurrency")
				.contains("Java 21");
	}
}
