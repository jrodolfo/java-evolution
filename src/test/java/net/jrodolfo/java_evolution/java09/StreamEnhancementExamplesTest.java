package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamEnhancementExamplesTest {

	private final StreamEnhancementExamples examples = new StreamEnhancementExamples();

	@Test
	void takeWhileKeepsValuesUntilPredicateFails() {
		// Given
		List<Integer> numbers = Arrays.asList(1, 2, 3, 10, 4, 5);

		// When
		List<Integer> numbersBeforeTen = examples.numbersBeforeTen(numbers);

		// Then
		assertThat(numbersBeforeTen)
				.as("takeWhile should stop at the first value that does not match")
				.containsExactly(1, 2, 3);
	}

	@Test
	void dropWhileSkipsValuesUntilPredicateFails() {
		// Given
		List<Integer> numbers = Arrays.asList(1, 2, 3, 10, 4, 5);

		// When
		List<Integer> numbersFromTenOnward = examples.numbersFromTenOnward(numbers);

		// Then
		assertThat(numbersFromTenOnward)
				.as("dropWhile should return the stream from the first non-matching value onward")
				.containsExactly(10, 4, 5);
	}

	@Test
	void ofNullableCreatesOneElementStreamForNonNullValue() {
		// When
		List<String> values = examples.listFromNullableValue("Java 9");

		// Then
		assertThat(values)
				.as("Stream.ofNullable should create one element when the value is non-null")
				.containsExactly("Java 9");
	}

	@Test
	void ofNullableCreatesEmptyStreamForNullValue() {
		// When
		List<String> values = examples.listFromNullableValue(null);

		// Then
		assertThat(values)
				.as("Stream.ofNullable should create an empty stream when the value is null")
				.isEmpty();
	}

	@Test
	void boundedIterateStopsWhenPredicateFails() {
		// When
		List<Integer> powersOfTwo = examples.powersOfTwoBelowOneHundred();

		// Then
		assertThat(powersOfTwo)
				.as("The Java 9 Stream.iterate overload should stop when the predicate becomes false")
				.containsExactly(1, 2, 4, 8, 16, 32, 64);
	}
}
