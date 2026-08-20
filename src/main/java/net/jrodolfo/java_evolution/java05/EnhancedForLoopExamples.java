package net.jrodolfo.java_evolution.java05;

import java.util.Iterator;
import java.util.List;

/**
 * Demonstrates the enhanced {@code for} loop introduced in Java 5.
 */
public class EnhancedForLoopExamples {

	/**
	 * Counts total characters using the enhanced {@code for} loop.
	 *
	 * @param names names to inspect
	 * @return total character count
	 */
	public int totalLengthWithEnhancedFor(List<String> names) {
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
	public int totalLengthWithIterator(List<String> names) {
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
}
