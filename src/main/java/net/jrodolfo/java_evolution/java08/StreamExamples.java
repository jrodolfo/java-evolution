package net.jrodolfo.java_evolution.java08;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demonstrates the Stream API introduced in Java 8.
 *
 * <p>
 * Streams make it possible to describe data processing as a pipeline of
 * operations such as {@code filter}, {@code map}, {@code sorted}, and
 * {@code collect}. The examples here return values instead of printing output
 * so tests can document the expected behavior.
 * </p>
 */
public class StreamExamples {

	/**
	 * Finds active users, extracts their names, and sorts the result.
	 *
	 * @param users the users to process
	 * @return active user names in alphabetical order
	 */
	public List<String> activeUserNames(List<User> users) {
		// This pipeline reads as: keep active users, transform to names, sort, collect.
		return users.stream()
				.filter(User::active)
				.map(User::name)
				.sorted()
				.collect(Collectors.toList());
	}

	/**
	 * Groups user names by department with {@link Collectors#groupingBy}.
	 *
	 * @param users the users to group
	 * @return a map where each department points to the names in that department
	 */
	public Map<String, List<String>> namesByDepartment(List<User> users) {
		return users.stream()
				.collect(Collectors.groupingBy(
						User::department,
						Collectors.mapping(User::name, Collectors.toList())));
	}

	/**
	 * Uses a primitive {@code IntStream} to sum ages without boxing every value
	 * as an {@link Integer}.
	 *
	 * @param users the users to inspect
	 * @return the sum of ages for active users
	 */
	public int totalAgeOfActiveUsers(List<User> users) {
		return users.stream()
				.filter(User::active)
				.mapToInt(User::age)
				.sum();
	}

	/**
	 * Uses {@code max} with a comparator to find the longest user name.
	 *
	 * @param users the users to inspect
	 * @return the longest name, or an empty string when the list has no users
	 */
	public String longestName(List<User> users) {
		return users.stream()
				.map(User::name)
				.max(Comparator.comparingInt(String::length))
				.orElse("");
	}

	/**
	 * Small Java 8-style data class used by the stream examples.
	 *
	 * <p>
	 * This deliberately avoids records because records were introduced much
	 * later, in Java 16.
	 * </p>
	 */
	public static class User {
		private final String name;
		private final int age;
		private final String department;
		private final boolean active;

		public User(String name, int age, String department, boolean active) {
			this.name = name;
			this.age = age;
			this.department = department;
			this.active = active;
		}

		/**
		 * @return the user's display name
		 */
		public String name() {
			return name;
		}

		/**
		 * @return the user's age
		 */
		public int age() {
			return age;
		}

		/**
		 * @return the department the user belongs to
		 */
		public String department() {
			return department;
		}

		/**
		 * @return whether the user is active
		 */
		public boolean active() {
			return active;
		}
	}
}
