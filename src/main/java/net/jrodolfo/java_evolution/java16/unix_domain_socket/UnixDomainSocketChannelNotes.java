package net.jrodolfo.java_evolution.java16.unix_domain_socket;

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
 * Java 16 added standard Unix-domain socket channel support through
 * {@code StandardProtocolFamily.UNIX}. This lets Java code use a file-system
 * path as the local socket address when the operating system supports that
 * style of communication.
 * </p>
 */
public class UnixDomainSocketChannelNotes {

	/**
	 * Explains the local communication problem this feature solves.
	 *
	 * @return a short problem statement
	 */
	public String problemSolved() {
		return "two processes on the same machine sometimes need to communicate without opening a TCP port";
	}

	/**
	 * Names the standard protocol family.
	 *
	 * @return the protocol family name
	 */
	public String protocolFamily() {
		return "StandardProtocolFamily.UNIX";
	}

	/**
	 * Explains the address shape used by Unix-domain sockets.
	 *
	 * @return a short address explanation
	 */
	public String addressShape() {
		return "a Unix-domain socket address is represented by a local file-system path";
	}

	/**
	 * Contrasts Unix-domain sockets with TCP sockets.
	 *
	 * @return a short contrast
	 */
	public String comparedWithTcp() {
		return "TCP sockets use host and port addresses, while Unix-domain sockets use local path addresses";
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
