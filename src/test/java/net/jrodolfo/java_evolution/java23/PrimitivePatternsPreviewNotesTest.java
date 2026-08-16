package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsPreviewNotesTest {

	private final PrimitivePatternsPreviewNotes notes = new PrimitivePatternsPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("Primitive patterns should explain matching primitive values directly")
				.contains("primitive values")
				.contains("switch");
		assertThat(status)
				.as("The note should prevent learners from treating Java 23 primitive patterns as final")
				.contains("preview in Java 23")
				.contains("still preview in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners through the Java 24 and Java 25 previews")
				.contains("PrimitivePatternsSecondPreviewNotes")
				.contains("PrimitivePatternsThirdPreviewNotes")
				.contains("still preview");
	}
}
