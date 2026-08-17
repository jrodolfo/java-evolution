package net.jrodolfo.java_evolution.java20.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VectorApiFifthIncubatorNotesTest {

	private final VectorApiFifthIncubatorNotes notes = new VectorApiFifthIncubatorNotes();

	@Test
	void notesExplainVectorApiIncubatorStatus() {
		String problemSolved = notes.problemSolved();
		String oldApproachProblem = notes.oldApproachProblem();
		String incubatorIdea = notes.incubatorIdea();
		String realUseCases = notes.realUseCases();
		String incubatorStatus = notes.incubatorStatus();
		String projectDecision = notes.projectDecision();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain the scalar-loop problem")
				.contains("many values")
				.contains("scalar value");
		assertThat(oldApproachProblem)
				.as("The note should name the older indirect/native approaches")
				.contains("JIT auto-vectorization")
				.contains("native libraries")
				.contains("CPU vector instructions");
		assertThat(incubatorIdea)
				.as("The note should preserve the direct SIMD-style Java API idea")
				.contains("Vector API")
				.contains("SIMD")
				.contains("lane-wise");
		assertThat(realUseCases)
				.as("The note should ground the feature in realistic performance-sensitive domains")
				.contains("image processing")
				.contains("audio processing")
				.contains("machine-learning");
		assertThat(incubatorStatus)
				.as("The note should identify Java 20 as the fifth incubator round")
				.contains("fifth incubator")
				.contains("Java 20");
		assertThat(projectDecision)
				.as("The note should explain why this project avoids the incubator module")
				.contains("jdk.incubator.vector")
				.contains("explanatory");
		assertThat(nextStep)
				.as("The Java 20 module should point to the later Java 25 Vector API guide")
				.contains("Java 25")
				.contains("vector_api")
				.contains("later incubator");
	}
}
