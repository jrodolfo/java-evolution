package net.jrodolfo.java_evolution.java21.key_encapsulation;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import javax.crypto.SecretKey;

/**
 * Represents the receiver side of a Java 21 KEM exchange.
 *
 * <p>
 * The receiver owns the asymmetric key pair. It shares the public key with the
 * sender and keeps the private key secret. The private key is later used to
 * decapsulate the sender's encapsulation message.
 * </p>
 */
public class KemReceiver {

	/**
	 * The key agreement key type used by this compact example.
	 */
	public static final String KEY_PAIR_ALGORITHM = "X25519";

	/**
	 * The Java 21 KEM algorithm name used by the standard API.
	 */
	public static final String KEM_ALGORITHM = "DHKEM";

	private final KeyPair keyPair;

	private KemReceiver(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	/**
	 * Creates a receiver with a fresh X25519 key pair.
	 *
	 * @return a receiver ready to publish its public key
	 * @throws NoSuchAlgorithmException if X25519 is not available from the active providers
	 */
	public static KemReceiver create() throws NoSuchAlgorithmException {
		var generator = KeyPairGenerator.getInstance(KEY_PAIR_ALGORITHM);
		return new KemReceiver(generator.generateKeyPair());
	}

	/**
	 * Returns the public key that can be shared with the sender.
	 *
	 * @return the receiver public key
	 */
	public PublicKey publicKey() {
		return keyPair.getPublic();
	}

	/**
	 * Recovers the shared secret from the sender's encapsulation message.
	 *
	 * @param encapsulation the byte message produced by the sender
	 * @return the receiver-side shared secret
	 * @throws NoSuchAlgorithmException if DHKEM is not available from the active providers
	 * @throws InvalidKeyException if the receiver private key cannot be used for DHKEM
	 * @throws DecapsulateException if the encapsulation message cannot be decapsulated
	 */
	public SecretKey decapsulate(byte[] encapsulation)
			throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {
		var kem = KEM.getInstance(KEM_ALGORITHM);
		var decapsulator = kem.newDecapsulator(keyPair.getPrivate());
		return decapsulator.decapsulate(encapsulation);
	}
}
