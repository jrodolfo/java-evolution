package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompactObjectHeadersNotesTest {

	private final CompactObjectHeadersNotes notes = new CompactObjectHeadersNotes();

	@Test
	void notesExplainMemoryFootprintGoal() {
		assertThat(notes.purpose())
				.as("Compact object headers should be explained as a memory-footprint feature")
				.contains("memory footprint");
	}
}
