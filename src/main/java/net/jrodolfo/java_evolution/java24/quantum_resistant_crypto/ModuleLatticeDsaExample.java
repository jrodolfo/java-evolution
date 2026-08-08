package net.jrodolfo.java_evolution.java24.quantum_resistant_crypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Demonstrates Java 24 module-lattice digital signature support.
 *
 * <p>
 * ML-DSA is a post-quantum digital signature algorithm. A private key signs a
 * message, and the matching public key verifies that the signature belongs to
 * the original message. The example also verifies that a tampered message is
 * rejected.
 * </p>
 */
public class ModuleLatticeDsaExample {

	/**
	 * Standard algorithm name for module-lattice digital signatures.
	 */
	public static final String ALGORITHM = "ML-DSA";

	/**
	 * Signs a message and verifies both the original and a tampered message.
	 *
	 * @param message the message to sign
	 * @return the signature bytes and verification outcomes
	 * @throws NoSuchAlgorithmException if ML-DSA is not available from the active providers
	 * @throws InvalidKeyException if generated keys cannot be used by the signature provider
	 * @throws SignatureException if the signature operation fails
	 */
	public ModuleLatticeDsaResult signAndVerify(String message)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		var keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		var keyPair = keyPairGenerator.generateKeyPair();

		var signature = Signature.getInstance(ALGORITHM);
		var messageBytes = message.getBytes(StandardCharsets.UTF_8);

		signature.initSign(keyPair.getPrivate());
		signature.update(messageBytes);
		var signatureBytes = signature.sign();

		var verifiesOriginal = verifies(keyPair.getPublic(), messageBytes, signatureBytes);
		var rejectsTampered = !verifies(
				keyPair.getPublic(),
				(message + " tampered").getBytes(StandardCharsets.UTF_8),
				signatureBytes);

		return new ModuleLatticeDsaResult(
				signature.getAlgorithm(),
				message,
				signatureBytes,
				verifiesOriginal,
				rejectsTampered);
	}

	private boolean verifies(java.security.PublicKey publicKey, byte[] message, byte[] signatureBytes)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		var verifier = Signature.getInstance(ALGORITHM);
		verifier.initVerify(publicKey);
		verifier.update(message);
		return verifier.verify(signatureBytes);
	}
}
