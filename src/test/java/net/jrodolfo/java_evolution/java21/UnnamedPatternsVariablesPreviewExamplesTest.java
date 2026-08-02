package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class UnnamedPatternsVariablesPreviewExamplesTest {

	private final UnnamedPatternsVariablesPreviewExamples examples = new UnnamedPatternsVariablesPreviewExamples();

	@Test
	void unnamedVariableDocumentsIgnoredLoopValue() {
		assertThat(examples.countWithoutUsingElements(List.of("a", "b", "c")))
				.as("The loop value is intentionally ignored")
				.isEqualTo(3);
	}

	@Test
	void unnamedCatchParameterDocumentsIgnoredExceptionObject() {
		assertThat(examples.canParseInteger("21")).isTrue();
		assertThat(examples.canParseInteger("not a number")).isFalse();
	}
}
