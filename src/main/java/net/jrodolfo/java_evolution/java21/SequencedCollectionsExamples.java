package net.jrodolfo.java_evolution.java21;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;

/**
 * Demonstrates sequenced collections, introduced in Java 21.
 *
 * <p>
 * Java had many ordered collections before Java 21, but there was no shared API
 * for first element, last element, or reversed views. Generic code had to know
 * whether it was dealing with a {@link List}, deque, ordered set, or ordered
 * map.
 * </p>
 *
 * <p>
 * Sequenced collection interfaces give encounter-ordered data structures a
 * common vocabulary. The {@code reversed()} operation returns a reversed view
 * rather than requiring callers to make a defensive copy just to look at the
 * same data from the other direction.
 * </p>
 */
public class SequencedCollectionsExamples {

	/**
	 * Uses first, last, and reversed operations from {@link SequencedCollection}.
	 *
	 * @param values ordered values
	 * @return a summary of sequence operations
	 */
	public String summarizeSequence(List<String> values) {
		SequencedCollection<String> sequence = new ArrayList<>(values);
		return sequence.getFirst() + " -> " + sequence.getLast() + " | reversed=" + sequence.reversed();
	}

	/**
	 * Uses first and last entry operations from {@link SequencedMap}.
	 *
	 * @return a summary of map encounter order
	 */
	public String summarizeMapOrder() {
		SequencedMap<Integer, String> releases = new LinkedHashMap<>();
		releases.put(17, "LTS");
		releases.put(21, "LTS");
		releases.put(25, "current");
		return releases.firstEntry().getKey() + " to " + releases.lastEntry().getKey();
	}
}
