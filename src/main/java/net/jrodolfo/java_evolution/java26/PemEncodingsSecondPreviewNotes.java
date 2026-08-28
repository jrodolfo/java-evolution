package net.jrodolfo.java_evolution.java26;

/**
 * Explains the second preview of PEM encodings of cryptographic objects.
 *
 * <p>
 * PEM originally stood for Privacy-Enhanced Mail. In modern Java security work,
 * it is a common text envelope for keys, certificates, certificate requests,
 * and certificate revocation lists. Java 26 continues the preview API first
 * introduced in Java 25.
 * </p>
 */
public class PemEncodingsSecondPreviewNotes {

	/**
	 * Describes the feature goal.
	 *
	 * @return a short feature explanation
	 */
	public String purpose() {
		return "standardize reading and writing cryptographic objects using PEM text encodings";
	}

	/**
	 * Describes the second-preview status.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "PEM encodings are a second preview API in Java 26 after previewing in Java 25";
	}

	/**
	 * Explains why this feature is not compiled as a normal example yet.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 26 preview API remains notes-only until a focused executable-example review";
	}
}
