package net.jrodolfo.java_evolution.java01.rmi;

/**
 * Explains Remote Method Invocation, introduced in Java 1.1.
 */
public class RmiNotes {

	public String problemSolved() {
		return "one JVM needed a standard way to call Java objects hosted in another JVM";
	}

	public String coreConcepts() {
		return "remote interfaces, remote objects, stubs, registries, RemoteException, and serialization-based argument passing";
	}

	public String repositoryDecision() {
		return "a faithful RMI demo needs a registry, network binding, exported objects, and remote failure handling";
	}
}
