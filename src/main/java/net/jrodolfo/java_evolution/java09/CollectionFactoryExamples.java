package net.jrodolfo.java_evolution.java09;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates collection factory methods introduced in Java 9.
 *
 * <p>
 * Before Java 9, creating a small read-only collection usually required
 * several steps: create a mutable collection, populate it, and wrap it with an
 * unmodifiable view. That made simple constants and test data noisier than the
 * values being represented.
 * </p>
 *
 * <p>
 * {@link List#of(Object[])}, {@link Set#of(Object[])}, and
 * {@link Map#of(Object, Object)} solve that problem for small collections.
 * They are compact, reject {@code null}, reject duplicate set elements or map
 * keys, and return collections that cannot be mutated.
 * </p>
 */
public class CollectionFactoryExamples {

	/**
	 * Creates an immutable list.
	 *
	 * @return a list created with {@link List#of(Object[])}
	 */
	public List<String> languageFeatures() {
		return List.of("modules", "collection factories", "stream enhancements");
	}

	/**
	 * Creates an immutable set.
	 *
	 * @return a set created with {@link Set#of(Object[])}
	 */
	public Set<String> languageFeatureCategories() {
		return Set.of("language", "library", "runtime");
	}

	/**
	 * Creates an immutable map.
	 *
	 * @return a map created with {@link Map#of(Object, Object, Object, Object)}
	 */
	public Map<Integer, String> releaseNamesByVersion() {
		return Map.of(
				8, "Java 8",
				9, "Java 9");
	}

	/**
	 * Shows that factory collections reject {@code null}.
	 *
	 * @return never returns because {@link List#of(Object[])} throws
	 */
	public List<String> listWithNullValue() {
		return List.of("Java 9", null);
	}

	/**
	 * Shows that {@link Set#of(Object[])} rejects duplicates.
	 *
	 * @return never returns because the set contains duplicate values
	 */
	public Set<String> setWithDuplicateValues() {
		return Set.of("modules", "modules");
	}

	/**
	 * Shows that {@link Map#of(Object, Object, Object, Object)} rejects duplicate
	 * keys.
	 *
	 * @return never returns because the map contains duplicate keys
	 */
	public Map<Integer, String> mapWithDuplicateKeys() {
		return Map.of(
				9, "modules",
				9, "collection factories");
	}
}
