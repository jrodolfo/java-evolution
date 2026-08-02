package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchExamplesTest {

	private final PatternMatchingSwitchExamples examples = new PatternMatchingSwitchExamples();

	@Test
	void switchPatternsHandleSealedCommandsExhaustively() {
		assertThat(examples.describe(new PatternMatchingSwitchExamples.Start("api"))).isEqualTo("start api");
		assertThat(examples.describe(new PatternMatchingSwitchExamples.Stop("api"))).isEqualTo("stop api");
		assertThat(examples.describe(new PatternMatchingSwitchExamples.Restart("api"))).isEqualTo("restart api");
	}
}
