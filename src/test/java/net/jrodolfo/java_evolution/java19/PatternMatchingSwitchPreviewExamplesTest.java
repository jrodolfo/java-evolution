package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PatternMatchingSwitchPreviewExamplesTest {

	private final PatternMatchingSwitchPreviewExamples examples = new PatternMatchingSwitchPreviewExamples();

	@Test
	void switchPatternsHandleSealedHierarchy() {
		// When / Then
		assertThat(examples.describe(new PatternMatchingSwitchPreviewExamples.Login("ana")))
				.as("The Login pattern should bind the event record")
				.isEqualTo("login by ana");
		assertThat(examples.describe(new PatternMatchingSwitchPreviewExamples.Logout("rodolfo")))
				.as("The Logout pattern should bind the event record")
				.isEqualTo("logout by rodolfo");
	}
}
