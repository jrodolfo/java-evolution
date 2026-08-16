package net.jrodolfo.java_evolution.java24.key_derivation;

/**
 * Explains the Key Derivation Function API preview in Java 24.
 *
 * <p>
 * Cryptographic systems often need to derive purpose-specific keys from shared
 * secret material instead of reusing the same raw bytes everywhere. A Key
 * Derivation Function, or KDF, combines input key material, salt, context, and
 * an output length to produce derived key material.
 * </p>
 *
 * <p>
 * Java 24 previewed a standard API for that operation, and Java 25 finalized
 * it. This package keeps the Java 24 feature as preview notes because the
 * final executable example belongs in the Java 25 key-derivation module.
 * </p>
 */
public class KeyDerivationFunctionPreviewNotes {
	/**
	 * Explains the cryptographic problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "cryptographic protocols need purpose-specific keys instead of reusing one raw shared secret everywhere";
	}

	/**
	* Explains why protocols derive multiple keys instead of reusing one raw
	 * secret everywhere.
	 *
	 * @return a short motivation note
	 */
	public String whyDeriveKeys() {
		return "derive purpose-specific keys so one shared secret is not reused for every cryptographic job";
	}

	/**
	 * Names the common KDF inputs.
	 *
	 * @return a short input summary
	 */
	public String kdfInputs() {
		return "a KDF commonly uses input key material, salt, context information, and requested output length";
	}

	/**
	 * Explains why context labels matter.
	 *
	 * @return a short explanation
	 */
	public String purposeSeparation() {
		return "context information separates derived keys for jobs such as encryption and authentication";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String previewStatus() {
		return "preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final runnable version.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 key-derivation module for the final runnable KDF example";
	}
}
