package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsPreviewNotesTest {

	private final PrimitivePatternsPreviewNotes notes = new PrimitivePatternsPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		assertThat(notes.purpose())
				.as("Primitive patterns should explain matching primitive values directly")
				.contains("primitive");
		assertThat(notes.status())
				.as("The note should prevent learners from treating Java 23 primitive patterns as final")
				.contains("preview in Java 23")
				.contains("still preview in Java 25");
	}
}
