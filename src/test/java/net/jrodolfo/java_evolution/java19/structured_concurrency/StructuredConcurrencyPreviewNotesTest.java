package net.jrodolfo.java_evolution.java19.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencyPreviewNotesTest {

	private final StructuredConcurrencyPreviewNotes notes = new StructuredConcurrencyPreviewNotes();

	@Test
	void notesExplainStructuredConcurrencyConcept() {
		String problemSolved = notes.problemSolved();
		String oldApproachProblem = notes.oldApproachProblem();
		String incubatorIdea = notes.incubatorIdea();
		String coordinationBenefits = notes.coordinationBenefits();
		String officialStatus = notes.officialStatus();
		String projectDecision = notes.projectDecision();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The first incubator note should explain the parent-child task lifetime problem")
				.contains("parent operation")
				.contains("lifetime boundary")
				.contains("child tasks");
		assertThat(oldApproachProblem)
				.as("The note should identify the older manual coordination problem")
				.contains("ExecutorService")
				.contains("Future")
				.contains("manual coordination")
				.contains("failure handling");
		assertThat(incubatorIdea)
				.as("The note should preserve the structured unit of work idea")
				.contains("related subtasks")
				.contains("structured unit of work");
		assertThat(coordinationBenefits)
				.as("The notes should explain cancellation, failure, cleanup, and observability benefits")
				.contains("joining")
				.contains("cancellation")
				.contains("failure handling")
				.contains("cleanup")
				.contains("observability");
		assertThat(officialStatus)
				.as("The note should clarify that Java 19 used incubator status")
				.contains("Java 19")
				.contains("incubating API")
				.contains("not as a preview feature");
		assertThat(projectDecision)
				.as("The notes should explain why no incubator module is enabled")
				.contains("incubator module")
				.contains("old incubator APIs");
		assertThat(nextStep)
				.as("The Java 19 module should point to the later structured-concurrency modules")
				.contains("Java 20")
				.contains("Java 25")
				.contains("structured_concurrency");
	}
}
