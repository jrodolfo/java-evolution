package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersPreviewNotesTest {

	private final StreamGatherersPreviewNotes notes = new StreamGatherersPreviewNotes();

	@Test
	void notesExplainStreamGatherersPreview() {
		assertThat(notes.purpose())
				.as("The note should explain the problem stream gatherers solve")
				.contains("custom intermediate stream operations");
		assertThat(notes.exampleUseCase())
				.as("The note should name examples that were awkward with only built-in stream operations")
				.contains("windowing")
				.contains("scanning")
				.contains("transformations");
		assertThat(notes.projectDecision())
				.as("The note should explain why Java 22 keeps this as documentation instead of final runnable syntax")
				.contains("Java 22")
				.contains("preview")
				.contains("notes");
	}
}
