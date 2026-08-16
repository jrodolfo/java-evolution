package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Key Derivation Function API preview in Java 24.
 *
 * <p>
 * Cryptographic systems often need to derive new keys from secret material and
 * context data. A Key Derivation Function, or KDF, turns input secret material
 * into purpose-specific key material, such as one key for encryption and
 * another key for authentication.
 * </p>
 *
 * <p>
 * Java 24 previewed a standard API for that operation, and Java 25 finalized
 * it. This package keeps the Java 24 feature as a preview note because the
 * final executable example belongs in the Java 25 key-derivation module.
 * </p>
 */
public class KeyDerivationFunctionPreviewNotes {
	/**
	 * Explains the cryptographic goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "derive cryptographic keys from secret key material and context data";
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
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
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
