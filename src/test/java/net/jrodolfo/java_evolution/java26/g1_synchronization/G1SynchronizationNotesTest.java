package net.jrodolfo.java_evolution.java26.g1_synchronization;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class G1SynchronizationNotesTest {

	private final G1SynchronizationNotes notes = new G1SynchronizationNotes();

	@Test
	void notesExplainG1RuntimeThroughputChange() {
		assertThat(notes.definition())
				.as("G1 should be defined for learners before discussing internal synchronization")
				.contains("Garbage-First")
				.contains("garbage collector");
		assertThat(notes.improvement())
				.as("The Java 26 improvement should mention throughput and synchronization")
				.contains("throughput")
				.contains("synchronization");
		assertThat(notes.projectDecision())
				.as("GC improvements should not be represented as tiny deterministic unit tests")
				.contains("runtime behavior")
				.contains("workloads");
	}
}
