package net.jrodolfo.java_evolution.java23.zgc_generational_mode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ZgcGenerationalModeNotesTest {

	private final ZgcGenerationalModeNotes notes = new ZgcGenerationalModeNotes();

	@Test
	void notesExplainZgcGenerationalDefaultRuntimeChange() {
		String garbageCollectionGoal = notes.garbageCollectionGoal();
		String generationalObservation = notes.generationalObservation();
		String java23Change = notes.java23Change();
		String benefit = notes.benefit();
		String projectDecision = notes.projectDecision();

		assertThat(garbageCollectionGoal)
				.as("The ZGC note should explain garbage collection before explaining the default change")
				.contains("reclaims memory")
				.contains("objects");
		assertThat(generationalObservation)
				.as("The note should explain why generational garbage collection exists")
				.contains("die young")
				.contains("young and old objects");
		assertThat(java23Change)
				.as("The note should document the Java 23 runtime default change")
				.contains("Java 23")
				.contains("ZGC")
				.contains("generational mode by default");
		assertThat(benefit)
				.as("The note should explain the expected GC benefit")
				.contains("garbage collection efficiency");
		assertThat(projectDecision)
				.as("The note should explain why there is no normal unit-test style example")
				.contains("runtime behavior")
				.contains("unit-test");
	}
}
