package net.jrodolfo.java_evolution.java05;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates autoboxing and unboxing, introduced in Java 5.
 */
public class AutoboxingExamples {

	/**
	 * Adds primitive {@code int} values to a list of {@link Integer} values. The
	 * compiler boxes each primitive for the collection.
	 *
	 * @param left first value
	 * @param right second value
	 * @return boxed integers in insertion order
	 */
	public List<Integer> boxedNumbers(int left, int right) {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(left);
		numbers.add(right);
		return numbers;
	}

	/**
	 * Adds boxed values as primitive {@code int} values. The compiler unboxes each
	 * {@link Integer} before arithmetic.
	 *
	 * @param numbers boxed numbers
	 * @return the primitive sum
	 */
	public int sumBoxedNumbers(List<Integer> numbers) {
		int sum = 0;

		for (Integer number : numbers) {
			sum += number;
		}

		return sum;
	}

	/**
	 * Shows the important caveat: unboxing {@code null} is still a
	 * {@link NullPointerException}.
	 *
	 * @param number boxed number
	 * @return primitive value
	 */
	public int unbox(Integer number) {
		return number;
	}
}
