package net.jrodolfo.java_evolution.java05;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Demonstrates the enhanced {@code for} loop introduced in Java 5.
 */
public class EnhancedForLoopExamples {

	/**
	 * Counts total characters using the enhanced {@code for} loop.
	 *
	 * <p>
	 * The enhanced {@code for} loop only needs an {@link Iterable}; callers can pass
	 * a {@code List}, a {@code Set}, or their own iterable type without converting
	 * it to a narrower collection type.
	 * </p>
	 *
	 * @param names names to inspect
	 * @return total character count
	 */
	public int totalLengthWithEnhancedFor(Iterable<String> names) {
		int totalLength = 0;

		for (String name : names) {
			totalLength += name.length();
		}

		return totalLength;
	}

	/**
	 * Counts total characters using the older explicit iterator style.
	 *
	 * @param names names to inspect
	 * @return total character count
	 */
	public int totalLengthWithIterator(Iterable<String> names) {
		int totalLength = 0;
		Iterator<String> iterator = names.iterator();

		while (iterator.hasNext()) {
			String name = iterator.next();
			totalLength += name.length();
		}

		return totalLength;
	}

	/**
	 * Sums an array using enhanced {@code for}; the feature works for arrays as
	 * well as {@link Iterable} values.
	 *
	 * @param values values to sum
	 * @return the sum
	 */
	public int sumArray(int[] values) {
		int sum = 0;

		for (int value : values) {
			sum += value;
		}

		return sum;
	}

	/**
	 * Creates a small custom {@link Iterable} to show how application classes can
	 * become enhanced-for friendly.
	 *
	 * @param first first release name
	 * @param second second release name
	 * @return an iterable pair of release names
	 */
	public Iterable<String> releaseNames(String first, String second) {
		return new ReleaseNames(first, second);
	}

	/**
	 * Minimal custom iterable used by the enhanced-for examples.
	 */
	public static class ReleaseNames implements Iterable<String> {

		private final String[] names;

		public ReleaseNames(String first, String second) {
			this.names = new String[] { first, second };
		}

		public Iterator<String> iterator() {
			return new Iterator<String>() {
				private int index;

				public boolean hasNext() {
					return index < names.length;
				}

				public String next() {
					if (!hasNext()) {
						throw new NoSuchElementException();
					}
					String name = names[index];
					index++;
					return name;
				}

				public void remove() {
					throw new UnsupportedOperationException("release names are read-only");
				}
			};
		}
	}
}
