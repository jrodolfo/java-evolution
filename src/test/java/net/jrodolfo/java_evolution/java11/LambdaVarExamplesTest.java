package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LambdaVarExamplesTest {

	private final LambdaVarExamples examples = new LambdaVarExamples();

	@Test
	void varCanBeUsedForInferredLambdaParameters() {
		// Given
		List<String> names = Arrays.asList(" Ana ", "RODOLFO");

		// When
		List<String> normalizedNames = examples.normalizeNames(names);

		// Then
		assertThat(normalizedNames)
				.as("var in a lambda parameter should keep type inference while allowing an explicit parameter")
				.containsExactly("ana", "rodolfo");
	}

	@Test
	void varAllowsAnnotationsOnInferredLambdaParameters() {
		// Given
		List<String> names = Arrays.asList("Ana", "Rodolfo");

		// When
		List<Integer> lengths = examples.nameLengths(names);

		// Then
		assertThat(lengths)
				.as("The annotated var parameter should still be inferred as String")
				.containsExactly(3, 7);
	}
}
