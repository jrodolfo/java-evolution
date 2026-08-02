package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class HelpfulNullPointerExceptionExamplesTest {

	private final HelpfulNullPointerExceptionExamples examples = new HelpfulNullPointerExceptionExamples();

	@Test
	void nullPointerExceptionMessageIdentifiesTheNullPartOfTheChain() {
		assertThatThrownBy(examples::triggerHelpfulNullPointerException)
				.as("Java 14 helpful NullPointerException messages should identify the null expression")
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("address()");
	}
}
