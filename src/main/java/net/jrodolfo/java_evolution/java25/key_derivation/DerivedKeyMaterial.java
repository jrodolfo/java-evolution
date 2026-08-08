package net.jrodolfo.java_evolution.java25.key_derivation;

import java.util.HexFormat;

/**
 * Captures key material derived by the Java 25 KDF API.
 *
 * <p>
 * The derived bytes are exposed for tests and learning. Production code should
 * handle key material carefully and avoid logging or printing secrets.
 * </p>
 *
 * @param algorithm the KDF algorithm used by the example
 * @param contextLabel the application context used during derivation
 * @param bytes the derived key bytes
 */
public record DerivedKeyMaterial(String algorithm, String contextLabel, byte[] bytes) {

	/**
	 * Creates an immutable holder for derived key bytes.
	 */
	public DerivedKeyMaterial {
		bytes = bytes.clone();
	}

	@Override
	public byte[] bytes() {
		return bytes.clone();
	}

	/**
	 * Returns the number of derived bytes.
	 *
	 * @return derived key material length in bytes
	 */
	public int byteLength() {
		return bytes.length;
	}

	/**
	 * Formats the derived bytes for deterministic tests and documentation.
	 *
	 * @return hexadecimal representation of the derived bytes
	 */
	public String hex() {
		return HexFormat.of().formatHex(bytes);
	}
}
