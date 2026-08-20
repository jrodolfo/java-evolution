package net.jrodolfo.java_evolution.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates the Collections Framework introduced in Java 2.
 */
public class CollectionsFrameworkExamples {

	/**
	 * Uses a {@link List} when duplicates and insertion order matter.
	 *
	 * @return ordered names including duplicates
	 */
	public List<String> orderedNames() {
		List<String> names = new ArrayList<>();
		names.add("Ana");
		names.add("Maria");
		names.add("Ana");
		return names;
	}

	/**
	 * Uses a {@link Set} when uniqueness matters.
	 *
	 * @return unique names
	 */
	public Set<String> uniqueNames() {
		Set<String> names = new HashSet<>();
		names.add("Ana");
		names.add("Maria");
		names.add("Ana");
		return names;
	}

	/**
	 * Uses a {@link Map} for key/value lookup.
	 *
	 * @return release descriptions by version number
	 */
	public Map<Integer, String> releaseDescriptions() {
		Map<Integer, String> releases = new HashMap<>();
		releases.put(1, "foundations");
		releases.put(2, "collections framework");
		return releases;
	}
}
