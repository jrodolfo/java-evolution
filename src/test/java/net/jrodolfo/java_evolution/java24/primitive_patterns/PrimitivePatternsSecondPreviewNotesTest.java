package net.jrodolfo.java_evolution.java24.primitive_patterns;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsSecondPreviewNotesTest {

	private final PrimitivePatternsSecondPreviewNotes notes = new PrimitivePatternsSecondPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		String problemSolved = notes.problemSolved();
		String olderModel = notes.olderModel();
		String primitivePatternModel = notes.primitivePatternModel();
		String safetyGoal = notes.safetyGoal();
		String previewStep = notes.previewStep();
		String status = notes.secondPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain the gap between primitives and reference values")
				.contains("primitive values")
				.contains("pattern matching")
				.contains("reference values");
		assertThat(olderModel)
				.as("The note should explain the older split in Java's model")
				.contains("reference values")
				.contains("primitive values")
				.contains("checks")
				.contains("conversions");
		assertThat(primitivePatternModel)
				.as("The note should name the pattern contexts affected by the preview")
				.contains("pattern matching")
				.contains("instanceof")
				.contains("switch");
		assertThat(safetyGoal)
				.as("The note should explain why safe primitive conversion matters")
				.contains("safe")
				.contains("primitive conversion")
				.contains("binding");
		assertThat(previewStep)
				.as("The note should explain Java 24's place in the preview sequence")
				.contains("second preview")
				.contains("Java 23")
				.contains("Java 25 third preview");
		assertThat(status)
				.as("Primitive patterns should still be preview after Java 24")
				.contains("second preview in Java 24")
				.contains("third preview");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the next Java 25 preview")
				.contains("PrimitivePatternsThirdPreviewExamples")
				.contains("still preview");
	}
}
