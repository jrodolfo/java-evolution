package net.jrodolfo.java_evolution.java26.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VectorApiEleventhIncubatorNotesTest {

	private final VectorApiEleventhIncubatorNotes notes = new VectorApiEleventhIncubatorNotes();

	@Test
	void notesExplainSimdAndIncubatorStatus() {
		assertThat(notes.programmingModel())
				.as("Vector API notes should define SIMD for learners")
				.contains("Single Instruction, Multiple Data");
		assertThat(notes.goal())
				.as("Vector API notes should connect Java code to CPU vector instructions")
				.contains("JVM")
				.contains("CPU vector instructions");
		assertThat(notes.status())
				.as("Vector API should be marked as eleventh incubator in Java 26")
				.contains("eleventh incubator")
				.contains("Java 26");
	}
}
