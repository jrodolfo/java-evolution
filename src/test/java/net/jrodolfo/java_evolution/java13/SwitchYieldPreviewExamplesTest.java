package net.jrodolfo.java_evolution.java13;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SwitchYieldPreviewExamplesTest {

	private final SwitchYieldPreviewExamples examples = new SwitchYieldPreviewExamples();

	@Test
	void yieldReturnsAValueFromSwitchBlockBranch() {
		// When / Then
		assertThat(examples.grade(95))
				.as("Arrow branches can return simple values directly")
				.isEqualTo("excellent");
		assertThat(examples.grade(75))
				.as("Block branches use yield to produce the switch expression value")
				.isEqualTo("good progress");
		assertThat(examples.grade(50))
				.as("The default branch should produce the fallback label")
				.isEqualTo("keep practicing");
	}
}
