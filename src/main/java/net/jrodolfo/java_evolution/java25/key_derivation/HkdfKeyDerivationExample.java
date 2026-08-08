package net.jrodolfo.java_evolution.java25.key_derivation;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KDF;
import javax.crypto.spec.HKDFParameterSpec;

/**
 * Demonstrates the Java 25 Key Derivation Function API with HKDF-SHA256.
 *
 * <p>
 * HKDF is the HMAC-based Key Derivation Function. It takes input key material,
 * an optional salt, and application-specific context information, then derives
 * key bytes for a concrete purpose. This is useful after a key exchange, such
 * as KEM, because raw shared material is usually not the exact key an
 * application should use directly.
 * </p>
 */
public class HkdfKeyDerivationExample {

	/**
	 * Standard algorithm name for HKDF with SHA-256.
	 */
	public static final String ALGORITHM = "HKDF-SHA256";

	private static final int AES_256_KEY_BYTES = 32;

	/**
	 * Derives 256-bit key material for a specific application context.
	 *
	 * @param inputKeyMaterial the existing secret material
	 * @param salt additional random or protocol-specific salt
	 * @param contextLabel purpose-specific context information
	 * @return derived key material
	 * @throws NoSuchAlgorithmException if HKDF-SHA256 is not available
	 * @throws InvalidAlgorithmParameterException if the HKDF parameters are invalid
	 */
	public DerivedKeyMaterial deriveAes256Key(byte[] inputKeyMaterial, byte[] salt, String contextLabel)
			throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		var kdf = KDF.getInstance(ALGORITHM);
		var parameters = HKDFParameterSpec.ofExtract()
				.addIKM(inputKeyMaterial)
				.addSalt(salt)
				.thenExpand(contextLabel.getBytes(StandardCharsets.UTF_8), AES_256_KEY_BYTES);

		return new DerivedKeyMaterial(
				kdf.getAlgorithm(),
				contextLabel,
				kdf.deriveData(parameters));
	}
}
