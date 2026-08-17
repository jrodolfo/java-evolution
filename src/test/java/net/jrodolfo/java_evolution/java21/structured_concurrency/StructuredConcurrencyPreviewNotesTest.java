package net.jrodolfo.java_evolution.java21.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyPreviewNotesTest {

	private final StructuredConcurrencyPreviewNotes notes = new StructuredConcurrencyPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencyPreviewStatus() {
		String problemSolved = notes.problemSolved();
		String oldApproachProblem = notes.oldApproachProblem();
		String previewIdea = notes.previewIdea();
		String coordinationBenefits = notes.coordinationBenefits();
		String status = notes.firstPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Structured concurrency should be introduced through related child-task lifetime")
				.contains("related concurrent subtasks")
				.contains("parent operation")
				.contains("lifetime boundary");
		assertThat(oldApproachProblem)
				.as("The first-preview note should explain why manual coordination was difficult")
				.contains("executors")
				.contains("futures")
				.contains("manual coordination")
				.contains("outlive the parent operation");
		assertThat(previewIdea)
				.as("The note should preserve the scope/fork/join/close mental model")
				.contains("open a scope")
				.contains("fork related subtasks")
				.contains("join them as a group")
				.contains("close the scope");
		assertThat(coordinationBenefits)
				.as("The note should explain the operational benefits of one structured unit")
				.contains("cancellation")
				.contains("failure handling")
				.contains("cleanup")
				.contains("observability")
				.contains("structured unit of work");
		assertThat(status)
				.as("The notes should identify Java 21 as the first preview release for structured concurrency")
				.contains("first previewed")
				.contains("Java 21");
		assertThat(nextStep)
				.as("The first-preview bridge should point to the later Java 25 module")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("later preview workflow");
	}
}
