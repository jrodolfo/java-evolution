package net.jrodolfo.java_evolution.java04.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.List;

import javax.crypto.AEADBadTagException;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

class SecurityIntegrationExamplesTest {

	private final SecurityIntegrationExamples examples = new SecurityIntegrationExamples();

	@Test
	void providerModelExposesAlgorithmServices() {
		List<SecurityIntegrationExamples.ProviderServiceSummary> services =
				examples.providerServicesFor("SHA-256");

		assertThat(services)
				.as("JCA providers should advertise installed algorithm services")
				.isNotEmpty()
				.anySatisfy(service -> {
					assertThat(service.providerName())
							.as("Provider names make the pluggable provider model visible")
							.isNotBlank();
					assertThat(service.serviceType())
							.as("SHA-256 should be exposed as a MessageDigest service")
							.isEqualTo("MessageDigest");
					assertThat(service.algorithm())
							.as("The requested algorithm should be preserved in the service summary")
							.isEqualToIgnoringCase("SHA-256");
				});
	}

	@Test
	void sha256DigestIsStableForKnownInput() throws Exception {
		byte[] digest = examples.sha256Digest("java");

		assertThat(digest)
				.as("SHA-256 should produce a 256-bit digest")
				.hasSize(32)
				.containsExactly(
						(byte) 0x38, (byte) 0xa0, (byte) 0x96, (byte) 0x3a,
						(byte) 0x63, (byte) 0x64, (byte) 0xb0, (byte) 0x9a,
						(byte) 0xd8, (byte) 0x67, (byte) 0xaa, (byte) 0x9a,
						(byte) 0x66, (byte) 0xc6, (byte) 0xd0, (byte) 0x09,
						(byte) 0x67, (byte) 0x3c, (byte) 0x21, (byte) 0xe1,
						(byte) 0x82, (byte) 0x01, (byte) 0x54, (byte) 0x61,
						(byte) 0xda, (byte) 0x23, (byte) 0x6e, (byte) 0xc3,
						(byte) 0x61, (byte) 0x87, (byte) 0x7f, (byte) 0x77);
	}

	@Test
	void secureRandomProducesRequestedNumberOfBytes() {
		byte[] first = examples.secureRandomBytes(16);
		byte[] second = examples.secureRandomBytes(16);

		assertThat(first)
				.as("SecureRandom should fill the requested byte array length")
				.hasSize(16);
		assertThat(second)
				.as("Separate random requests should return separate arrays")
				.hasSize(16)
				.isNotSameAs(first);
	}

	@Test
	void aesKeyGeneratorProducesSecretKeyMaterial() throws Exception {
		SecretKey key = examples.generateAesKey();

		assertThat(key.getAlgorithm())
				.as("JCE key generators produce provider-backed keys for named algorithms")
				.isEqualTo("AES");
		assertThat(key.getEncoded())
				.as("A 128-bit AES key should contain 16 bytes of encoded key material")
				.hasSize(16);
	}

	@Test
	void aesGcmRoundTripsPlaintextAndRejectsTampering() throws Exception {
		byte[] ciphertext = examples.encryptWithAesGcm("confidential");

		assertThat(ciphertext)
				.as("Authenticated encryption should produce bytes different from the plaintext")
				.isNotEqualTo("confidential".getBytes(StandardCharsets.UTF_8));
		assertThat(examples.decryptWithAesGcm(ciphertext))
				.as("Decrypting the original AES/GCM ciphertext should recover the plaintext")
				.isEqualTo("confidential");

		byte[] tampered = examples.tamper(ciphertext);
		assertThatThrownBy(() -> examples.decryptWithAesGcm(tampered))
				.as("AES/GCM should reject ciphertext whose authentication tag no longer matches")
				.isInstanceOf(AEADBadTagException.class);
	}

	@Test
	void hmacVerifiesOriginalMessageAndRejectsChangedMessage() throws Exception {
		byte[] tag = examples.hmacSha256("important");

		assertThat(tag)
				.as("HMAC-SHA256 should produce a 256-bit authentication tag")
				.hasSize(32);
		assertThat(examples.verifyHmacSha256("important", tag))
				.as("The original message should verify against its HMAC tag")
				.isTrue();
		assertThat(examples.verifyHmacSha256("changed", tag))
				.as("Changing the message should invalidate the HMAC tag")
				.isFalse();
	}

	@Test
	void rsaSignatureVerifiesOriginalMessageAndRejectsChangedMessage() throws Exception {
		KeyPair keyPair = examples.generateRsaKeyPair();
		byte[] signed = examples.signWithRsa("signed content", keyPair);

		assertThat(signed)
				.as("RSA signatures should produce signature bytes")
				.isNotEmpty();
		assertThat(examples.verifyRsaSignature("signed content", signed, keyPair))
				.as("The public key should verify the original signed content")
				.isTrue();
		assertThat(examples.verifyRsaSignature("changed content", signed, keyPair))
				.as("Changing the content should invalidate the signature")
				.isFalse();
	}
}
