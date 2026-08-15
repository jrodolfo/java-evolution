package net.jrodolfo.java_evolution.java25.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyFifthPreviewNotesTest {

	private final StructuredConcurrencyFifthPreviewNotes notes = new StructuredConcurrencyFifthPreviewNotes();

	@Test
	void notesExplainScatteredSubtaskProblem() {
		assertThat(notes.problem())
				.as("Structured concurrency should explain the scattered-subtask problem")
				.contains("started")
				.contains("joined")
				.contains("cancelled");
	}

	@Test
	void notesExplainCommonAlternativeAndPreviewStatus() {
		assertThat(notes.commonAlternative())
				.as("The notes should name common pre-structured-concurrency tools")
				.contains("ExecutorService")
				.contains("Future");
		assertThat(notes.java25Idea())
				.as("The notes should frame StructuredTaskScope as one unit of related work")
				.contains("StructuredTaskScope")
				.contains("unit of work");
		assertThat(notes.status())
				.as("Structured concurrency should stay marked as fifth preview")
				.contains("fifth preview")
				.contains("--enable-preview");
	}
}
