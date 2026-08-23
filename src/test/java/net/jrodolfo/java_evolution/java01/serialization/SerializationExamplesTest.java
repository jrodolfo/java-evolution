package net.jrodolfo.java_evolution.java01.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SerializationExamplesTest {

	private final SerializationExamples examples = new SerializationExamples();

	@Test
	void serializableObjectCanRoundTripThroughBytes() throws Exception {
		SerializationExamples.ProjectSnapshot original = snapshot();

		SerializationExamples.ProjectSnapshot restored = examples.roundTrip(original);

		assertThat(restored.projectName())
				.as("Serializable objects can be written to bytes and reconstructed later")
				.isEqualTo("java-evolution");
		assertThat(restored.taskCount())
				.as("Normal serializable fields should survive the object stream round trip")
				.isEqualTo(26);
	}

	@Test
	void objectGraphPreservesNestedSerializableObjects() throws Exception {
		SerializationExamples.ProjectSnapshot restored = examples.roundTrip(snapshot());

		assertThat(restored.owner().username())
				.as("Serialization follows reachable serializable objects in the object graph")
				.isEqualTo("jrodolfo");
	}

	@Test
	void transientFieldsAreNotSerialized() throws Exception {
		SerializationExamples.ProjectSnapshot original = snapshot();

		SerializationExamples.ProjectSnapshot restored = examples.roundTrip(original);

		assertThat(original.cachedSummary())
				.as("The original object computes a cached value before serialization")
				.isEqualTo("java-evolution has 26 tasks");
		assertThat(restored.cachedSummary())
				.as("transient fields are intentionally excluded from the serialized form")
				.isNull();
	}

	@Test
	void deserializationReturnsANewObject() throws Exception {
		SerializationExamples.ProjectSnapshot original = snapshot();

		SerializationExamples.ProjectSnapshot restored = examples.roundTrip(original);

		assertThat(restored)
				.as("Deserialization reconstructs an object with similar state, not the same identity")
				.isNotSameAs(original);
		assertThat(restored.owner())
				.as("Nested objects are reconstructed too")
				.isNotSameAs(original.owner());
	}

	private SerializationExamples.ProjectSnapshot snapshot() {
		return new SerializationExamples.ProjectSnapshot(
				"java-evolution", new SerializationExamples.User("jrodolfo"), 26);
	}
}
