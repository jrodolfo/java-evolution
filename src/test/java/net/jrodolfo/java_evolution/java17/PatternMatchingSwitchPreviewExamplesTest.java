package net.jrodolfo.java_evolution.java17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchPreviewExamplesTest {

	private final PatternMatchingSwitchPreviewExamples examples = new PatternMatchingSwitchPreviewExamples();

	@Test
	void switchCanUseTypePatternsAndGuards() {
		// When / Then
		assertThat(examples.describe(null))
				.as("Pattern matching switch can handle null explicitly")
				.isEqualTo("null");
		assertThat(examples.describe("   "))
				.as("A guarded String pattern should handle blank text")
				.isEqualTo("blank string");
		assertThat(examples.describe("Java"))
				.as("A String pattern should bind the value")
				.isEqualTo("string length=4");
		assertThat(examples.describe(21))
				.as("An Integer pattern should bind the value")
				.isEqualTo("integer doubled=42");
		assertThat(examples.describe(1.5))
				.as("Values not matching any pattern should use the default branch")
				.isEqualTo("unknown");
	}
}
