package net.jrodolfo.java_evolution.java21.key_encapsulation;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.DecapsulateException;

/**
 * Demonstrates the full Java 21 Key Encapsulation Mechanism API flow.
 *
 * <p>
 * The example models a receiver, often called Bob, that owns a public/private
 * key pair. A sender, often called Alice, uses Bob's public key to create a
 * shared secret and an encapsulation message. Bob then uses his private key and
 * the encapsulation message to recover the same shared secret.
 * </p>
 */
public class KeyEncapsulationExchange {

	/**
	 * Runs a complete in-memory KEM exchange.
	 *
	 * @return the sender and receiver secrets plus the encapsulation message
	 * @throws NoSuchAlgorithmException if X25519 or DHKEM is not available
	 * @throws InvalidKeyException if the generated keys cannot be used for DHKEM
	 * @throws DecapsulateException if the encapsulation cannot be decoded by the receiver
	 */
	public KeyExchangeResult exchange()
			throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {
		var receiver = KemReceiver.create();
		var sender = KemSender.forReceiver(receiver.publicKey());
		var encapsulated = sender.encapsulate();
		var receiverSecret = receiver.decapsulate(encapsulated.encapsulation());

		return new KeyExchangeResult(
				encapsulated.secretBytes(),
				receiverSecret.getEncoded(),
				encapsulated.encapsulation(),
				sender.providerName());
	}

	/**
	 * Captures the observable result of the KEM exchange for tests and readers.
	 *
	 * @param senderSecretBytes the shared secret bytes created by the sender
	 * @param receiverSecretBytes the shared secret bytes recovered by the receiver
	 * @param encapsulation the transmitted encapsulation message
	 * @param providerName the provider selected for the sender-side KEM operation
	 */
	public record KeyExchangeResult(
			byte[] senderSecretBytes,
			byte[] receiverSecretBytes,
			byte[] encapsulation,
			String providerName) {

		/**
		 * Creates an immutable exchange result.
		 */
		public KeyExchangeResult {
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
		 * Shows whether both parties reached the same shared secret.
		 *
		 * @return {@code true} when sender and receiver key material matches
		 */
		public boolean secretsMatch() {
			return Arrays.equals(senderSecretBytes, receiverSecretBytes);
		}
	}
}
