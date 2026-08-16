package net.jrodolfo.java_evolution.java25.generational_shenandoah;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenerationalShenandoahNotesTest {

	private final GenerationalShenandoahNotes notes = new GenerationalShenandoahNotes();

	@Test
	void notesExplainGarbageCollectionAndShenandoahGoal() {
		assertThat(notes.garbageCollectionProblem())
				.as("Generational Shenandoah should start from the garbage-collection problem")
				.contains("heap memory")
				.contains("no longer reachable");

		assertThat(notes.shenandoahGoal())
				.as("Shenandoah should be introduced as a low-pause garbage collector")
				.contains("low-pause")
				.contains("concurrently");
	}

	@Test
	void notesExplainGenerationalStatusAndRuntimeOption() {
		assertThat(notes.generationalIdea())
				.as("The notes should explain the generational hypothesis")
				.contains("many objects die young");

		assertThat(notes.java25Status())
				.as("Java 25 should be described as the product-feature transition")
				.contains("experimental")
				.contains("product feature");

		assertThat(notes.option())
				.as("The notes should identify the Shenandoah options and avoid implying it is the default")
				.contains("-XX:+UseShenandoahGC")
				.contains("-XX:ShenandoahGCMode=generational")
				.contains("not the default");

		assertThat(notes.projectDecision())
				.as("The notes should explain why this is not a normal unit-test example")
				.contains("realistic workloads")
				.contains("GC logs")
				.contains("measurements");
	}
}
