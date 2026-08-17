package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VirtualThreadsSecondPreviewNotesTest {

	private final VirtualThreadsSecondPreviewNotes notes = new VirtualThreadsSecondPreviewNotes();

	@Test
	void notesExplainVirtualThreadsPreviewStatus() {
		String purpose = notes.purpose();
		String previewStep = notes.previewStep();
		String projectDecision = notes.projectDecision();
		String finalRelease = notes.finalRelease();

		assertThat(purpose)
				.as("The note should preserve the blocking thread-per-task scalability goal")
				.contains("lightweight threads")
				.contains("blocking-style code");
		assertThat(previewStep)
				.as("The note should explain Java 20 as the second preview step")
				.contains("Java 20")
				.contains("second preview")
				.contains("before finalization");
		assertThat(projectDecision)
				.as("The note should direct learners to the final executable Java 21 example")
				.contains("Java 21")
				.contains("final executable")
				.contains("virtual-thread example");
		assertThat(finalRelease)
				.as("The note should name the final release")
				.contains("Java 21");
	}
}
