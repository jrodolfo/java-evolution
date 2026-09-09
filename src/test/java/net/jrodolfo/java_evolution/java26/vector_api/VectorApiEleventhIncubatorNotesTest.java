package net.jrodolfo.java_evolution.java26.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class VectorApiEleventhIncubatorNotesTest {

	private final VectorApiEleventhIncubatorNotes notes = new VectorApiEleventhIncubatorNotes();

	@Test
	void childProcessRunsVectorApiArrayAddition(@TempDir Path workspace) throws Exception {
		VectorApiEleventhIncubatorNotes.VectorWorkflowResult result = notes.runVectorWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile with the Java 26 incubator vector module")
				.isZero();
		assertThat(result.execution().exitCode())
				.as("the child JVM should run with the Java 26 incubator vector module")
				.isZero();
		assertThat(result.execution().output())
				.as("the vector operation should report its computed result")
				.contains("result=[11, 22, 33, 44, 55, 66, 77, 88, 99, 110]");
	}

	@Test
	void probeSourceUsesTheRealIncubatorVectorApi() {
		assertThat(notes.probeSource())
				.as("the child source should demonstrate the Java 26 incubator Vector API directly")
				.contains("jdk.incubator.vector.IntVector")
				.contains("jdk.incubator.vector.VectorSpecies")
				.contains("IntVector.SPECIES_PREFERRED")
				.contains("IntVector.fromArray")
				.contains(".add(")
				.contains("intoArray");
	}

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
