package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingInstanceofPreviewExamplesTest {

	private final PatternMatchingInstanceofPreviewExamples examples = new PatternMatchingInstanceofPreviewExamples();

	@Test
	void instanceofPatternBindsTheCastedVariable() {
		// When / Then
		assertThat(examples.describe("Java"))
				.as("The String pattern should bind the value to a String variable")
				.isEqualTo("string length=4");
		assertThat(examples.describe(21))
				.as("The Integer pattern should bind the value to an Integer variable")
				.isEqualTo("integer doubled=42");
		assertThat(examples.describe(1.5))
				.as("Values that do not match a pattern should use the fallback branch")
				.isEqualTo("unknown");
	}
}
