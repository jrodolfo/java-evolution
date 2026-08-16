package net.jrodolfo.java_evolution.java25.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VectorApiTenthIncubatorNotesTest {

	private final VectorApiTenthIncubatorNotes notes = new VectorApiTenthIncubatorNotes();

	@Test
	void notesExplainScalarLoopsAndVectorComputation() {
		assertThat(notes.problem())
				.as("The Vector API should be introduced as a response to scalar one-value-at-a-time loops")
				.contains("scalar loops")
				.contains("one value at a time");
		assertThat(notes.java25Idea())
				.as("The notes should explain lane-wise vector computation and JVM compilation")
				.contains("lane-wise")
				.contains("JVM")
				.contains("CPU vector instructions");
	}

	@Test
	void notesExplainSimdTerminologyAndIncubatorStatus() {
		assertThat(notes.terminology())
				.as("The notes should define SIMD for learners")
				.contains("Single Instruction, Multiple Data")
				.contains("vector lanes");
		assertThat(notes.projectDecision())
				.as("The notes should explain why the incubator module is not compiled in the normal build")
				.contains("incubating")
				.contains("jdk.incubator.vector");
		assertThat(notes.status())
				.as("Vector API should stay marked as incubating")
				.contains("tenth incubator");
	}
}
