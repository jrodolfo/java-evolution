package net.jrodolfo.java_evolution.java08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Demonstrates method references, introduced in Java 8 as a compact syntax for
 * lambdas that only call an existing method or constructor.
 *
 * <p>
 * Lambdas made it easy to pass behavior, but many lambdas simply delegated to
 * an existing method, such as {@code text -> Integer.parseInt(text)}. That
 * repeated information already present in the method name.
 * </p>
 *
 * <p>
 * Method references solve that small readability problem. They let code say
 * {@code Integer::parseInt}, {@code formatter::format},
 * {@code String::compareToIgnoreCase}, or {@code User::new}. The feature does
 * not add new behavior beyond lambdas; it makes simple delegation easier to
 * scan.
 * </p>
 */
public class MethodReferenceExamples {

	/**
	 * Uses a static method reference.
	 *
	 * @param numbers the numeric text values to parse
	 * @return the parsed integer values
	 */
	public List<Integer> parseNumbers(List<String> numbers) {
		return numbers.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	/**
	 * Uses a bound instance method reference, where the receiver object already
	 * exists.
	 *
	 * @param names the names to format
	 * @return formatted names produced by the same formatter instance
	 */
	public List<String> formatNames(List<String> names) {
		NameFormatter formatter = new NameFormatter();

		return names.stream()
				.map(formatter::format)
				.collect(Collectors.toList());
	}

	/**
	 * Uses an unbound instance method reference, where the receiver is supplied
	 * by each stream element.
	 *
	 * @param names the names to sort
	 * @return a new list sorted without considering letter case
	 */
	public List<String> sortIgnoringCase(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(String::compareToIgnoreCase);
		return sortedNames;
	}

	/**
	 * Uses a constructor reference to create a domain object from each input
	 * value.
	 *
	 * @param names the names used to create users
	 * @return users created with {@code User::new}
	 */
	public List<User> createUsers(List<String> names) {
		return names.stream()
				.map(User::new)
				.collect(Collectors.toList());
	}

	/**
	 * Shows that constructor references can also be assigned to functional
	 * interfaces.
	 *
	 * @param name the user's name
	 * @return a user created by a {@link Function}
	 */
	public User createUserWithFunction(String name) {
		Function<String, User> userFactory = User::new;
		return userFactory.apply(name);
	}

	/**
	 * Shows a zero-argument constructor reference with {@link Supplier}.
	 *
	 * @return a formatter created by calling {@code NameFormatter::new}
	 */
	public NameFormatter createFormatterWithSupplier() {
		Supplier<NameFormatter> formatterFactory = NameFormatter::new;
		return formatterFactory.get();
	}

	/**
	 * Simple formatter used to demonstrate a bound instance method reference.
	 */
	public static class NameFormatter {
		/**
		 * Formats one name for display.
		 *
		 * @param name the raw name
		 * @return the formatted display value
		 */
		public String format(String name) {
			return "Name: " + name;
		}
	}

	/**
	 * Small Java 8-style data class used by constructor reference examples.
	 */
	public static class User {
		private final String name;

		/**
		 * Creates a user with the supplied display name.
		 *
		 * @param name the user's display name
		 */
		public User(String name) {
			this.name = name;
		}

		/**
		 * Returns the user's display name.
		 *
		 * @return the user's display name
		 */
		public String name() {
			return name;
		}
	}
}
