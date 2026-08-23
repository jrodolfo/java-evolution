package net.jrodolfo.java_evolution.java01.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Demonstrates object serialization, introduced in Java 1.1.
 */
public class SerializationExamples {

	/**
	 * Serializes a trusted in-memory object to bytes.
	 *
	 * @param snapshot object to serialize
	 * @return serialized object bytes
	 * @throws IOException when the object cannot be written
	 */
	public byte[] serialize(ProjectSnapshot snapshot) throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream objects = new ObjectOutputStream(bytes);
		objects.writeObject(snapshot);
		// Manual close keeps this Java 1.1-era example visually close to pre-try-with-resources code.
		objects.close();
		return bytes.toByteArray();
	}

	/**
	 * Deserializes bytes produced by this example.
	 *
	 * @param bytes trusted serialized object bytes
	 * @return deserialized project snapshot
	 * @throws IOException when the bytes cannot be read
	 * @throws ClassNotFoundException when the serialized class is unavailable
	 */
	public ProjectSnapshot deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ObjectInputStream objects = new ObjectInputStream(new ByteArrayInputStream(bytes));
		ProjectSnapshot snapshot = (ProjectSnapshot) objects.readObject();
		// Manual close keeps this Java 1.1-era example visually close to pre-try-with-resources code.
		objects.close();
		return snapshot;
	}

	/**
	 * Serializes and deserializes the object once.
	 *
	 * @param snapshot object to round-trip
	 * @return reconstructed object
	 * @throws IOException when serialization fails
	 * @throws ClassNotFoundException when deserialization cannot load the class
	 */
	public ProjectSnapshot roundTrip(ProjectSnapshot snapshot) throws IOException, ClassNotFoundException {
		return deserialize(serialize(snapshot));
	}

	/**
	 * Small serializable object graph used by the examples.
	 */
	public static class ProjectSnapshot implements Serializable {

		private static final long serialVersionUID = 1L;

		private final String projectName;
		private final User owner;
		private final int taskCount;
		private transient String cachedSummary;

		public ProjectSnapshot(String projectName, User owner, int taskCount) {
			this.projectName = projectName;
			this.owner = owner;
			this.taskCount = taskCount;
			this.cachedSummary = projectName + " has " + taskCount + " tasks";
		}

		public String projectName() {
			return projectName;
		}

		public User owner() {
			return owner;
		}

		public int taskCount() {
			return taskCount;
		}

		public String cachedSummary() {
			return cachedSummary;
		}
	}

	/**
	 * Nested serializable object reached from the root snapshot.
	 */
	public static class User implements Serializable {

		private static final long serialVersionUID = 1L;

		private final String username;

		public User(String username) {
			this.username = username;
		}

		public String username() {
			return username;
		}
	}
}
