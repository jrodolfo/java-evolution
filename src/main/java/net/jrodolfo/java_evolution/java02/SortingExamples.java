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
	public List<Integer> sortNaturally(List<Integer> numbers) {
		List<Integer> copy = new ArrayList<>(numbers);
		Collections.sort(copy);
		return copy;
	}

	/**
	 * Sorts names by length using a comparator object.
	 *
	 * @param names names to sort
	 * @return names from shortest to longest
	 */
	public List<String> sortByLength(List<String> names) {
		List<String> copy = new ArrayList<>(names);
		Collections.sort(copy, new Comparator<String>() {
			@Override
			public int compare(String left, String right) {
				return Integer.compare(left.length(), right.length());
			}
		});
		return copy;
	}
}
