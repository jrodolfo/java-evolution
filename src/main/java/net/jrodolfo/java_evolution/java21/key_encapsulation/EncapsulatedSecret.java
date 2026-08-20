package net.jrodolfo.java_evolution.java21.key_encapsulation;

import javax.crypto.SecretKey;

/**
 * Holds the two pieces produced by the sender side of a KEM exchange.
 *
 * <p>
 * The {@code secretKey} is local key material. It is not sent over the network.
 * The {@code encapsulation} is the byte message that can be sent to the
 * receiver. The receiver combines that message with its private key to recover
 * the same shared secret.
 * </p>
 *
 * @param secretKey the sender-side shared secret
 * @param encapsulation the message sent to the receiver
 */
public record EncapsulatedSecret(SecretKey secretKey, byte[] encapsulation) {

	/**
	 * Creates an immutable holder for the encapsulation bytes.
	 */
	public EncapsulatedSecret {
		encapsulation = encapsulation.clone();
	}

	@Override
	public byte[] encapsulation() {
		return encapsulation.clone();
	}

	/**
	 * Returns the encoded shared secret bytes for demonstration and testing.
	 *
	 * <p>
	 * Real applications should usually pass key material into a key derivation
	 * step instead of printing or comparing raw bytes directly.
	 * </p>
	 *
	 * @return a defensive copy of the sender-side secret bytes
	 */
	public byte[] secretBytes() {
		var encoded = secretKey.getEncoded();
		if (encoded == null) {
			throw new IllegalStateException("the shared secret does not support encoding");
		}
		return encoded.clone();
	}
}
