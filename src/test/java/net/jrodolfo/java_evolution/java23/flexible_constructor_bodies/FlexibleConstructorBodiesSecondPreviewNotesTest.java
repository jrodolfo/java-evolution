package net.jrodolfo.java_evolution.java23.flexible_constructor_bodies;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesSecondPreviewNotesTest {

	private final FlexibleConstructorBodiesSecondPreviewNotes notes = new FlexibleConstructorBodiesSecondPreviewNotes();

	@Test
	void notesExplainConstructorValidationBeforeDelegation() {
		String problemSolved = notes.problemSolved();
		String java22Connection = notes.java22Connection();
		String safetyRule = notes.safetyRule();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain why constructor validation before delegation matters")
				.contains("validation")
				.contains("preparation")
				.contains("explicit constructor invocation");
		assertThat(java22Connection)
				.as("The note should connect Java 23 flexible constructor bodies to the Java 22 preview")
				.contains("Java 22")
				.contains("statements before super")
				.contains("Java 23");
		assertThat(safetyRule)
				.as("The note should preserve the constructor initialization safety rule")
				.contains("before delegation")
				.contains("partially constructed object")
				.contains("unsafely");
		assertThat(status)
				.as("The Java 23 note should document the preview path before Java 25 finalization")
				.contains("second preview in Java 23")
				.contains("third preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 runnable example")
				.contains("FlexibleConstructorBodiesExamples")
				.contains("Java 25");
	}
}
