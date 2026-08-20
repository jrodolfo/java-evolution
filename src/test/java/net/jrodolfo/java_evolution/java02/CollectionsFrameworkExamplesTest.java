package net.jrodolfo.java_evolution.java02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CollectionsFrameworkExamplesTest {

	private final CollectionsFrameworkExamples examples = new CollectionsFrameworkExamples();

	@Test
	void listPreservesOrderAndDuplicates() {
		assertThat(examples.orderedNames())
				.as("List should preserve insertion order and duplicates")
				.containsExactly("Ana", "Maria", "Ana");
	}

	@Test
	void setKeepsUniqueElements() {
		assertThat(examples.uniqueNames())
				.as("Set should model uniqueness")
				.containsExactlyInAnyOrder("Ana", "Maria");
	}

	@Test
	void mapAssociatesKeysWithValues() {
		assertThat(examples.releaseDescriptions())
				.as("Map should provide key/value lookup")
				.containsEntry(2, "collections framework");
	}
}
