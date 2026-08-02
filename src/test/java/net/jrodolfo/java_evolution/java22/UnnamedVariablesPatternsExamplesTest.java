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
		assertThat(examples.broadType("java"))
				.as("The String pattern needs only the broad type, not the bound value")
				.isEqualTo("text");
		assertThat(examples.broadType(22))
				.as("The Number pattern needs only the broad type, not the bound value")
				.isEqualTo("number");
		assertThat(examples.broadType(null))
				.as("Pattern switches can handle null explicitly")
				.isEqualTo("null");
		assertThat(examples.broadType(true))
				.as("Values outside the named patterns should use the default branch")
				.isEqualTo("other");
	}
}
