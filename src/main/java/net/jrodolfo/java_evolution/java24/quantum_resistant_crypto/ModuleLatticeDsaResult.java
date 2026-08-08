package net.jrodolfo.java_evolution.java24.quantum_resistant_crypto;

/**
 * Captures the result of signing and verifying data with ML-DSA.
 *
 * @param algorithm the signature algorithm used by the example
 * @param message the message that was signed
 * @param signature the digital signature bytes
 * @param verifiesOriginalMessage whether verification succeeds for the original message
 * @param rejectsTamperedMessage whether verification fails for a modified message
 */
public record ModuleLatticeDsaResult(
		String algorithm,
		String message,
		byte[] signature,
		boolean verifiesOriginalMessage,
		boolean rejectsTamperedMessage) {

	/**
	 * Creates an immutable signature result.
	 */
	public ModuleLatticeDsaResult {
		signature = signature.clone();
	}

	@Override
	public byte[] signature() {
		return signature.clone();
	}
}
