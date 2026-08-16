package net.jrodolfo.java_evolution.java23.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyThirdPreviewNotesTest {

	private final StructuredConcurrencyThirdPreviewNotes notes = new StructuredConcurrencyThirdPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyMarkedAsEvolving() {
		String problemSolved = notes.problemSolved();
		String structuredUnitOfWork = notes.structuredUnitOfWork();
		String previewStep = notes.previewStep();
		String status = notes.thirdPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Structured concurrency should explain parent ownership of related tasks")
				.contains("concurrent subtasks")
				.contains("parent scope")
				.contains("cancellation")
				.contains("failure handling");
		assertThat(structuredUnitOfWork)
				.as("The note should describe the parent scope workflow")
				.contains("opens a scope")
				.contains("forks related subtasks")
				.contains("joins them")
				.contains("closes the scope");
		assertThat(previewStep)
				.as("The Java 23 note should explain its place in the preview sequence")
				.contains("third preview")
				.contains("Java 22 second preview");
		assertThat(status)
				.as("The note should document Java 23 as the third preview")
				.contains("third preview in Java 23")
				.contains("structured concurrency");
		assertThat(nextStep)
				.as("The bridge note should point learners to the Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
	}
}
