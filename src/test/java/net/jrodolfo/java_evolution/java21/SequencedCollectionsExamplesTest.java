package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class SequencedCollectionsExamplesTest {

	private final SequencedCollectionsExamples examples = new SequencedCollectionsExamples();

	@Test
	void sequencedCollectionExposesFirstLastAndReversedOrder() {
		assertThat(examples.summarizeSequence(List.of("java17", "java21", "java25")))
				.as("SequencedCollection should expose first, last, and reversed views")
				.isEqualTo("java17 -> java25 | reversed=[java25, java21, java17]");
	}

	@Test
	void sequencedMapExposesFirstAndLastEntries() {
		assertThat(examples.summarizeMapOrder())
				.as("SequencedMap should expose first and last entries by encounter order")
				.isEqualTo("17 to 25");
	}
}
