package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesSecondPreviewNotesTest {

	private final FlexibleConstructorBodiesSecondPreviewNotes notes = new FlexibleConstructorBodiesSecondPreviewNotes();

	@Test
	void notesExplainConstructorValidationBeforeDelegation() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("The note should explain why constructor validation before delegation matters")
				.contains("validation")
				.contains("constructor invocation");
		assertThat(status)
				.as("The Java 23 note should document the second preview before Java 25 finalization")
				.contains("second preview in Java 23")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 runnable example")
				.contains("FlexibleConstructorBodiesExamples")
				.contains("Java 25");
	}
}
