package net.jrodolfo.java_evolution.java22.statements_before_super;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StatementsBeforeSuperPreviewNotesTest {

	private final StatementsBeforeSuperPreviewNotes notes = new StatementsBeforeSuperPreviewNotes();

	@Test
	void notesExplainWhyStatementsBeforeSuperWerePreviewed() {
		String oldRule = notes.oldRule();
		String problemSolved = notes.problemSolved();
		String safetyRule = notes.safetyRule();
		String nextStep = notes.nextStep();
		String projectDecision = notes.projectDecision();

		assertThat(oldRule)
				.as("The note should explain the old first-statement constructor rule")
				.contains("super")
				.contains("this")
				.contains("first constructor statement");
		assertThat(problemSolved)
				.as("The note should explain why validation or preparation before delegation matters")
				.contains("validation")
				.contains("preparation")
				.contains("before delegation");
		assertThat(safetyRule)
				.as("The note should preserve the constructor initialization safety rule")
				.contains("cannot use the instance")
				.contains("being constructed");
		assertThat(nextStep)
				.as("The Java 22 bridge note should point to the final Java 25 constructor feature")
				.contains("FlexibleConstructorBodiesExamples")
				.contains("Java 25");
		assertThat(projectDecision)
				.as("The note should explain why this package avoids preview-flag complexity")
				.contains("Java 22")
				.contains("preview")
				.contains("preview-flag");
	}
}
