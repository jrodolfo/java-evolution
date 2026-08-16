package net.jrodolfo.java_evolution.java22.stream_gatherers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamGatherersPreviewNotesTest {

	private final StreamGatherersPreviewNotes notes = new StreamGatherersPreviewNotes();

	@Test
	void notesExplainWhyStreamGatherersWerePreviewed() {
		String problemSolved = notes.problemSolved();
		String whatJavaIntroduced = notes.whatJavaIntroduced();
		String exampleUseCases = notes.exampleUseCases();
		String previewStatus = notes.previewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain why ordinary stream operations were not always enough")
				.contains("buffering")
				.contains("state")
				.contains("custom emission");
		assertThat(whatJavaIntroduced)
				.as("The note should identify gatherers as custom intermediate stream operations")
				.contains("standard extension point")
				.contains("custom intermediate stream operations");
		assertThat(exampleUseCases)
				.as("The note should name transformations where output depends on more than one simple input mapping")
				.contains("fixed windows")
				.contains("running scans")
				.contains("stateful transformations");
		assertThat(previewStatus)
				.as("The Java 22 note should be explicit that gatherers were still preview")
				.contains("preview")
				.contains("Java 22")
				.contains("final in Java 24");
		assertThat(nextStep)
				.as("The preview module should send learners to the final runnable Java 24 example")
				.contains("StreamGatherersExamples")
				.contains("Java 24")
				.contains("windowFixed")
				.contains("scan");
	}
}
