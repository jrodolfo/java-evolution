package net.jrodolfo.java_evolution.java23.stream_gatherers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersSecondPreviewNotesTest {

	private final StreamGatherersSecondPreviewNotes notes = new StreamGatherersSecondPreviewNotes();

	@Test
	void notesKeepStreamGatherersAsPreviewBeforeJava24Finalization() {
		String problemSolved = notes.problemSolved();
		String java22Connection = notes.java22Connection();
		String java23Status = notes.java23Status();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Stream gatherers should explain custom stream transformations")
				.contains("custom intermediate stream operations")
				.contains("windowing")
				.contains("scanning")
				.contains("batching");
		assertThat(java22Connection)
				.as("The bridge note should connect Java 23 back to the Java 22 first preview")
				.contains("Java 22")
				.contains("first preview");
		assertThat(java23Status)
				.as("The note should document Java 23 as the second preview")
				.contains("Java 23")
				.contains("second preview");
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
