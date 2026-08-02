package net.jrodolfo.java_evolution.java10;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Demonstrates unmodifiable collectors introduced in Java 10.
 *
 * <p>
 * Java 8 streams made it easy to collect pipeline results, but the standard
 * collectors returned mutable collections. Code that wanted to return a
 * read-only result needed an extra wrapping or copying step.
 * </p>
 *
 * <p>
 * Java 10 added {@link Collectors#toUnmodifiableList()},
 * {@link Collectors#toUnmodifiableSet()}, and
 * {@link Collectors#toUnmodifiableMap(Function, Function)}. These collectors
 * produce collections that reject mutation after the stream pipeline completes.
 * </p>
 */
public class UnmodifiableCollectorsExamples {

	/**
	 * Collects active feature names into an unmodifiable list.
	 *
	 * @param features the features to inspect
	 * @return active feature names in encounter order
	 */
	public List<String> activeFeatureNames(List<Feature> features) {
		return features.stream()
				.filter(Feature::active)
				.map(Feature::name)
				.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Collects feature categories into an unmodifiable set.
	 *
	 * @param features the features to inspect
	 * @return unique categories from the feature list
	 */
	public Set<String> featureCategories(List<Feature> features) {
		return features.stream()
				.map(Feature::category)
				.collect(Collectors.toUnmodifiableSet());
	}

	/**
	 * Collects features into an unmodifiable map keyed by feature name.
	 *
	 * @param features the features to index
	 * @return an unmodifiable map from feature name to category
	 */
	public Map<String, String> categoriesByFeatureName(List<Feature> features) {
		return features.stream()
				.collect(Collectors.toUnmodifiableMap(Feature::name, Feature::category));
	}

	/**
	 * Attempts to collect duplicate keys into an unmodifiable map.
	 *
	 * @param features the features to index
	 * @return never returns when duplicate feature names exist
	 */
	public Map<String, String> categoriesByFeatureNameWithDuplicateKey(List<Feature> features) {
		return features.stream()
				.collect(Collectors.toUnmodifiableMap(Feature::name, Feature::category));
	}

	/**
	 * Small Java 10-style data class used by the collector examples.
	 */
	public static class Feature {
		private final String name;
		private final String category;
		private final boolean active;

		/**
		 * Creates a feature used by unmodifiable collector examples.
		 *
		 * @param name the feature name
		 * @param category the feature category
		 * @param active whether the feature is active
		 */
		public Feature(String name, String category, boolean active) {
			this.name = name;
			this.category = category;
			this.active = active;
		}

		/**
		 * @return the feature name
		 */
		public String name() {
			return name;
		}

		/**
		 * @return the feature category
		 */
		public String category() {
			return category;
		}

		/**
		 * @return whether the feature is active in the example
		 */
		public boolean active() {
			return active;
		}
	}
}
