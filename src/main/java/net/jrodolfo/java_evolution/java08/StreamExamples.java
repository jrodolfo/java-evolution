package net.jrodolfo.java_evolution.java08;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExamples {

	public List<String> activeUserNames(List<User> users) {
		return users.stream()
				.filter(User::active)
				.map(User::name)
				.sorted()
				.collect(Collectors.toList());
	}

	public Map<String, List<String>> namesByDepartment(List<User> users) {
		return users.stream()
				.collect(Collectors.groupingBy(
						User::department,
						Collectors.mapping(User::name, Collectors.toList())));
	}

	public int totalAgeOfActiveUsers(List<User> users) {
		return users.stream()
				.filter(User::active)
				.mapToInt(User::age)
				.sum();
	}

	public String longestName(List<User> users) {
		return users.stream()
				.map(User::name)
				.max(Comparator.comparingInt(String::length))
				.orElse("");
	}

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

		public String name() {
			return name;
		}

		public int age() {
			return age;
		}

		public String department() {
			return department;
		}

		public boolean active() {
			return active;
		}
	}
}
