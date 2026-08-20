package net.jrodolfo.java_evolution.java18.inet_address_resolution;

/**
 * Explains the Internet-Address Resolution SPI introduced in Java 18.
 *
 * <p>
 * Most applications can rely on the operating system for host-name resolution.
 * Advanced environments sometimes need different behavior for controlled DNS,
 * service discovery, or testing infrastructure. Java 18 introduced a
 * service-provider interface so a library or runtime can provide that behavior
 * through a supported JDK extension point.
 * </p>
 *
 * <p>
 * A resolver provider participates in the resolution performed by
 * {@link java.net.InetAddress}. It is not a replacement for one isolated call:
 * installing a provider can affect name resolution throughout the running
 * process. This repository keeps the topic as notes instead of installing a
 * live provider during tests.
 * </p>
 */
public class InetAddressResolutionNotes {

	/**
	 * Names the extension point.
	 *
	 * @return a short API description
	 */
	public String extensionPoint() {
		return "InetAddressResolverProvider service-provider interface";
	}

	/**
	 * Explains the intended audience.
	 *
	 * @return a short explanation
	 */
	public String useCase() {
		return "custom DNS or address resolution for advanced networking environments";
	}

	/**
	 * Explains why this feature is not demonstrated with a live provider.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the repository documents the SPI without installing a process-wide resolver provider";
	}
}
