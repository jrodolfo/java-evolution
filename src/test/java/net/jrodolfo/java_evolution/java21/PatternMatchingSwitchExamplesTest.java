package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchExamplesTest {

	private final PatternMatchingSwitchExamples examples = new PatternMatchingSwitchExamples();

	@Test
	void switchPatternsHandleSealedCommandsExhaustively() {
		// When
		String startDescription = examples.describe(new PatternMatchingSwitchExamples.Start("api"));
		String stopDescription = examples.describe(new PatternMatchingSwitchExamples.Stop("api"));
		String restartDescription = examples.describe(new PatternMatchingSwitchExamples.Restart("api"));

		// Then
		assertThat(startDescription)
				.as("The Start record pattern should bind the service name")
				.isEqualTo("start api");
		assertThat(stopDescription)
				.as("The Stop record pattern should bind the service name")
				.isEqualTo("stop api");
		assertThat(restartDescription)
				.as("The Restart record pattern should bind the service name")
				.isEqualTo("restart api");
	}
}
