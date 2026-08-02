package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersPreviewNotesTest {

	private final StreamGatherersPreviewNotes notes = new StreamGatherersPreviewNotes();

	@Test
	void notesExplainStreamGatherersPreview() {
		assertThat(notes.purpose()).contains("custom intermediate stream operations");
		assertThat(notes.exampleUseCase()).contains("windowing").contains("scanning");
		assertThat(notes.projectDecision()).contains("preview");
	}
}
