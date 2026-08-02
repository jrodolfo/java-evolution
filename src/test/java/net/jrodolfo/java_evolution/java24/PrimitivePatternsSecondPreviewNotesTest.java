package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitivePatternsSecondPreviewNotesTest {

	private final PrimitivePatternsSecondPreviewNotes notes = new PrimitivePatternsSecondPreviewNotes();

	@Test
	void notesKeepPrimitivePatternsLabeledAsPreview() {
		assertThat(notes.status())
				.as("Primitive patterns should still be preview after Java 24")
				.contains("third preview");
	}
}
