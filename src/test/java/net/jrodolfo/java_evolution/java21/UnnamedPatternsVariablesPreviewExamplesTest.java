package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class UnnamedPatternsVariablesPreviewExamplesTest {

	private final UnnamedPatternsVariablesPreviewExamples examples = new UnnamedPatternsVariablesPreviewExamples();

	@Test
	void unnamedVariableDocumentsIgnoredLoopValue() {
		List<String> values = List.of("a", "b", "c");

		int visitedValues = examples.countWithoutUsingElements(values);

		assertThat(visitedValues)
				.as("The loop value is intentionally ignored")
				.isEqualTo(3);
	}

	@Test
	void unnamedCatchParameterDocumentsIgnoredExceptionObject() {
		boolean validNumber = examples.canParseInteger("21");
		boolean invalidNumber = examples.canParseInteger("not a number");

		assertThat(validNumber)
				.as("Parsing succeeds without needing the catch path")
				.isTrue();
		assertThat(invalidNumber)
				.as("The catch parameter can be unnamed when the exception object is not needed")
				.isFalse();
	}
}
