package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesThirdPreviewNotesTest {

	private final FlexibleConstructorBodiesThirdPreviewNotes notes = new FlexibleConstructorBodiesThirdPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		assertThat(notes.status())
				.as("Flexible constructor bodies should point to Java 25 finalization")
				.contains("final in Java 25");
	}
}
