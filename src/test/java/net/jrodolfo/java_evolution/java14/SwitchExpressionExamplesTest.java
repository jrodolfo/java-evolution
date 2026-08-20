package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SwitchExpressionExamplesTest {

	private final SwitchExpressionExamples examples = new SwitchExpressionExamples();

	@Test
	void switchExpressionReturnsAValueDirectly() {
		// When / Then
		assertThat(examples.season(1))
				.as("Switch expressions should return the selected branch value")
				.isEqualTo("winter");
		assertThat(examples.season(7))
				.as("Comma-separated labels can share the same arrow branch")
				.isEqualTo("summer");
		assertThat(examples.season(99))
				.as("The default branch should handle invalid values")
				.isEqualTo("unknown");
	}

	@Test
	void yieldReturnsAValueFromABlockBranch() {
		// When / Then
		assertThat(examples.grade(95))
				.as("Arrow branches can return values directly without yield")
				.isEqualTo("excellent");
		assertThat(examples.grade(75))
				.as("Block branches should use yield to produce the switch expression result")
				.isEqualTo("good progress");
		assertThat(examples.grade(50))
				.as("The default branch should produce the fallback label")
				.isEqualTo("keep practicing");
	}
}
