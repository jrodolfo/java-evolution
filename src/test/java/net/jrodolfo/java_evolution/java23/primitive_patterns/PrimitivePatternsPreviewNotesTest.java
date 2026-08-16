package net.jrodolfo.java_evolution.java23.primitive_patterns;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsPreviewNotesTest {

	private final PrimitivePatternsPreviewNotes notes = new PrimitivePatternsPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		String problemSolved = notes.problemSolved();
		String whatJavaPreviewed = notes.whatJavaPreviewed();
		String languageDirection = notes.languageDirection();
		String safetyGoal = notes.safetyGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Primitive patterns should explain the gap between primitive and reference pattern matching")
				.contains("primitive values")
				.contains("pattern matching")
				.contains("reference values");
		assertThat(whatJavaPreviewed)
				.as("The note should identify the actual Java 23 preview surface")
				.contains("primitive values")
				.contains("instanceof")
				.contains("switch");
		assertThat(languageDirection)
				.as("The note should explain the broader pattern-matching direction")
				.contains("uniform pattern model")
				.contains("reference")
				.contains("primitive");
		assertThat(safetyGoal)
				.as("The note should explain why safe primitive conversion matters")
				.contains("primitive conversion")
				.contains("safe")
				.contains("binding");
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
