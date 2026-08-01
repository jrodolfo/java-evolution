package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LambdaExamplesTest {

	private final LambdaExamples examples = new LambdaExamples();

	@Test
	void filtersNamesUsingPredicateLambda() {
		List<String> names = Arrays.asList("Ana", "John", "Li", "Maria");

		assertThat(examples.namesWithAtLeastFourLetters(names))
				.containsExactly("John", "Maria");
	}

	@Test
	void sortsNamesUsingComparatorLambda() {
		List<String> names = Arrays.asList("Maria", "Ana", "Jonathan", "Li");

		assertThat(examples.sortByLength(names))
				.containsExactly("Li", "Ana", "Maria", "Jonathan");
	}

	@Test
	void receivesBehaviorAsFunctionArgument() {
		int sum = examples.calculate(10, 5, (left, right) -> left + right);
		int multiplication = examples.calculate(10, 5, (left, right) -> left * right);

		assertThat(sum).isEqualTo(15);
		assertThat(multiplication).isEqualTo(50);
	}

	@Test
	void lambdaCanReplaceAnonymousClassForSimpleBehavior() {
		List<String> names = Arrays.asList("Maria", "Ana", "John");

		assertThat(examples.sortWithLambda(names))
				.isEqualTo(examples.sortWithAnonymousClass(names))
				.containsExactly("Ana", "John", "Maria");
	}
}
