package net.jrodolfo.java_evolution.java24;

/**
 * Explains quantum-resistant ML-KEM and ML-DSA support introduced in Java 24.
 *
 * <p>
 * Post-quantum cryptography prepares systems for attackers with future quantum
 * computing capabilities. Java 24 added standard support for module-lattice
 * algorithms used for key encapsulation and digital signatures.
 * </p>
 */
public class QuantumResistantCryptoNotes {
	/**
	 * Names the algorithms represented by this release note.
	 *
	 * @return algorithm summary
	 */
	public String algorithms() {
		return "ML-KEM for key encapsulation and ML-DSA for digital signatures";
	}

	/**
	 * Explains the security goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "prepare Java cryptography for post-quantum security requirements";
	}
}
