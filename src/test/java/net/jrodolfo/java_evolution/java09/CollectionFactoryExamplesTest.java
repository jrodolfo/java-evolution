package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CollectionFactoryExamplesTest {

	private final CollectionFactoryExamples examples = new CollectionFactoryExamples();

	@Test
	void listOfCreatesAnImmutableList() {
		// When
		List<String> features = examples.languageFeatures();

		// Then
		assertThat(features)
				.as("List.of should create a compact immutable list")
				.containsExactly("modules", "collection factories", "stream enhancements");
		assertThatThrownBy(() -> features.add("another feature"))
				.as("The list returned by List.of should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void setOfCreatesAnImmutableSet() {
		// When
		Set<String> categories = examples.languageFeatureCategories();

		// Then
		assertThat(categories)
				.as("Set.of should create a compact immutable set")
				.containsExactlyInAnyOrder("language", "library", "runtime");
		assertThatThrownBy(() -> categories.add("tooling"))
				.as("The set returned by Set.of should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void mapOfCreatesAnImmutableMap() {
		// When
		Map<Integer, String> releases = examples.releaseNamesByVersion();

		// Then
		assertThat(releases)
				.as("Map.of should create a compact immutable map")
				.containsEntry(8, "Java 8")
				.containsEntry(9, "Java 9");
		assertThatThrownBy(() -> releases.put(10, "Java 10"))
				.as("The map returned by Map.of should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void collectionFactoriesRejectNullValues() {
		assertThatThrownBy(examples::listWithNullValue)
				.as("List.of should reject null elements immediately")
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	void setFactoryRejectsDuplicateValues() {
		assertThatThrownBy(examples::setWithDuplicateValues)
				.as("Set.of should reject duplicate elements immediately")
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void mapFactoryRejectsDuplicateKeys() {
		assertThatThrownBy(examples::mapWithDuplicateKeys)
				.as("Map.of should reject duplicate keys immediately")
				.isInstanceOf(IllegalArgumentException.class);
	}
}
