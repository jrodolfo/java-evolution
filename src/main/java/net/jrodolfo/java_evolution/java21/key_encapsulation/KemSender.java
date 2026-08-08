package net.jrodolfo.java_evolution.java21.key_encapsulation;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.KEM;

/**
 * Represents the sender side of a Java 21 KEM exchange.
 *
 * <p>
 * The sender receives the receiver's public key, creates a shared secret, and
 * creates an encapsulation message. The sender keeps the shared secret locally
 * and sends only the encapsulation message to the receiver.
 * </p>
 */
public class KemSender {

	private final KEM.Encapsulator encapsulator;

	private KemSender(KEM.Encapsulator encapsulator) {
		this.encapsulator = encapsulator;
	}

	/**
	 * Creates a sender that can encapsulate a secret for the supplied receiver.
	 *
	 * @param receiverPublicKey the public key published by the receiver
	 * @return a sender bound to the receiver public key
	 * @throws NoSuchAlgorithmException if DHKEM is not available from the active providers
	 * @throws InvalidKeyException if the public key cannot be used for DHKEM
	 */
	public static KemSender forReceiver(PublicKey receiverPublicKey)
			throws NoSuchAlgorithmException, InvalidKeyException {
		var kem = KEM.getInstance(KemReceiver.KEM_ALGORITHM);
		return new KemSender(kem.newEncapsulator(receiverPublicKey));
	}

	/**
	 * Creates the sender-side secret and the encapsulation message.
	 *
	 * @return the shared secret and the message that should be sent to the receiver
	 */
	public EncapsulatedSecret encapsulate() {
		var encapsulated = encapsulator.encapsulate();
		return new EncapsulatedSecret(encapsulated.key(), encapsulated.encapsulation());
	}

	/**
	 * Returns the provider name selected by the Java Cryptography Architecture.
	 *
	 * @return the provider that implements this KEM operation
	 */
	public String providerName() {
		return encapsulator.providerName();
	}
}
