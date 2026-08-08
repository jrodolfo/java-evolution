package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class QuantumResistantCryptoNotesTest {

	private final QuantumResistantCryptoNotes notes = new QuantumResistantCryptoNotes();

	@Test
	void notesIdentifyPostQuantumAlgorithms() {
		assertThat(notes.algorithms())
				.as("Java 24 added post-quantum algorithm support")
				.contains("ML-KEM")
				.contains("ML-DSA");
		assertThat(notes.purpose())
				.as("The note should explain why post-quantum algorithms matter")
				.contains("quantum");
		assertThat(notes.detailedExplanation())
				.as("The detailed explanation should point to the executable examples")
				.endsWith("quantum_resistant_crypto/README.md");
	}
}
