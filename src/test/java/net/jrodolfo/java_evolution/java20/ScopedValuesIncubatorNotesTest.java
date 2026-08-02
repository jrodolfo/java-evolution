package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesIncubatorNotesTest {

	private final ScopedValuesIncubatorNotes notes = new ScopedValuesIncubatorNotes();

	@Test
	void notesExplainScopedValuesIncubatorStatus() {
		assertThat(notes.purpose()).contains("immutable data");
		assertThat(notes.relationToThreadLocal()).contains("ThreadLocal");
		assertThat(notes.projectDecision()).contains("incubator modules");
	}
}
