package net.jrodolfo.java_evolution.java18;

/**
 * Explains the Internet-address resolution SPI introduced in Java 18.
 *
 * <p>
 * The feature allows custom name and address resolution providers. It is mostly
 * useful for advanced networking libraries and controlled runtime environments,
 * so this repository keeps the example as notes.
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
