package net.jrodolfo.java_evolution.java21;

/**
 * Explains the Key Encapsulation Mechanism API introduced in Java 21.
 *
 * <p>
 * Secure communication often needs two parties to agree on shared symmetric key
 * material without sending that key directly. A Key Encapsulation Mechanism
 * uses asymmetric cryptography to establish that shared material.
 * </p>
 *
 * <p>
 * The executable sender/receiver flow lives in the {@code key_encapsulation}
 * subpackage. These notes keep the high-level purpose easy to find from the
 * main Java 21 package.
 * </p>
 */
public class KeyEncapsulationMechanismNotes {

	/**
	 * Explains the cryptographic purpose.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "KEM lets two parties establish symmetric key material using asymmetric cryptography";
	}

	/**
	 * Explains why this feature is documented as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the runnable KEM sender and receiver example lives in the key_encapsulation subpackage";
	}

	/**
	 * Points readers to the detailed Markdown explanation.
	 *
	 * @return the local documentation file for the full explanation
	 */
	public String detailedExplanation() {
		return "src/main/java/net/jrodolfo/java_evolution/java21/key_encapsulation/README.md";
	}
}
