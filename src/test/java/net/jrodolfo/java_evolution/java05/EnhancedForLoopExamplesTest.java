package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class EnhancedForLoopExamplesTest {

	private final EnhancedForLoopExamples examples = new EnhancedForLoopExamples();

	@Test
	void enhancedForLoopProducesSameResultAsExplicitIterator() {
		// Given
		List<String> names = Arrays.asList("Ana", "John", "Maria");

		// When
		int enhancedForTotal = examples.totalLengthWithEnhancedFor(names);
		int iteratorTotal = examples.totalLengthWithIterator(names);

		// Then
		assertThat(enhancedForTotal)
				.as("Enhanced for should keep traversal focused on each element, not iterator mechanics")
				.isEqualTo(iteratorTotal)
				.isEqualTo(12);
	}

	@Test
	void enhancedForLoopWorksForArrays() {
		// When
		int sum = examples.sumArray(new int[] { 2, 4, 6 });

		// Then
		assertThat(sum)
				.as("Enhanced for should traverse array values directly")
				.isEqualTo(12);
	}
}
