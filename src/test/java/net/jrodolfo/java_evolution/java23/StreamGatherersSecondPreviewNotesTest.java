package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersSecondPreviewNotesTest {

	private final StreamGatherersSecondPreviewNotes notes = new StreamGatherersSecondPreviewNotes();

	@Test
	void notesKeepStreamGatherersAsPreviewBeforeJava24Finalization() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("Stream gatherers should explain custom stream transformations")
				.contains("custom intermediate stream operations");
		assertThat(status)
				.as("Gatherers should be documented as a Java 23 preview, not a final Java 23 API")
				.contains("second preview in Java 23")
				.contains("final in Java 24");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 24 runnable example")
				.contains("StreamGatherersExamples")
				.contains("Java 24");
	}
}
