package net.jrodolfo.java_evolution.java25.key_derivation;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class HkdfKeyDerivationExampleTest {

	private final HkdfKeyDerivationExample example = new HkdfKeyDerivationExample();
	private final byte[] sharedSecret = "shared secret from a key exchange".getBytes(StandardCharsets.UTF_8);
	private final byte[] salt = "protocol salt".getBytes(StandardCharsets.UTF_8);

	@Test
	void derivesAesSizedKeyMaterialWithHkdfSha256() throws Exception {
		var keyMaterial = example.deriveAes256Key(sharedSecret, salt, "message encryption");

		assertThat(keyMaterial.algorithm())
				.as("the example uses the Java 25 HKDF-SHA256 algorithm")
				.isEqualTo(HkdfKeyDerivationExample.ALGORITHM);
		assertThat(keyMaterial.contextLabel())
				.as("the context label binds the derived key to a purpose")
				.isEqualTo("message encryption");
		assertThat(keyMaterial.byteLength())
				.as("AES-256 key material needs 32 bytes")
				.isEqualTo(32);
		assertThat(keyMaterial.hex())
				.as("the derived bytes should be observable in tests without printing raw arrays")
				.hasSize(64);
	}

	@Test
	void changingContextProducesDifferentKeyMaterial() throws Exception {
		var encryptionKey = example.deriveAes256Key(sharedSecret, salt, "message encryption");
		var authenticationKey = example.deriveAes256Key(sharedSecret, salt, "message authentication");

		assertThat(encryptionKey.bytes())
				.as("different context labels should produce separate derived keys")
				.isNotEqualTo(authenticationKey.bytes());
		assertThat(encryptionKey.contextLabel()).isEqualTo("message encryption");
		assertThat(authenticationKey.contextLabel()).isEqualTo("message authentication");
	}
}
