package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

class HelpfulNullPointerExceptionExamplesTest {

	private final HelpfulNullPointerExceptionExamples examples = new HelpfulNullPointerExceptionExamples();

	@Test
	void nullPointerExceptionMessageIdentifiesTheNullPartOfTheChain() {
		Throwable failure = org.assertj.core.api.Assertions.catchThrowable(examples::triggerHelpfulNullPointerException);

		// Helpful NPE details can be disabled with -XX:-ShowCodeDetailsInExceptionMessages.
		assumeTrue(failure.getMessage() != null, "helpful NullPointerException details are disabled in this JVM");

		assertThatThrownBy(() -> {
			throw failure;
		})
				.as("Java 14 helpful NullPointerException messages should identify the null expression")
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("address()");
	}
}
