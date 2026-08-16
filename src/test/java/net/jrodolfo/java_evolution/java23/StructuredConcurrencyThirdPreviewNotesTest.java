package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyThirdPreviewNotesTest {

	private final StructuredConcurrencyThirdPreviewNotes notes = new StructuredConcurrencyThirdPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyMarkedAsEvolving() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("Structured concurrency should explain grouping related tasks")
				.contains("concurrent subtasks")
				.contains("unit of work");
		assertThat(status)
				.as("Structured concurrency should remain marked as preview in this release range")
				.contains("third preview in Java 23")
				.contains("fifth preview");
		assertThat(nextStep)
				.as("The bridge note should point learners to the Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
	}
}
