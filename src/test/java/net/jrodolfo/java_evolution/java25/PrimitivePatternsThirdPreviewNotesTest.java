package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsThirdPreviewNotesTest {

	private final PrimitivePatternsThirdPreviewNotes notes = new PrimitivePatternsThirdPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsClearlyLabeledAsPreview() {
		assertThat(notes.purpose())
				.as("Primitive patterns should be framed as uniform pattern matching")
				.contains("uniform")
				.contains("primitive types")
				.contains("pattern contexts");
		assertThat(notes.safetyGoal())
				.as("Primitive patterns should explain safe conversion before binding")
				.contains("conversion is safe")
				.contains("binding");
		assertThat(notes.status())
				.as("Primitive patterns should not be presented as final in Java 25")
				.contains("third preview")
				.contains("--enable-preview");
	}
}
