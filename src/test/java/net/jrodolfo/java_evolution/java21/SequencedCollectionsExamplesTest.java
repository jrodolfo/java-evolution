package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class SequencedCollectionsExamplesTest {

	private final SequencedCollectionsExamples examples = new SequencedCollectionsExamples();

	@Test
	void sequencedCollectionExposesFirstLastAndReversedOrder() {
		var summary = examples.summarizeSequence(List.of("java17", "java21", "java25"));

		assertThat(summary.first())
				.as("SequencedCollection.getFirst should expose the first value in encounter order")
				.isEqualTo("java17");
		assertThat(summary.last())
				.as("SequencedCollection.getLast should expose the last value in encounter order")
				.isEqualTo("java25");
		assertThat(summary.reversed())
				.as("SequencedCollection.reversed should expose the same values from the other direction")
				.containsExactly("java25", "java21", "java17");
	}

	@Test
	void sequencedMapExposesFirstAndLastEntries() {
		var summary = examples.summarizeMapOrder();

		assertThat(summary.firstKey())
				.as("SequencedMap.firstEntry should expose the first key in encounter order")
				.isEqualTo(17);
		assertThat(summary.firstValue())
				.as("SequencedMap.firstEntry should expose the first value in encounter order")
				.isEqualTo("LTS");
		assertThat(summary.lastKey())
				.as("SequencedMap.lastEntry should expose the last key in encounter order")
				.isEqualTo(25);
		assertThat(summary.lastValue())
				.as("SequencedMap.lastEntry should expose the last value in encounter order")
				.isEqualTo("current");
	}
}
