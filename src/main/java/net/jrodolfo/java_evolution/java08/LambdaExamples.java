package net.jrodolfo.java_evolution.java08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExamples {

	public List<String> namesWithAtLeastFourLetters(List<String> names) {
		Predicate<String> hasAtLeastFourLetters = name -> name.length() >= 4;

		return names.stream()
				.filter(hasAtLeastFourLetters)
				.collect(Collectors.toList());
	}

	public List<String> sortByLength(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort((first, second) -> Integer.compare(first.length(), second.length()));
		return sortedNames;
	}

	public int calculate(int left, int right, IntegerOperation operation) {
		return operation.apply(left, right);
	}

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

	public List<String> sortWithLambda(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort((first, second) -> first.compareTo(second));
		return sortedNames;
	}

	@FunctionalInterface
	public interface IntegerOperation {
		int apply(int left, int right);
	}
}
