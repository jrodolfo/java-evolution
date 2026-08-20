package net.jrodolfo.java_evolution.java06;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class NavigableCollectionExamplesTest {

	private final NavigableCollectionExamples examples = new NavigableCollectionExamples();

	@Test
	void navigableSetFindsNearestValueAtOrAboveTarget() {
		// Given
		NavigableSet<Integer> scores = new TreeSet<>();
		scores.add(60);
		scores.add(75);
		scores.add(90);

		// When
		Integer nearestPassingScore = examples.nearestPassingScore(scores, 70);

		// Then
		assertThat(nearestPassingScore)
				.as("NavigableSet.ceiling should find the nearest element at or above the target")
				.isEqualTo(75);
	}

	@Test
	void navigableSetCanExposeDescendingView() {
		assertThat(examples.releaseNamesDescending())
				.as("A descending navigable view should reverse encounter order")
				.containsExactly("java07", "java06", "java05");
	}

	@Test
	void navigableMapFindsFloorEntry() {
		assertThat(examples.releaseAtOrBefore(6))
				.as("NavigableMap.floorEntry should find the greatest key not above the request")
				.isEqualTo("scripting and compiler APIs");
	}

	@Test
	void dequeSupportsWorkAtBothEnds() {
		assertThat(examples.dequeProcessingOrder())
				.as("Deque should support urgent work at the front and normal work at the back")
				.isEqualTo("urgent -> normal -> background");
	}
}
