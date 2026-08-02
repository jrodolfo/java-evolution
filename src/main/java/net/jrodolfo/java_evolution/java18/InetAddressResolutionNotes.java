package net.jrodolfo.java_evolution.java18;

/**
 * Explains the Internet-address resolution SPI introduced in Java 18.
 *
 * <p>
 * Most applications can rely on the operating system for host-name resolution,
 * but advanced environments sometimes need custom behavior for service
 * discovery, controlled DNS, or testing infrastructure. Java 18 introduced a
 * service-provider interface so this kind of resolver can be provided through a
 * supported JDK extension point.
 * </p>
 *
 * <p>
 * Because a resolver provider affects process-wide networking behavior, this
 * repository keeps the topic as notes instead of installing a live provider
 * during tests.
 * </p>
 */
public class InetAddressResolutionNotes {

	/**
	 * Names the extension point.
	 *
	 * @return a short API description
	 */
	public String extensionPoint() {
		return "InetAddress resolver service-provider interface";
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
