package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsFourthPreviewNotesTest {

	private final PrimitivePatternsFourthPreviewNotes notes = new PrimitivePatternsFourthPreviewNotes();

	@Test
	void notesExplainPrimitivePatternMatchingPreview() {
		assertThat(notes.problem())
				.as("Primitive patterns should be introduced from the pre-existing primitive handling problem")
				.contains("range checks")
				.contains("casts");
		assertThat(notes.idea())
				.as("The notes should explain safe conversion and binding")
				.contains("safe conversion")
				.contains("instanceof")
				.contains("switch");
		assertThat(notes.status())
				.as("Primitive patterns should stay marked as fourth preview in Java 26")
				.contains("fourth preview")
				.contains("Java 26");
	}
}
