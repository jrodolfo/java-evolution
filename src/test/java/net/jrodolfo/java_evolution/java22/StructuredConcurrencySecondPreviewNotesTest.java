package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySecondPreviewNotesTest {

	private final StructuredConcurrencySecondPreviewNotes notes = new StructuredConcurrencySecondPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencySecondPreview() {
		String purpose = notes.purpose();
		String nextStep = notes.nextStep();
		String status = notes.secondPreviewStatus();

		assertThat(purpose)
				.as("Structured concurrency should explain grouped subtask lifetime and cancellation")
				.contains("concurrent subtasks")
				.contains("cancellation");
		assertThat(nextStep)
				.as("The Java 22 bridge note should point to the later Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
		assertThat(status)
				.as("The note should document Java 22 as the second preview")
				.contains("second preview")
				.contains("Java 22");
	}
}
