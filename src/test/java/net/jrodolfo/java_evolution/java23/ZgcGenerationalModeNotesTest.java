package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ZgcGenerationalModeNotesTest {

	private final ZgcGenerationalModeNotes notes = new ZgcGenerationalModeNotes();

	@Test
	void notesExplainZgcGenerationalDefaultRuntimeChange() {
		assertThat(notes.purpose())
				.as("The ZGC note should document the runtime default change")
				.contains("generational mode");
		assertThat(notes.benefit())
				.as("The note should explain the expected GC benefit")
				.contains("garbage");
	}
}
