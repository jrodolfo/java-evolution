package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchFourthPreviewExamplesTest {

	private final PatternMatchingSwitchFourthPreviewExamples examples = new PatternMatchingSwitchFourthPreviewExamples();

	@Test
	void switchPatternsClassifyValues() {
		// When / Then
		assertThat(examples.classify(null)).isEqualTo("null");
		assertThat(examples.classify("evolution")).isEqualTo("long string");
		assertThat(examples.classify("java")).isEqualTo("short string: java");
		assertThat(examples.classify(20)).isEqualTo("integer: 20");
		assertThat(examples.classify(20L)).isEqualTo("unknown");
	}
}
