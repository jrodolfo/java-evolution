package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesThirdPreviewNotesTest {

	private final FlexibleConstructorBodiesThirdPreviewNotes notes = new FlexibleConstructorBodiesThirdPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String featureGoal = notes.featureGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(featureGoal)
				.as("The note should explain why constructor bodies changed")
				.contains("validation")
				.contains("constructor delegation");
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
