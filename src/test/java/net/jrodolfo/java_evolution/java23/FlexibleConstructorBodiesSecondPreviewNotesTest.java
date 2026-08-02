package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesSecondPreviewNotesTest {

	private final FlexibleConstructorBodiesSecondPreviewNotes notes = new FlexibleConstructorBodiesSecondPreviewNotes();

	@Test
	void notesExplainConstructorValidationBeforeDelegation() {
		assertThat(notes.purpose())
				.as("The note should explain why constructor validation before delegation matters")
				.contains("constructor");
		assertThat(notes.status())
				.as("The feature should remain labeled as preview in Java 23")
				.contains("preview");
	}
}
