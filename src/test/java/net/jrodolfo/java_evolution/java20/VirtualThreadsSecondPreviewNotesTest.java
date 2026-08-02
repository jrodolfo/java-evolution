package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VirtualThreadsSecondPreviewNotesTest {

	private final VirtualThreadsSecondPreviewNotes notes = new VirtualThreadsSecondPreviewNotes();

	@Test
	void notesExplainVirtualThreadsPreviewStatus() {
		assertThat(notes.purpose()).contains("lightweight threads");
		assertThat(notes.finalRelease()).contains("Java 21");
	}
}
