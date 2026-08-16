package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyFourthPreviewNotesTest {

	private final StructuredConcurrencyFourthPreviewNotes notes = new StructuredConcurrencyFourthPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyAsPreviewInJava25() {
		String featureGoal = notes.featureGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(featureGoal)
				.as("The note should explain the structured concurrency goal")
				.contains("concurrent subtasks")
				.contains("unit of work");
		assertThat(status)
				.as("Structured concurrency should stay marked as preview in Java 25")
				.contains("fourth preview in Java 24")
				.contains("fifth preview");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
	}
}
