package net.jrodolfo.java_evolution.java22.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySecondPreviewNotesTest {

	private final StructuredConcurrencySecondPreviewNotes notes = new StructuredConcurrencySecondPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencySecondPreview() {
		String problemSolved = notes.problemSolved();
		String structuredUnitOfWork = notes.structuredUnitOfWork();
		String coordinationBenefits = notes.coordinationBenefits();
		String nextStep = notes.nextStep();
		String status = notes.secondPreviewStatus();

		assertThat(problemSolved)
				.as("Structured concurrency should explain grouped subtask lifetime and cancellation")
				.contains("concurrent subtasks")
				.contains("parent")
				.contains("lifetime")
				.contains("cancellation");
		assertThat(structuredUnitOfWork)
				.as("The note should describe the parent scope workflow")
				.contains("opens a scope")
				.contains("forks related subtasks")
				.contains("joins them")
				.contains("closes the scope");
		assertThat(coordinationBenefits)
				.as("The note should explain why the structure matters operationally")
				.contains("failure handling")
				.contains("cancellation")
				.contains("observability");
		assertThat(nextStep)
				.as("The Java 22 bridge note should point to the later Java 25 preview module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("still preview");
		assertThat(status)
				.as("The note should document Java 22 as the second preview")
				.contains("second preview")
				.contains("Java 22");
	}
}
