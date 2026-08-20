package net.jrodolfo.java_evolution.java02.strict_floating_point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StrictFloatingPointNotesTest {

	private final StrictFloatingPointNotes notes = new StrictFloatingPointNotes();

	@Test
	void notesExplainStrictfpHistoryAndModernContext() {
		assertThat(notes.problemSolved()).contains("floating-point").contains("intermediate precision");
		assertThat(notes.featureShape()).contains("strictfp").contains("classes").contains("methods");
		assertThat(notes.modernContext()).contains("Java 17").contains("JDK 25");
	}
}
