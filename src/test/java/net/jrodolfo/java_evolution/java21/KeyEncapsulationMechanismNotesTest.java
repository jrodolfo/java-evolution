package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KeyEncapsulationMechanismNotesTest {

	private final KeyEncapsulationMechanismNotes notes = new KeyEncapsulationMechanismNotes();

	@Test
	void notesExplainKemApi() {
		assertThat(notes.purpose()).contains("symmetric key material");
		assertThat(notes.projectDecision()).contains("key_encapsulation");
		assertThat(notes.detailedExplanation()).endsWith("key_encapsulation/README.md");
	}
}
