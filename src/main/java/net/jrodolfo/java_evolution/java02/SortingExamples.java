package net.jrodolfo.java_evolution.java02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates Java 2 collection sorting with {@link Comparable} and
 * {@link Comparator}.
 */
public class SortingExamples {

	/**
	 * Sorts values by their natural ordering.
	 *
	 * @param numbers numbers to sort
	 * @return sorted numbers
	 */
	public List sortNaturally(List numbers) {
		List copy = new ArrayList(numbers);
		Collections.sort(copy);
		return copy;
	}

	/**
	 * Sorts names by length using a comparator object.
	 *
	 * @param names names to sort
	 * @return names from shortest to longest
	 */
	public List sortByLength(List names) {
		List copy = new ArrayList(names);
		Collections.sort(copy, new Comparator() {
			public int compare(Object leftValue, Object rightValue) {
				String left = (String) leftValue;
				String right = (String) rightValue;
				if (left.length() < right.length()) {
					return -1;
				}
				if (left.length() > right.length()) {
					return 1;
				}
				return 0;
			}
		});
		return copy;
	}
}
