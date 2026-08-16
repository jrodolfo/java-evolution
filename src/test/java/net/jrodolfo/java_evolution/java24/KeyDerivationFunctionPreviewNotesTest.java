package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KeyDerivationFunctionPreviewNotesTest {

	private final KeyDerivationFunctionPreviewNotes notes = new KeyDerivationFunctionPreviewNotes();

	@Test
	void notesSeparatePreviewSupportFromJava25FinalSupport() {
		String purpose = notes.purpose();
		String whyDeriveKeys = notes.whyDeriveKeys();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("The KDF note should explain key derivation at a high level")
				.contains("keys")
				.contains("context data");
		assertThat(whyDeriveKeys)
				.as("The note should explain why protocols derive separate keys")
				.contains("purpose-specific keys")
				.contains("shared secret");
		assertThat(status)
				.as("KDF should remain documented as preview in Java 24 and final in Java 25")
				.contains("preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The Java 24 preview note should point learners to the final Java 25 example")
				.contains("Java 25")
				.contains("final runnable KDF example");
	}
}
