package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySecondIncubatorNotesTest {

	private final StructuredConcurrencySecondIncubatorNotes notes = new StructuredConcurrencySecondIncubatorNotes();

	@Test
	void notesExplainStructuredConcurrencyIncubatorStatus() {
		assertThat(notes.purpose()).contains("one unit of work");
		assertThat(notes.benefit()).contains("cancellation").contains("failure");
		assertThat(notes.projectDecision()).contains("incubating");
	}
}
