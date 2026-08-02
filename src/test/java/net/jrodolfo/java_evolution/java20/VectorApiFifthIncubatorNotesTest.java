package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VectorApiFifthIncubatorNotesTest {

	private final VectorApiFifthIncubatorNotes notes = new VectorApiFifthIncubatorNotes();

	@Test
	void notesExplainVectorApiIncubatorStatus() {
		assertThat(notes.purpose()).contains("vector computations");
		assertThat(notes.projectDecision()).contains("incubator module");
	}
}
