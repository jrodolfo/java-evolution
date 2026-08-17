package net.jrodolfo.java_evolution.java20.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySecondIncubatorNotesTest {

	private final StructuredConcurrencySecondIncubatorNotes notes = new StructuredConcurrencySecondIncubatorNotes();

	@Test
	void notesExplainStructuredConcurrencyIncubatorStatus() {
		String problemSolved = notes.problemSolved();
		String oldApproachProblem = notes.oldApproachProblem();
		String incubatorIdea = notes.incubatorIdea();
		String coordinationBenefits = notes.coordinationBenefits();
		String incubatorStatus = notes.incubatorStatus();
		String projectDecision = notes.projectDecision();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain the parent-child lifetime problem")
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
				.contains("structured unit of work")
				.contains("parent");
		assertThat(coordinationBenefits)
				.as("The note should name the operational benefits")
				.contains("joining")
				.contains("cancellation")
				.contains("failure handling")
				.contains("cleanup")
				.contains("observability");
		assertThat(incubatorStatus)
				.as("The note should identify Java 20 as the second incubator round")
				.contains("second incubator")
				.contains("Java 20");
		assertThat(projectDecision)
				.as("The note should explain why this project does not enable the old incubator API")
				.contains("incubator module")
				.contains("old incubator APIs");
		assertThat(nextStep)
				.as("The Java 20 module should point to the later Java 25 preview workflow")
				.contains("Java 25")
				.contains("structured_concurrency")
				.contains("later preview workflow");
	}
}
