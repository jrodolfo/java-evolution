package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StatementsBeforeSuperPreviewNotesTest {

	private final StatementsBeforeSuperPreviewNotes notes = new StatementsBeforeSuperPreviewNotes();

	@Test
	void notesExplainStatementsBeforeSuperPreview() {
		String purpose = notes.purpose();
		String limitation = notes.limitation();
		String nextStep = notes.nextStep();
		String projectDecision = notes.projectDecision();

		assertThat(purpose)
				.as("The note should explain validation or preparation before constructor delegation")
				.contains("before calling")
				.contains("superclass constructor");
		assertThat(limitation)
				.as("The note should preserve the constructor initialization safety rule")
				.contains("cannot use the instance");
		assertThat(nextStep)
				.as("The Java 22 bridge note should point to the final Java 25 constructor feature")
				.contains("FlexibleConstructorBodiesExamples")
				.contains("Java 25");
		assertThat(projectDecision)
				.as("The note should explain why this package avoids preview-flag complexity")
				.contains("preview-flag");
	}
}
