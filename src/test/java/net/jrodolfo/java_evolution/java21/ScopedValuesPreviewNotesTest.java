package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesPreviewNotesTest {

	private final ScopedValuesPreviewNotes notes = new ScopedValuesPreviewNotes();

	@Test
	void notesExplainScopedValuesPreviewStatus() {
		assertThat(notes.purpose()).contains("immutable data");
		assertThat(notes.previewStatus()).contains("Java 21");
	}
}
