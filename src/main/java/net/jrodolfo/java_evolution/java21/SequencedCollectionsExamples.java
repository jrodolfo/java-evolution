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
	public SequenceSummary summarizeSequence(List<String> values) {
		SequencedCollection<String> sequence = new ArrayList<>(values);
		return new SequenceSummary(sequence.getFirst(), sequence.getLast(), List.copyOf(sequence.reversed()));
	}

	/**
	 * Uses first and last entry operations from {@link SequencedMap}.
	 *
	 * @return a summary of map encounter order
	 */
	public MapOrderSummary summarizeMapOrder() {
		SequencedMap<Integer, String> releases = new LinkedHashMap<>();
		releases.put(17, "LTS");
		releases.put(21, "LTS");
		releases.put(25, "current");
		var first = releases.firstEntry();
		var last = releases.lastEntry();
		return new MapOrderSummary(first.getKey(), first.getValue(), last.getKey(), last.getValue());
	}

	/**
	 * Captures direct observations from {@link SequencedCollection}.
	 *
	 * @param first the first element in encounter order
	 * @param last the last element in encounter order
	 * @param reversed the values observed through the reversed view
	 */
	public record SequenceSummary(String first, String last, List<String> reversed) {
	}

	/**
	 * Captures direct observations from {@link SequencedMap}.
	 *
	 * @param firstKey the first key in encounter order
	 * @param firstValue the first value in encounter order
	 * @param lastKey the last key in encounter order
	 * @param lastValue the last value in encounter order
	 */
	public record MapOrderSummary(int firstKey, String firstValue, int lastKey, String lastValue) {
	}
}
