package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyPreviewNotesTest {

	private final StructuredConcurrencyPreviewNotes notes = new StructuredConcurrencyPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencyConcept() {
		// When / Then
		assertThat(notes.purpose())
				.as("The notes should describe related tasks as a unit")
				.contains("structured unit of work");
		assertThat(notes.benefit())
				.as("The notes should explain cancellation and failure benefits")
				.contains("cancellation")
				.contains("failure");
		assertThat(notes.projectDecision())
				.as("The notes should explain why no incubator module is enabled")
				.contains("incubating")
				.contains("without enabling incubator modules");
	}
}
