package net.jrodolfo.java_evolution.java10;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UnmodifiableCollectorsExamplesTest {

	private final UnmodifiableCollectorsExamples examples = new UnmodifiableCollectorsExamples();

	@Test
	void toUnmodifiableListCollectsValuesAndRejectsMutation() {
		// Given
		List<UnmodifiableCollectorsExamples.Feature> features = exampleFeatures();

		// When
		List<String> names = examples.activeFeatureNames(features);

		// Then
		assertThat(names)
				.as("toUnmodifiableList should collect active feature names")
				.containsExactly("var", "orElseThrow");
		assertThatThrownBy(() -> names.add("another feature"))
				.as("The collected list should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void toUnmodifiableSetCollectsUniqueValuesAndRejectsMutation() {
		// Given
		List<UnmodifiableCollectorsExamples.Feature> features = exampleFeatures();

		// When
		Set<String> categories = examples.featureCategories(features);

		// Then
		assertThat(categories)
				.as("toUnmodifiableSet should collect unique categories")
				.containsExactlyInAnyOrder("language", "library", "runtime");
		assertThatThrownBy(() -> categories.add("runtime"))
				.as("The collected set should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void toUnmodifiableMapCollectsEntriesAndRejectsMutation() {
		// Given
		List<UnmodifiableCollectorsExamples.Feature> features = exampleFeatures();

		// When
		Map<String, String> categoriesByName = examples.categoriesByFeatureName(features);

		// Then
		assertThat(categoriesByName)
				.as("toUnmodifiableMap should collect feature names and categories")
				.containsEntry("var", "language")
				.containsEntry("orElseThrow", "library");
		assertThatThrownBy(() -> categoriesByName.put("new feature", "library"))
				.as("The collected map should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void toUnmodifiableMapRejectsDuplicateKeys() {
		// Given
		List<UnmodifiableCollectorsExamples.Feature> features = Arrays.asList(
				new UnmodifiableCollectorsExamples.Feature("var", "language", true),
				new UnmodifiableCollectorsExamples.Feature("var", "keyword", true));

		// When / Then
		assertThatThrownBy(() -> examples.categoriesByFeatureNameWithDuplicateKey(features))
				.as("toUnmodifiableMap should reject duplicate keys unless a merge function is supplied")
				.isInstanceOf(IllegalStateException.class);
	}

	private List<UnmodifiableCollectorsExamples.Feature> exampleFeatures() {
		return Arrays.asList(
				new UnmodifiableCollectorsExamples.Feature("var", "language", true),
				new UnmodifiableCollectorsExamples.Feature("orElseThrow", "library", true),
				new UnmodifiableCollectorsExamples.Feature("application class-data sharing", "runtime", false));
	}
}
