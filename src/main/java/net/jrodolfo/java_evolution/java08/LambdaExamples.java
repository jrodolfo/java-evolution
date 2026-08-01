package net.jrodolfo.java_evolution.java08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrates lambda expressions, one of the main language features introduced
 * in Java 8.
 *
 * <p>
 * Lambdas let a method receive behavior as data. They are commonly used with
 * functional interfaces such as {@link Predicate}, {@link Comparator}, and the
 * interfaces in {@code java.util.function}.
 * </p>
 */
public class LambdaExamples {

	/**
	 * Filters a list by assigning a lambda expression to a {@link Predicate}.
	 *
	 * @param names the names to inspect
	 * @return only the names that contain at least four letters
	 */
	public List<String> namesWithAtLeastFourLetters(List<String> names) {
		// A lambda can be stored in any compatible functional interface.
		Predicate<String> hasAtLeastFourLetters = name -> name.length() >= 4;

		return names.stream()
				.filter(hasAtLeastFourLetters)
				.collect(Collectors.toList());
	}

	/**
	 * Sorts names using a lambda expression as a {@link Comparator}.
	 *
	 * @param names the names to sort
	 * @return a new list sorted from shortest name to longest name
	 */
	public List<String> sortByLength(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort((first, second) -> Integer.compare(first.length(), second.length()));
		return sortedNames;
	}

	/**
	 * Receives a custom operation as a method argument.
	 *
	 * @param left the left number used by the operation
	 * @param right the right number used by the operation
	 * @param operation the behavior to apply to both numbers
	 * @return the result produced by the operation
	 */
	public int calculate(int left, int right, IntegerOperation operation) {
		return operation.apply(left, right);
	}

	/**
	 * Sorts names with an anonymous class, the older style commonly used before
	 * Java 8 lambdas.
	 *
	 * @param names the names to sort
	 * @return a new list sorted alphabetically
	 */
	public List<String> sortWithAnonymousClass(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(new Comparator<String>() {
			@Override
			public int compare(String first, String second) {
				return first.compareTo(second);
			}
		});
		return sortedNames;
	}

	/**
	 * Sorts names with a lambda expression, showing the shorter Java 8
	 * replacement for a simple anonymous class.
	 *
	 * @param names the names to sort
	 * @return a new list sorted alphabetically
	 */
	public List<String> sortWithLambda(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort((first, second) -> first.compareTo(second));
		return sortedNames;
	}

	/**
	 * Simple functional interface used to demonstrate passing behavior into a
	 * method.
	 */
	@FunctionalInterface
	public interface IntegerOperation {
		/**
		 * Applies an operation to two integers.
		 *
		 * @param left the left number
		 * @param right the right number
		 * @return the operation result
		 */
		int apply(int left, int right);
	}
}
