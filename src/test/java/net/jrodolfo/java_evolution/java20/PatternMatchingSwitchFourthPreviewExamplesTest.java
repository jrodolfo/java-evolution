package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchFourthPreviewExamplesTest {

	private final PatternMatchingSwitchFourthPreviewExamples examples = new PatternMatchingSwitchFourthPreviewExamples();

	@Test
	void switchPatternsClassifyValues() {
		// When
		String nullResult = examples.classify(null);
		String guardedStringResult = examples.classify("evolution");
		String stringPatternResult = examples.classify("java");
		String integerPatternResult = examples.classify(20);
		String fallbackResult = examples.classify(20L);

		// Then
		assertThat(nullResult)
				.as("A pattern switch can handle null explicitly")
				.isEqualTo("null");
		assertThat(guardedStringResult)
				.as("A guarded String pattern should run before the more general String pattern")
				.isEqualTo("long string");
		assertThat(stringPatternResult)
				.as("A String pattern should bind the text when the guard does not match")
				.isEqualTo("short string: java");
		assertThat(integerPatternResult)
				.as("An Integer pattern should bind the integer value")
				.isEqualTo("integer: 20");
		assertThat(fallbackResult)
				.as("Values without a matching pattern should reach the default branch")
				.isEqualTo("unknown");
	}
}
