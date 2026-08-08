package net.jrodolfo.java_evolution.java24.quantum_resistant_crypto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleLatticeCryptoExamplesTest {

	@Test
	void moduleLatticeKemDerivesTheSameSecretOnBothSides() throws Exception {
		var example = new ModuleLatticeKemExample();

		var result = example.exchange();

		assertThat(result.algorithm())
				.as("the example uses the Java 24 ML-KEM algorithm")
				.isEqualTo(ModuleLatticeKemExample.ALGORITHM);
		assertThat(result.senderSecretBytes())
				.as("the sender creates shared secret material")
				.isNotEmpty();
		assertThat(result.receiverSecretBytes())
				.as("the receiver recovers shared secret material")
				.isNotEmpty();
		assertThat(result.encapsulation())
				.as("the encapsulation message is sent instead of the secret itself")
				.isNotEmpty();
		assertThat(result.secretsMatch())
				.as("encapsulation and decapsulation should produce matching secrets")
				.isTrue();
	}

	@Test
	void moduleLatticeDsaSignsAndVerifiesMessages() throws Exception {
		var example = new ModuleLatticeDsaExample();

		var result = example.signAndVerify("Java 24 post-quantum signature");

		assertThat(result.algorithm())
				.as("the example uses the Java 24 ML-DSA algorithm")
				.isEqualTo(ModuleLatticeDsaExample.ALGORITHM);
		assertThat(result.signature())
				.as("signing produces signature bytes")
				.isNotEmpty();
		assertThat(result.verifiesOriginalMessage())
				.as("the public key verifies the original signed message")
				.isTrue();
		assertThat(result.rejectsTamperedMessage())
				.as("the same signature must not verify a changed message")
				.isTrue();
	}
}
