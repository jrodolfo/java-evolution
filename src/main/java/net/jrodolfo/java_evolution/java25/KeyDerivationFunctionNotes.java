package net.jrodolfo.java_evolution.java25;

/**
 * Explains the Key Derivation Function API, finalized in Java 25.
 *
 * <p>
 * Cryptographic systems often derive new keys from secret material and context
 * data. Java 25 finalized a standard API for this task.
 * </p>
 *
 * <p>
 * The executable HKDF example lives in the {@code key_derivation} subpackage.
 * These notes keep the high-level purpose easy to find from the main Java 25
 * package.
 * </p>
 */
public class KeyDerivationFunctionNotes {
	/**
	 * Explains the cryptographic goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "derive cryptographic keys from secret material and contextual information";
	}

	/**
	 * Explains where the runnable example lives.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the runnable HKDF example lives in the key_derivation subpackage";
	}

	/**
	 * Points readers to the detailed Markdown explanation.
	 *
	 * @return the local documentation file for the full explanation
	 */
	public String detailedExplanation() {
		return "src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md";
	}
}
