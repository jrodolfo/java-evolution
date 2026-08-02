package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KeyDerivationFunctionNotesTest {

	private final KeyDerivationFunctionNotes notes = new KeyDerivationFunctionNotes();

	@Test
	void notesExplainKdfWithoutProviderSpecificSetup() {
		assertThat(notes.purpose())
				.as("The KDF note should explain key derivation at a high level")
				.contains("cryptographic keys");
		assertThat(notes.projectDecision())
				.as("The note should explain why provider-specific setup is avoided")
				.contains("provider");
	}
}
