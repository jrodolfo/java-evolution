package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesPreviewNotesTest {

	private final ScopedValuesPreviewNotes notes = new ScopedValuesPreviewNotes();

	@Test
	void notesExplainScopedValuesPreviewStatus() {
		assertThat(notes.purpose())
				.as("The Java 21 notes should preserve the main scoped-values idea")
				.contains("immutable data");
		assertThat(notes.previewStatus())
				.as("The notes should identify Java 21 as a preview release for scoped values")
				.contains("Java 21");
	}
}
