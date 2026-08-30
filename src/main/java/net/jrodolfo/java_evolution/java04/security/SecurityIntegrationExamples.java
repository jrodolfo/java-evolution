package net.jrodolfo.java_evolution.java04.security;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Demonstrates local security APIs integrated into the standard platform.
 *
 * <p>
 * J2SE 1.4 integrated JCE, JSSE, and JAAS into the platform. This executable
 * example focuses on local Java Cryptography Architecture (JCA) and Java
 * Cryptography Extension (JCE) operations because they are deterministic,
 * portable, and do not require sockets, login configuration, credentials, or
 * external services.
 * </p>
 * <p>
 * The example uses later platform APIs such as AES-GCM, SHA-256,
 * {@link GCMParameterSpec}, {@link Provider.Service},
 * {@link Arrays#copyOf(byte[], int)}, and {@link StandardCharsets#UTF_8}
 * intentionally. These choices keep the demonstration aligned with modern
 * cryptographic security practice on the current JDK, while the surrounding
 * package explains the Java 4-era integration of security APIs.
 * </p>
 */
public class SecurityIntegrationExamples {

	private static final byte[] AES_KEY_BYTES = new byte[] {
			0x00, 0x01, 0x02, 0x03,
			0x04, 0x05, 0x06, 0x07,
			0x08, 0x09, 0x0a, 0x0b,
			0x0c, 0x0d, 0x0e, 0x0f
	};
	private static final byte[] GCM_IV = new byte[] {
			0x10, 0x11, 0x12, 0x13,
			0x14, 0x15, 0x16, 0x17,
			0x18, 0x19, 0x1a, 0x1b
	};
	private static final byte[] HMAC_KEY_BYTES = new byte[] {
			0x20, 0x21, 0x22, 0x23,
			0x24, 0x25, 0x26, 0x27,
			0x28, 0x29, 0x2a, 0x2b,
			0x2c, 0x2d, 0x2e, 0x2f
	};

	/**
	 * Lists provider services for a given algorithm.
	 *
	 * @param algorithm algorithm to find, such as {@code SHA-256}
	 * @return provider/service summaries
	 */
	public List<ProviderServiceSummary> providerServicesFor(String algorithm) {
		List<ProviderServiceSummary> services = new ArrayList<ProviderServiceSummary>();
		Provider[] providers = Security.getProviders();
		for (Provider provider : providers) {
			for (Provider.Service service : provider.getServices()) {
				if (service.getAlgorithm().equalsIgnoreCase(algorithm)) {
					services.add(new ProviderServiceSummary(
							provider.getName(),
							service.getType(),
							service.getAlgorithm()));
				}
			}
		}
		return services;
	}

	/**
	 * Computes a SHA-256 digest.
	 *
	 * @param text text to digest
	 * @return digest bytes
	 * @throws GeneralSecurityException when SHA-256 is unavailable
	 */
	public byte[] sha256Digest(String text) throws GeneralSecurityException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(text.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Generates random bytes with {@link SecureRandom}.
	 *
	 * @param length number of bytes to generate
	 * @return random bytes
	 */
	public byte[] secureRandomBytes(int length) {
		byte[] bytes = new byte[length];
		new SecureRandom().nextBytes(bytes);
		return bytes;
	}

	/**
	 * Generates an AES key with the platform provider.
	 *
	 * @return generated AES key
	 * @throws GeneralSecurityException when AES key generation is unavailable
	 */
	public SecretKey generateAesKey() throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);
		return generator.generateKey();
	}

	/**
	 * Encrypts text with AES/GCM.
	 *
	 * <p>
	 * This example uses a fixed key and initialization vector so tests can focus
	 * on API behavior. Production code must use a fresh, unique GCM IV for each
	 * encryption with the same key.
	 * </p>
	 *
	 * @param plaintext text to encrypt
	 * @return ciphertext plus authentication tag
	 * @throws GeneralSecurityException when encryption fails
	 */
	public byte[] encryptWithAesGcm(String plaintext) throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, fixedAesKey(), fixedGcmParameters());
		return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Decrypts bytes produced by {@link #encryptWithAesGcm(String)}.
	 *
	 * @param ciphertext ciphertext plus authentication tag
	 * @return decrypted text
	 * @throws GeneralSecurityException when authentication or decryption fails
	 */
	public String decryptWithAesGcm(byte[] ciphertext) throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, fixedAesKey(), fixedGcmParameters());
		byte[] plaintext = cipher.doFinal(ciphertext);
		return new String(plaintext, StandardCharsets.UTF_8);
	}

	/**
	 * Computes an HMAC-SHA256 tag.
	 *
	 * @param message message to authenticate
	 * @return authentication tag
	 * @throws GeneralSecurityException when HMAC-SHA256 is unavailable
	 */
	public byte[] hmacSha256(String message) throws GeneralSecurityException {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(HMAC_KEY_BYTES, "HmacSHA256"));
		return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Verifies an HMAC-SHA256 tag using constant-time comparison.
	 *
	 * @param message authenticated message
	 * @param expectedTag expected authentication tag
	 * @return whether the tag matches the message
	 * @throws GeneralSecurityException when HMAC-SHA256 is unavailable
	 */
	public boolean verifyHmacSha256(String message, byte[] expectedTag) throws GeneralSecurityException {
		byte[] actualTag = hmacSha256(message);
		return MessageDigest.isEqual(actualTag, expectedTag);
	}

	/**
	 * Generates an RSA key pair for the signature example.
	 *
	 * @return generated key pair
	 * @throws GeneralSecurityException when RSA key generation is unavailable
	 */
	public KeyPair generateRsaKeyPair() throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.generateKeyPair();
	}

	/**
	 * Signs a message with SHA256withRSA.
	 *
	 * @param message message to sign
	 * @param keyPair signing key pair
	 * @return signature bytes
	 * @throws GeneralSecurityException when signing fails
	 */
	public byte[] signWithRsa(String message, KeyPair keyPair) throws GeneralSecurityException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(keyPair.getPrivate());
		signature.update(message.getBytes(StandardCharsets.UTF_8));
		return signature.sign();
	}

	/**
	 * Verifies a SHA256withRSA signature.
	 *
	 * @param message signed message
	 * @param signed signature bytes
	 * @param keyPair key pair whose public key should verify the signature
	 * @return whether the signature is valid for the message
	 * @throws GeneralSecurityException when verification fails
	 */
	public boolean verifyRsaSignature(String message, byte[] signed, KeyPair keyPair)
			throws GeneralSecurityException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(keyPair.getPublic());
		signature.update(message.getBytes(StandardCharsets.UTF_8));
		return signature.verify(signed);
	}

	/**
	 * Creates a copy with one byte changed.
	 *
	 * @param bytes source bytes
	 * @return tampered copy
	 */
	public byte[] tamper(byte[] bytes) {
		byte[] copy = Arrays.copyOf(bytes, bytes.length);
		copy[copy.length - 1] = (byte) (copy[copy.length - 1] ^ 0x01);
		return copy;
	}

	private SecretKey fixedAesKey() {
		return new SecretKeySpec(AES_KEY_BYTES, "AES");
	}

	private GCMParameterSpec fixedGcmParameters() {
		return new GCMParameterSpec(128, GCM_IV);
	}

	/**
	 * Summarizes a provider service.
	 */
	public static final class ProviderServiceSummary {

		private final String providerName;
		private final String serviceType;
		private final String algorithm;

		private ProviderServiceSummary(String providerName, String serviceType, String algorithm) {
			this.providerName = providerName;
			this.serviceType = serviceType;
			this.algorithm = algorithm;
		}

		/**
		 * @return provider name
		 */
		public String providerName() {
			return providerName;
		}

		/**
		 * @return service type, such as {@code MessageDigest}
		 */
		public String serviceType() {
			return serviceType;
		}

		/**
		 * @return algorithm name
		 */
		public String algorithm() {
			return algorithm;
		}
	}
}
