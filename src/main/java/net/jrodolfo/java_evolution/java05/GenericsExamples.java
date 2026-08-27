package net.jrodolfo.java_evolution.java05;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates generics, introduced in Java 5.
 *
 * <p>
 * Before generics, collections usually stored {@link Object} values. Callers had
 * to cast values back to the expected type, so type mistakes could survive until
 * runtime. Generics move that check into the type system.
 * </p>
 */
public class GenericsExamples {

	/**
	 * Creates a typed list of names.
	 *
	 * @param names names to copy
	 * @return a new list whose element type is known to the compiler
	 */
	public List<String> typedNames(List<String> names) {
		return new ArrayList<String>(names);
	}

	/**
	 * Returns the first element from a typed list without a cast.
	 *
	 * @param <T> the list element type
	 * @param values values to inspect
	 * @return the first value
	 */
	public <T> T first(List<T> values) {
		return values.get(0);
	}

	/**
	 * Shows the old raw-list shape that generics were designed to replace.
	 *
	 * @param rawValues raw values without an element type
	 * @return the first value cast to {@link String}
	 */
	public String firstRawValue(List rawValues) {
		return (String) rawValues.get(0);
	}

	/**
	 * Reads feature importance from any iterable producer of {@link ReleaseFeature}
	 * values or subtypes.
	 *
	 * <p>
	 * The {@code ? extends ReleaseFeature} wildcard says this method only reads
	 * feature values. That makes the API flexible: callers can pass
	 * {@code Iterable<LanguageFeature>} or {@code Iterable<PlatformFeature>} without
	 * copying those values into a less specific collection first.
	 * </p>
	 *
	 * @param features features to inspect
	 * @return total importance score
	 */
	public int totalImportance(Iterable<? extends ReleaseFeature> features) {
		int total = 0;
		for (ReleaseFeature feature : features) {
			total += feature.importance();
		}
		return total;
	}

	/**
	 * Describes a value whose type parameter has multiple bounds.
	 *
	 * <p>
	 * {@code <T extends NamedFeature & PrioritizedFeature>} is a multiple-bound type
	 * parameter. It lets the method require both capabilities without inventing a
	 * separate combined interface. This is one place where Java exposes an
	 * intersection-type-style constraint in normal source code.
	 * </p>
	 *
	 * @param <T> type that satisfies both bounds
	 * @param feature feature that has both a name and a priority
	 * @return human-readable feature description
	 */
	public <T extends NamedFeature & PrioritizedFeature> String describePrioritizedFeature(T feature) {
		return feature.name() + " has priority " + feature.priority();
	}

	/**
	 * Base type for the bounded wildcard example.
	 */
	public interface ReleaseFeature {

		/**
		 * Returns a small teaching-oriented importance score.
		 *
		 * @return importance score
		 */
		int importance();
	}

	/**
	 * Language-level feature used by the wildcard example.
	 */
	public static class LanguageFeature implements ReleaseFeature {

		private final int importance;

		public LanguageFeature(int importance) {
			this.importance = importance;
		}

		public int importance() {
			return importance;
		}
	}

	/**
	 * Platform-level feature used by the wildcard example.
	 */
	public static class PlatformFeature implements ReleaseFeature {

		private final int importance;

		public PlatformFeature(int importance) {
			this.importance = importance;
		}

		public int importance() {
			return importance;
		}
	}

	/**
	 * First capability required by the multiple-bound example.
	 */
	public interface NamedFeature {

		/**
		 * Returns the feature name.
		 *
		 * @return feature name
		 */
		String name();
	}

	/**
	 * Second capability required by the multiple-bound example.
	 */
	public interface PrioritizedFeature {

		/**
		 * Returns the feature priority.
		 *
		 * @return feature priority
		 */
		int priority();
	}

	/**
	 * Concrete type that satisfies both bounds.
	 */
	public static class RankedFeature implements NamedFeature, PrioritizedFeature {

		private final String name;

		private final int priority;

		public RankedFeature(String name, int priority) {
			this.name = name;
			this.priority = priority;
		}

		public String name() {
			return name;
		}

		public int priority() {
			return priority;
		}
	}
}
