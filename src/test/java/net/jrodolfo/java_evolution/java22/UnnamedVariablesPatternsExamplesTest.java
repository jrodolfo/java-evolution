package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class UnnamedVariablesPatternsExamplesTest {

	private final UnnamedVariablesPatternsExamples examples = new UnnamedVariablesPatternsExamples();

	@Test
	void unnamedLambdaParameterDocumentsIgnoredArgument() {
		int usefulValue = 10;
		int intentionallyIgnoredValue = 99;

		int result = examples.useOnlyFirstValue(usefulValue, intentionallyIgnoredValue);

		assertThat(result)
				.as("The second lambda parameter is intentionally ignored")
				.isEqualTo(20);
	}

	@Test
	void unnamedLoopVariableDocumentsIgnoredElement() {
		List<String> valuesWhoseContentDoesNotMatter = List.of("a", "b", "c");

		int count = examples.countValues(valuesWhoseContentDoesNotMatter);

		assertThat(count)
				.as("The loop element is intentionally ignored")
				.isEqualTo(3);
	}

	@Test
	void unnamedStringPatternDocumentsIgnoredBinding() {
		Object textValue = "java";

		String broadType = examples.broadType(textValue);

		assertThat(broadType)
				.as("The String pattern needs only the broad type, not the bound value")
				.isEqualTo("text");
	}

	@Test
	void unnamedNumberPatternDocumentsIgnoredBinding() {
		Object numberValue = 22;

		String broadType = examples.broadType(numberValue);

		assertThat(broadType)
				.as("The Number pattern needs only the broad type, not the bound value")
				.isEqualTo("number");
	}

	@Test
	void patternSwitchCanStillHandleNullAndDefaultCases() {
		String nullType = examples.broadType(null);
		String defaultType = examples.broadType(true);

		assertThat(nullType)
				.as("Pattern switches can handle null explicitly")
				.isEqualTo("null");
		assertThat(defaultType)
				.as("Values outside the named patterns should use the default branch")
				.isEqualTo("other");
	}
}
