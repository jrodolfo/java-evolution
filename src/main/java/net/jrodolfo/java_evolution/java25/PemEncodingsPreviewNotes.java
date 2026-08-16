package net.jrodolfo.java_evolution.java25;

/**
 * Explains PEM encodings of cryptographic objects as a Java 25 preview feature.
 *
 * <p>
 * PEM originally stood for Privacy-Enhanced Mail, but today it is widely used
 * as a text transport format for cryptographic objects such as public keys,
 * private keys, certificates, and certificate revocation lists.
 * </p>
 *
 * <p>
 * A PEM text wraps Base64-encoded binary data in a readable header and footer,
 * such as {@code -----BEGIN PUBLIC KEY-----} and
 * {@code -----END PUBLIC KEY-----}. Java 25 previewed APIs for converting
 * between PEM text and Java cryptographic objects without requiring every
 * application to hand-roll parsing and encoding logic.
 * </p>
 */
public class PemEncodingsPreviewNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "read and write cryptographic objects using Privacy-Enhanced Mail (PEM) text encodings";
	}

	/**
	 * Describes the visible shape of PEM text.
	 *
	 * @return a short PEM format explanation
	 */
	public String formatShape() {
		return "PEM text uses BEGIN and END headers around Base64-encoded cryptographic object data";
	}

	/**
	 * Describes the Java 25 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "PEM encodings of cryptographic objects are preview API in Java 25";
	}
}
