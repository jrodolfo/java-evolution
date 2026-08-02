package net.jrodolfo.java_evolution.java09;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates Stream API enhancements introduced in Java 9.
 *
 * <p>
 * Java 8 streams made pipeline-style data processing practical, but some
 * common stream shapes still required custom logic: process ordered values
 * until a boundary, skip ordered values until a boundary, safely stream a
 * possibly null value, or generate values with a built-in stopping condition.
 * </p>
 *
 * <p>
 * Java 9 added ordered stream slicing with {@code takeWhile} and
 * {@code dropWhile}, nullable stream creation with {@code Stream.ofNullable},
 * and a bounded form of {@code Stream.iterate}.
 * </p>
 */
public class StreamEnhancementExamples {

	/**
	 * Uses {@link Stream#takeWhile(java.util.function.Predicate)} to keep values
	 * only until the first value that does not match.
	 *
	 * @param numbers ordered numbers to inspect
	 * @return the numbers before the first value greater than or equal to 10
	 */
	public List<Integer> numbersBeforeTen(List<Integer> numbers) {
		return numbers.stream()
				.takeWhile(number -> number < 10)
				.collect(Collectors.toList());
	}

	/**
	 * Uses {@link Stream#dropWhile(java.util.function.Predicate)} to skip values
	 * until the first value that does not match.
	 *
	 * @param numbers ordered numbers to inspect
	 * @return the numbers starting at the first value greater than or equal to 10
	 */
	public List<Integer> numbersFromTenOnward(List<Integer> numbers) {
		return numbers.stream()
				.dropWhile(number -> number < 10)
				.collect(Collectors.toList());
	}

	/**
	 * Uses {@link Stream#ofNullable(Object)} to create either a one-element
	 * stream or an empty stream.
	 *
	 * @param value the possibly null value
	 * @return a list containing the value when non-null, otherwise an empty list
	 */
	public List<String> listFromNullableValue(String value) {
		return Stream.ofNullable(value)
				.collect(Collectors.toList());
	}

	/**
	 * Uses the Java 9 bounded form of {@link Stream#iterate(Object,
	 * java.util.function.Predicate, java.util.function.UnaryOperator)}.
	 *
	 * @return powers of two that are smaller than 100
	 */
	public List<Integer> powersOfTwoBelowOneHundred() {
		return Stream.iterate(1, number -> number < 100, number -> number * 2)
				.collect(Collectors.toList());
	}
}
