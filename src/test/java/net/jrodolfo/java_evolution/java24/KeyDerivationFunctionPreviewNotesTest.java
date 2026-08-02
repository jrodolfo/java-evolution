package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KeyDerivationFunctionPreviewNotesTest {

	private final KeyDerivationFunctionPreviewNotes notes = new KeyDerivationFunctionPreviewNotes();

	@Test
	void notesSeparatePreviewSupportFromJava25FinalSupport() {
		assertThat(notes.purpose())
				.as("The KDF note should explain key derivation at a high level")
				.contains("keys");
		assertThat(notes.status())
				.as("KDF should remain documented as preview in Java 24 and final in Java 25")
				.contains("final in Java 25");
	}
}
