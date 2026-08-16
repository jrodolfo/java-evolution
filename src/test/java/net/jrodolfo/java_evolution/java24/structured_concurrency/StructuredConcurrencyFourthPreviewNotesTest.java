package net.jrodolfo.java_evolution.java24.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyFourthPreviewNotesTest {

	private final StructuredConcurrencyFourthPreviewNotes notes = new StructuredConcurrencyFourthPreviewNotes();

	@Test
	void notesKeepStructuredConcurrencyAsPreviewInJava25() {
		String problemSolved = notes.problemSolved();
		String structuredUnitOfWork = notes.structuredUnitOfWork();
		String previewStep = notes.previewStep();
		String status = notes.fourthPreviewStatus();
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
				.as("The Java 24 note should explain its place in the preview sequence")
				.contains("fourth preview")
				.contains("Java 23")
				.contains("Java 25 fifth preview");
		assertThat(status)
				.as("The note should document Java 24 as the fourth preview")
				.contains("fourth preview in Java 24")
				.contains("structured concurrency");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
	}
}
