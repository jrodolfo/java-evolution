package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VirtualThreadSynchronizationNotesTest {

	private final VirtualThreadSynchronizationNotes notes = new VirtualThreadSynchronizationNotes();

	@Test
	void notesExplainExistingCodeScalability() {
		assertThat(notes.purpose())
				.as("The note should connect synchronization to virtual-thread behavior")
				.contains("virtual threads");
		assertThat(notes.benefit())
				.as("The feature matters because synchronized legacy code should scale better on virtual threads")
				.contains("scalability");
	}
}
