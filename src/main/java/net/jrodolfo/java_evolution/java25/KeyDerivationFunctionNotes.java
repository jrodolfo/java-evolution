package net.jrodolfo.java_evolution.java25;

/**
 * Explains the Key Derivation Function API, finalized in Java 25.
 *
 * <p>
 * Cryptographic systems often derive new keys from secret material and context
 * data. Java 25 finalized a standard API for this task. This repository keeps
 * the topic as notes because real cryptographic examples require careful
 * provider and security setup.
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
	 * Explains why this package uses notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the repository keeps this as notes to avoid provider-specific cryptographic setup";
	}
}
