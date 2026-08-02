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
 * This repository keeps KEM as notes because a realistic example depends on
 * cryptographic providers and setup that would distract from the language/API
 * learning path.
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
		return "the repository keeps KEM as notes to avoid crypto-provider-specific setup in simple examples";
	}
}
