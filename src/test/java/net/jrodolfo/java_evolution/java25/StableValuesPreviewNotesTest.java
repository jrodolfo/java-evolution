package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StableValuesPreviewNotesTest {

	private final StableValuesPreviewNotes notes = new StableValuesPreviewNotes();

	@Test
	void notesExplainInitializedOnceModel() {
		assertThat(notes.purpose())
				.as("Stable values should explain the initialized-once model")
				.contains("initialized at most once");
	}
}
