package net.jrodolfo.java_evolution.java24.flexible_constructor_bodies;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesThirdPreviewNotesTest {

	private final FlexibleConstructorBodiesThirdPreviewNotes notes = new FlexibleConstructorBodiesThirdPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String problemSolved = notes.problemSolved();
		String oldConstructorRule = notes.oldConstructorRule();
		String flexibleConstructorModel = notes.flexibleConstructorModel();
		String safetyRule = notes.safetyRule();
		String previewStep = notes.previewStep();
		String status = notes.thirdPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain why constructor bodies changed")
				.contains("validation")
				.contains("normalization")
				.contains("delegating");
		assertThat(oldConstructorRule)
				.as("The note should explain the older first-statement rule")
				.contains("this(...)")
				.contains("super(...)")
				.contains("first statement");
		assertThat(flexibleConstructorModel)
				.as("The note should explain safe preparation before delegation")
				.contains("safe preparation")
				.contains("constructor delegation");
		assertThat(safetyRule)
				.as("The note should preserve the object-under-construction safety boundary")
				.contains("object under construction")
				.contains("properly initialized");
		assertThat(previewStep)
				.as("The note should explain Java 24's place in the preview sequence")
				.contains("third preview")
				.contains("Java 23")
				.contains("Java 25");
		assertThat(status)
				.as("Flexible constructor bodies should point to Java 25 finalization")
				.contains("third preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the final runnable Java 25 example")
				.contains("FlexibleConstructorBodiesExamples")
				.contains("Java 25");
	}
}
