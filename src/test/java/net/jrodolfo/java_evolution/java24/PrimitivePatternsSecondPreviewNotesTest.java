package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsSecondPreviewNotesTest {

	private final PrimitivePatternsSecondPreviewNotes notes = new PrimitivePatternsSecondPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		String featureGoal = notes.featureGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(featureGoal)
				.as("The note should explain the primitive pattern-matching goal")
				.contains("primitive values")
				.contains("pattern matching");
		assertThat(status)
				.as("Primitive patterns should still be preview after Java 24")
				.contains("second preview in Java 24")
				.contains("third preview");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the next Java 25 preview")
				.contains("PrimitivePatternsThirdPreviewNotes")
				.contains("still preview");
	}
}
