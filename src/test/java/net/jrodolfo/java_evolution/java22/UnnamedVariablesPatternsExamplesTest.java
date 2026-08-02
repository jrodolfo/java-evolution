package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class UnnamedVariablesPatternsExamplesTest {

	private final UnnamedVariablesPatternsExamples examples = new UnnamedVariablesPatternsExamples();

	@Test
	void unnamedLambdaParameterDocumentsIgnoredArgument() {
		assertThat(examples.useOnlyFirstValue(10, 99))
				.as("The second lambda parameter is intentionally ignored")
				.isEqualTo(20);
	}

	@Test
	void unnamedLoopVariableDocumentsIgnoredElement() {
		assertThat(examples.countValues(List.of("a", "b", "c")))
				.as("The loop element is intentionally ignored")
				.isEqualTo(3);
	}

	@Test
	void unnamedPatternDocumentsIgnoredBinding() {
		assertThat(examples.broadType("java")).isEqualTo("text");
		assertThat(examples.broadType(22)).isEqualTo("number");
		assertThat(examples.broadType(null)).isEqualTo("null");
		assertThat(examples.broadType(true)).isEqualTo("other");
	}
}
