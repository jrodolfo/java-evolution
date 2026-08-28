package net.jrodolfo.java_evolution.java26;

/**
 * Explains Java 26 HTTP/3 support in the HTTP Client API.
 *
 * <p>
 * Java 11 standardized the HTTP Client API. Java 26 extends that client with
 * HTTP/3 support, letting applications request the newer protocol through the
 * same client model instead of switching to a separate networking library.
 * </p>
 */
public class Http3ClientNotes {

	/**
	 * Explains the problem addressed by HTTP/3 support.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "applications using Java's standard HTTP Client previously had no built-in HTTP/3 protocol option";
	}

	/**
	 * Describes the protocol relationship.
	 *
	 * @return a short protocol explanation
	 */
	public String protocolContext() {
		return "HTTP/3 is the HTTP mapping that runs over QUIC rather than TCP";
	}

	/**
	 * Explains why this repository keeps the feature notes-only for now.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps HTTP/3 as notes until it has a focused executable-example feasibility review";
	}
}
