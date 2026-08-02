package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesFourthPreviewNotesTest {

	private final ScopedValuesFourthPreviewNotes notes = new ScopedValuesFourthPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		assertThat(notes.status())
				.as("Scoped values should point to Java 25 finalization")
				.contains("final in Java 25");
	}
}
