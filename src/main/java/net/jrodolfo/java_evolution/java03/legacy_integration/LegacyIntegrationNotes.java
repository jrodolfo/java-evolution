package net.jrodolfo.java_evolution.java03.legacy_integration;

/**
 * Explains legacy integration context around J2SE 1.3.
 */
public class LegacyIntegrationNotes {

	public String historicalContext() {
		return "Java was expanding into enterprise and distributed systems with applets, plugins, RMI/IIOP, CORBA, and integration APIs";
	}

	public String modernCaveat() {
		return "some APIs from this era were later removed from the JDK or moved to explicit dependencies";
	}

	public String repositoryDecision() {
		return "CORBA, plugin, and multi-process enterprise setups are not portable compact examples on JDK 25";
	}
}
