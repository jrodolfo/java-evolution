package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LambdaExamplesTest {

	private final LambdaExamples examples = new LambdaExamples();

	@Test
	void predicateLambdaKeepsOnlyNamesWithAtLeastFourLetters() {
		// Given
		List<String> names = Arrays.asList("Ana", "John", "Li", "Maria");

		// When
		List<String> filteredNames = examples.namesWithAtLeastFourLetters(names);

		// Then
		assertThat(filteredNames)
				.as("The Predicate lambda should keep only names with four or more letters")
				.containsExactly("John", "Maria");
	}

	@Test
	void comparatorLambdaSortsNamesFromShortestToLongest() {
		// Given
		List<String> names = Arrays.asList("Maria", "Ana", "Jonathan", "Li");

		// When
		List<String> sortedNames = examples.sortByLength(names);

		// Then
		assertThat(sortedNames)
				.as("The Comparator lambda should compare names by their length")
				.containsExactly("Li", "Ana", "Maria", "Jonathan");
	}

	@Test
	void functionalInterfaceAllowsBehaviorToBePassedAsAnArgument() {
		// Given / When
		int sum = examples.calculate(10, 5, (left, right) -> left + right);
		int multiplication = examples.calculate(10, 5, (left, right) -> left * right);

		// Then
		assertThat(sum)
				.as("The first lambda should add both numbers")
				.isEqualTo(15);
		assertThat(multiplication)
				.as("The second lambda should multiply both numbers")
				.isEqualTo(50);
	}

	@Test
	void standardFunctionalInterfacesCanBeUsedInProjectApis() {
		// Given
		List<String> names = Arrays.asList("Ana", "John", "Maria");
		List<String> visitedNames = new ArrayList<>();

		// When
		List<String> longNames = examples.keepMatching(names, name -> name.length() >= 4);
		List<Integer> nameLengths = examples.transform(names, name -> Integer.valueOf(name.length()));
		String fallback = examples.valueOrFallback(null, () -> "generated");
		examples.visitEach(names, name -> visitedNames.add(name.toUpperCase()));

		// Then
		assertThat(longNames)
				.as("A project method can accept Predicate<T>, not only Stream.filter")
				.containsExactly("John", "Maria");
		assertThat(nameLengths)
				.as("A project method can accept Function<T, R> to map values")
				.containsExactly(Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5));
		assertThat(fallback)
				.as("A project method can accept Supplier<T> for deferred fallback creation")
				.isEqualTo("generated");
		assertThat(visitedNames)
				.as("A project method can accept Consumer<T> for side-effect callbacks")
				.containsExactly("ANA", "JOHN", "MARIA");
	}

	@Test
	void blockLambdaUsesBracesAndExplicitReturnForMultipleStatements() {
		// When
		int normalizedDifference = examples.normalizedPositiveDifference(250, 40);

		// Then
		assertThat(normalizedDifference)
				.as("A multi-statement lambda should use braces and return an explicit value")
				.isEqualTo(100);
	}

	@Test
	void lambdaCanReplaceAnonymousClassForSimpleComparatorBehavior() {
		// Given
		List<String> names = Arrays.asList("Maria", "Ana", "John");

		// When
		List<String> sortedWithLambda = examples.sortWithLambda(names);
		List<String> sortedWithAnonymousClass = examples.sortWithAnonymousClass(names);

		// Then
		assertThat(sortedWithLambda)
				.as("The lambda version should produce the same result as the anonymous class")
				.isEqualTo(sortedWithAnonymousClass)
				.containsExactly("Ana", "John", "Maria");
	}
}
