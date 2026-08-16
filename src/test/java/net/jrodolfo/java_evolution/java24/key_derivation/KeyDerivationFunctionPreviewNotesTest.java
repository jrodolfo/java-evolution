package net.jrodolfo.java_evolution.java24.key_derivation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KeyDerivationFunctionPreviewNotesTest {

	private final KeyDerivationFunctionPreviewNotes notes = new KeyDerivationFunctionPreviewNotes();

	@Test
	void notesSeparatePreviewSupportFromJava25FinalSupport() {
		String problemSolved = notes.problemSolved();
		String whyDeriveKeys = notes.whyDeriveKeys();
		String kdfInputs = notes.kdfInputs();
		String purposeSeparation = notes.purposeSeparation();
		String status = notes.previewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The KDF note should explain the risk of reusing raw secret material")
				.contains("purpose-specific keys")
				.contains("raw shared secret");
		assertThat(whyDeriveKeys)
				.as("The note should explain why protocols derive separate keys")
				.contains("purpose-specific keys")
				.contains("shared secret");
		assertThat(kdfInputs)
				.as("The note should name the major KDF inputs")
				.contains("input key material")
				.contains("salt")
				.contains("context information")
				.contains("output length");
		assertThat(purposeSeparation)
				.as("The note should explain context-based purpose separation")
				.contains("context information")
				.contains("encryption")
				.contains("authentication");
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
