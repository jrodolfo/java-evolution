package net.jrodolfo.java_evolution.java04.security;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
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
 */
public class SecurityIntegrationExamples {

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
	public List providerServicesFor(String algorithm) {
		List services = new ArrayList();
		Provider[] providers = Security.getProviders();
		for (int providerIndex = 0; providerIndex < providers.length; providerIndex++) {
			Provider provider = providers[providerIndex];
			Enumeration keys = provider.keys();
			while (keys.hasMoreElements()) {
				String key = String.valueOf(keys.nextElement());
				int separator = key.indexOf('.');
				if (separator > 0 && key.substring(0, separator).equals("MessageDigest")
						&& key.substring(separator + 1).equalsIgnoreCase(algorithm)) {
					services.add(new ProviderServiceSummary(
							provider.getName(), "MessageDigest", algorithm));
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
		return digest.digest(text.getBytes());
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
	 * Computes an HMAC-SHA1 tag for this historical provider example.
	 *
	 * @param message message to authenticate
	 * @return authentication tag
	 * @throws GeneralSecurityException when HMAC-SHA1 is unavailable
	 */
	public byte[] hmacSha1(String message) throws GeneralSecurityException {
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(HMAC_KEY_BYTES, "HmacSHA1"));
		return mac.doFinal(message.getBytes());
	}

	/**
	 * Verifies an HMAC-SHA1 tag using constant-time comparison.
	 *
	 * @param message authenticated message
	 * @param expectedTag expected authentication tag
	 * @return whether the tag matches the message
	 * @throws GeneralSecurityException when HMAC-SHA1 is unavailable
	 */
	public boolean verifyHmacSha1(String message, byte[] expectedTag) throws GeneralSecurityException {
		byte[] actualTag = hmacSha1(message);
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
		signature.update(message.getBytes());
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
		signature.update(message.getBytes());
		return signature.verify(signed);
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
