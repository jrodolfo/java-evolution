package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Key Derivation Function API preview in Java 24.
 *
 * <p>
 * Cryptographic systems often need to derive new keys from secret material and
 * context data. Java 24 previewed a standard API for that operation, and Java
 * 25 finalized it.
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
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "preview in Java 24 and final in Java 25";
	}
}
