package net.jrodolfo.java_evolution.java16;

/**
 * Explains Unix-domain socket channel support, introduced in Java 16.
 *
 * <p>
 * Before Java 16, Java's standard socket channel APIs focused on network
 * sockets. Local inter-process communication through Unix-domain sockets
 * required platform-specific approaches or non-standard libraries.
 * </p>
 *
 * <p>
 * Java 16 added standard Unix-domain socket channel support. The API is
 * platform-dependent, so this repository keeps the example explanatory instead
 * of opening OS-specific sockets in unit tests.
 * </p>
 */
public class UnixDomainSocketChannelNotes {

	/**
	 * Names the standard protocol family.
	 *
	 * @return the protocol family name
	 */
	public String protocolFamily() {
		return "StandardProtocolFamily.UNIX";
	}

	/**
	 * Explains the typical use case.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "Unix-domain socket channels support local inter-process communication without TCP ports";
	}

	/**
	 * Explains why this repository keeps the example as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the example is documented as notes because socket behavior depends on operating system support";
	}
}
