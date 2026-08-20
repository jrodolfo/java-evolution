package net.jrodolfo.java_evolution.java01.serialization;

/**
 * Explains object serialization, introduced in Java 1.1.
 */
public class SerializationNotes {

	public String problemSolved() {
		return "early Java systems needed to write object graphs to streams for persistence, messaging, and RMI";
	}

	public String coreApi() {
		return "Serializable marks opt-in classes, ObjectOutputStream writes objects, and ObjectInputStream reconstructs objects";
	}

	public String modernCaution() {
		return "deserializing untrusted data is dangerous, and serialized forms become compatibility contracts";
	}
}
