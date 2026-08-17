package net.jrodolfo.java_evolution.java20.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesIncubatorNotesTest {

	private final ScopedValuesIncubatorNotes notes = new ScopedValuesIncubatorNotes();

	@Test
	void notesExplainScopedValuesIncubatorStatus() {
		String problemSolved = notes.problemSolved();
		String threadLocalProblem = notes.threadLocalProblem();
		String incubatorIdea = notes.incubatorIdea();
		String incubatorStatus = notes.incubatorStatus();
		String projectDecision = notes.projectDecision();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The incubator note should explain why contextual data is hard to pass manually")
				.contains("contextual data")
				.contains("request IDs")
				.contains("parameter plumbing");
		assertThat(threadLocalProblem)
				.as("The note should identify ThreadLocal as the older common mechanism and explain its cleanup risk")
				.contains("ThreadLocal")
				.contains("thread-associated state")
				.contains("clean up");
		assertThat(incubatorIdea)
				.as("The note should preserve the scoped-values mental model")
				.contains("immutable contextual data")
				.contains("bounded lifetime")
				.contains("scoped operation");
		assertThat(incubatorStatus)
				.as("The note should identify Java 20 as the incubator release")
				.contains("Java 20")
				.contains("incubating API");
		assertThat(projectDecision)
				.as("The note should explain why this project does not enable the old incubator API")
				.contains("incubator module")
				.contains("old incubator APIs");
		assertThat(nextStep)
				.as("The Java 20 module should point to the final runnable ScopedValue example")
				.contains("Java 25")
				.contains("scoped_values")
				.contains("final runnable");
	}
}
