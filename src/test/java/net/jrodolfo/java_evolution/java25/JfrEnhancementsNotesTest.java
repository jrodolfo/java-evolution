package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JfrEnhancementsNotesTest {

	private final JfrEnhancementsNotes notes = new JfrEnhancementsNotes();

	@Test
	void notesIdentifyProfilingAndTracingCapabilities() {
		assertThat(notes.features())
				.as("JFR notes should identify profiling and tracing capabilities")
				.contains("CPU-time")
				.contains("method timing");
	}
}
