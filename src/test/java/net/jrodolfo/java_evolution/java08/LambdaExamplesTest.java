package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

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
