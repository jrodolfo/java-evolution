package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersSecondPreviewNotesTest {

	private final StreamGatherersSecondPreviewNotes notes = new StreamGatherersSecondPreviewNotes();

	@Test
	void notesKeepStreamGatherersAsPreviewBeforeJava24Finalization() {
		assertThat(notes.purpose())
				.as("Stream gatherers should explain custom stream transformations")
				.contains("stream");
		assertThat(notes.status())
				.as("Gatherers should be documented as a Java 23 preview, not a final Java 23 API")
				.contains("final in Java 24");
	}
}
