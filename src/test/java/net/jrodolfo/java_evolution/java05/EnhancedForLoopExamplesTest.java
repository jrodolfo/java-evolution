package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
	void enhancedForLoopOnlyRequiresIterable() {
		// Given
		Set<String> names = new LinkedHashSet<String>(Arrays.asList("Set", "List"));

		// When
		int total = examples.totalLengthWithEnhancedFor(names);

		// Then
		assertThat(total)
				.as("Enhanced for should accept any Iterable, not only List")
				.isEqualTo(7);
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

	@Test
	void applicationTypesCanImplementIterableForEnhancedFor() {
		// Given
		Iterable<String> releaseNames = examples.releaseNames("Java 5", "Tiger");

		// When
		int total = examples.totalLengthWithEnhancedFor(releaseNames);

		// Then
		assertThat(total)
				.as("A custom Iterable should be usable directly in an enhanced for loop")
				.isEqualTo(11);
	}
}
