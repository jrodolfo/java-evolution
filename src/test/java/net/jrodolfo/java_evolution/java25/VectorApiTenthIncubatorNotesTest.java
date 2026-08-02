package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VectorApiTenthIncubatorNotesTest {

	private final VectorApiTenthIncubatorNotes notes = new VectorApiTenthIncubatorNotes();

	@Test
	void notesKeepVectorApiMarkedAsIncubating() {
		assertThat(notes.status())
				.as("Vector API should stay marked as incubating")
				.contains("tenth incubator");
	}
}
