package net.jrodolfo.java_evolution.java24.quantum_resistant_crypto;

import java.util.Arrays;

/**
 * Captures the observable pieces of a module-lattice KEM exchange.
 *
 * <p>
 * The result keeps the example readable without printing key material. The
 * sender and receiver secrets are included only so tests can prove that both
 * sides derived the same shared secret.
 * </p>
 *
 * @param senderSecretBytes the shared secret bytes created by the sender
 * @param receiverSecretBytes the shared secret bytes recovered by the receiver
 * @param encapsulation the message sent from sender to receiver
 * @param algorithm the KEM algorithm used by the example
 */
public record ModuleLatticeKemResult(
		byte[] senderSecretBytes,
		byte[] receiverSecretBytes,
		byte[] encapsulation,
		String algorithm) {

	/**
	 * Creates an immutable KEM result.
	 */
	public ModuleLatticeKemResult {
		senderSecretBytes = senderSecretBytes.clone();
		receiverSecretBytes = receiverSecretBytes.clone();
		encapsulation = encapsulation.clone();
	}

	@Override
	public byte[] senderSecretBytes() {
		return senderSecretBytes.clone();
	}

	@Override
	public byte[] receiverSecretBytes() {
		return receiverSecretBytes.clone();
	}

	@Override
	public byte[] encapsulation() {
		return encapsulation.clone();
	}

	/**
	 * Shows whether encapsulation and decapsulation reached the same secret.
	 *
	 * @return {@code true} when both sides derived matching key material
	 */
	public boolean secretsMatch() {
		return Arrays.equals(senderSecretBytes, receiverSecretBytes);
	}
}
