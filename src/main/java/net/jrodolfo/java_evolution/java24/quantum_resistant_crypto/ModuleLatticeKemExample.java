package net.jrodolfo.java_evolution.java24.quantum_resistant_crypto;

import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;

/**
 * Demonstrates Java 24 module-lattice key encapsulation support.
 *
 * <p>
 * ML-KEM is a post-quantum key encapsulation mechanism. Like the Java 21 KEM
 * example, the sender uses the receiver's public key to create a shared secret
 * and an encapsulation message. The receiver uses the private key and the
 * encapsulation message to recover the same shared secret.
 * </p>
 */
public class ModuleLatticeKemExample {

	/**
	 * Standard algorithm name for module-lattice key encapsulation.
	 */
	public static final String ALGORITHM = "ML-KEM";

	/**
	 * Runs a complete in-memory ML-KEM exchange.
	 *
	 * @return sender secret, receiver secret, and encapsulation bytes
	 * @throws NoSuchAlgorithmException if ML-KEM is not available from the active providers
	 * @throws InvalidKeyException if generated keys cannot be used by the KEM provider
	 * @throws DecapsulateException if the encapsulation cannot be decoded by the receiver
	 */
	public ModuleLatticeKemResult exchange()
			throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {
		var keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		var receiverKeys = keyPairGenerator.generateKeyPair();

		var kem = KEM.getInstance(ALGORITHM);
		var sender = kem.newEncapsulator(receiverKeys.getPublic());
		var encapsulated = sender.encapsulate();

		var receiver = kem.newDecapsulator(receiverKeys.getPrivate());
		var receiverSecret = receiver.decapsulate(encapsulated.encapsulation());

		return new ModuleLatticeKemResult(
				encapsulated.key().getEncoded(),
				receiverSecret.getEncoded(),
				encapsulated.encapsulation(),
				kem.getAlgorithm());
	}
}
