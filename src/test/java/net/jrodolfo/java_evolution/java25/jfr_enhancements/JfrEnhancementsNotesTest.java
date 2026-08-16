package net.jrodolfo.java_evolution.java25.jfr_enhancements;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JfrEnhancementsNotesTest {

	private final JfrEnhancementsNotes notes = new JfrEnhancementsNotes();

	@Test
	void notesFrameJfrAsRuntimeObservability() {
		assertThat(notes.problem())
				.as("JFR should be introduced as runtime evidence for performance and behavior questions")
				.contains("runtime evidence")
				.contains("performance");
		assertThat(notes.projectDecision())
				.as("The notes should explain why JFR is not a normal deterministic unit-test example")
				.contains("running application")
				.contains("recording")
				.contains("analysis");
	}

	@Test
	void notesIdentifyTheJava25JfrEnhancements() {
		assertThat(notes.features())
				.as("JFR notes should identify the Java 25 profiling, sampling, and tracing capabilities")
				.contains("CPU-time")
				.contains("cooperative sampling")
				.contains("method timing");
		assertThat(notes.cpuTimeProfiling())
				.as("CPU-time profiling should be marked as experimental and Linux-specific")
				.contains("experimental")
				.contains("Linux")
				.contains("CPU");
		assertThat(notes.cooperativeSampling())
				.as("Cooperative sampling should be tied to safer stack sampling")
				.contains("stack-sampling stability")
				.contains("safepoints");
		assertThat(notes.methodTimingAndTracing())
				.as("Method timing and tracing should be tied to selected methods")
				.contains("selected methods")
				.contains("stack information");
	}
}
