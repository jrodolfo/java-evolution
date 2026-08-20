package net.jrodolfo.java_evolution.java06;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Demonstrates collection APIs introduced in Java 6: {@link NavigableSet},
 * {@link NavigableMap}, and {@link Deque}.
 */
public class NavigableCollectionExamples {

	/**
	 * Finds the nearest score greater than or equal to a target.
	 *
	 * @param scores sorted scores
	 * @param target target score
	 * @return nearest score at or above the target
	 */
	public Integer nearestPassingScore(NavigableSet<Integer> scores, int target) {
		return scores.ceiling(target);
	}

	/**
	 * Creates a sorted set and views it in descending order.
	 *
	 * @return descending release names
	 */
	public NavigableSet<String> releaseNamesDescending() {
		NavigableSet<String> releases = new TreeSet<String>();
		releases.add("java05");
		releases.add("java06");
		releases.add("java07");
		return releases.descendingSet();
	}

	/**
	 * Uses a navigable map to find the floor entry for a version number.
	 *
	 * @param requestedVersion requested version
	 * @return release name for the greatest known version at or below the request
	 */
	public String releaseAtOrBefore(int requestedVersion) {
		NavigableMap<Integer, String> releases = new TreeMap<Integer, String>();
		releases.put(5, "generics and concurrency utilities");
		releases.put(6, "scripting and compiler APIs");
		releases.put(7, "Project Coin and NIO.2");
		Map.Entry<Integer, String> entry = releases.floorEntry(requestedVersion);
		return entry != null ? entry.getValue() : null;
	}

	/**
	 * Uses a {@link Deque} as a double-ended queue.
	 *
	 * @return removal order after adding work to both ends
	 */
	public String dequeProcessingOrder() {
		Deque<String> work = new ArrayDeque<String>();
		work.addLast("normal");
		work.addFirst("urgent");
		work.addLast("background");
		return work.removeFirst() + " -> " + work.removeFirst() + " -> " + work.removeFirst();
	}
}
