package net.jrodolfo.java_evolution.java16;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingInstanceofExamplesTest {

	private final PatternMatchingInstanceofExamples examples = new PatternMatchingInstanceofExamples();

	@Test
	void instanceofPatternCombinesTypeCheckAndBinding() {
		// When / Then
		assertThat(examples.describe("Java 16"))
				.as("The String pattern should bind a String variable after the type check")
				.isEqualTo("text length=7");
		assertThat(examples.describe(25))
				.as("The Number pattern should bind a Number variable after the type check")
				.isEqualTo("number=25");
		assertThat(examples.describe("   "))
				.as("The bound variable can be used in the same condition")
				.isEqualTo("unknown");
	}
}
