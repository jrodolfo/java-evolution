package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForeignFunctionMemorySecondPreviewNotesTest {

	private final ForeignFunctionMemorySecondPreviewNotes notes = new ForeignFunctionMemorySecondPreviewNotes();

	@Test
	void notesExplainFfmPreviewStatus() {
		String purpose = notes.purpose();
		String oldApproachProblem = notes.oldApproachProblem();
		String java20Status = notes.java20Status();
		String projectDecision = notes.projectDecision();
		String finalRelease = notes.finalRelease();

		assertThat(purpose)
				.as("The note should explain the native interop goal")
				.contains("native functions")
				.contains("outside the Java heap")
				.contains("supported APIs");
		assertThat(oldApproachProblem)
				.as("The note should identify JNI as the older difficult approach")
				.contains("JNI")
				.contains("safely")
				.contains("portably");
		assertThat(java20Status)
				.as("The note should identify Java 20 as preview history")
				.contains("second preview")
				.contains("Java 20");
		assertThat(projectDecision)
				.as("The note should point learners to the final executable Java 22 example")
				.contains("Java 22")
				.contains("final executable")
				.contains("FFM example");
		assertThat(finalRelease)
				.as("The note should name the final release")
				.contains("Java 22");
	}
}
