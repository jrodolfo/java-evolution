package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

class HelpfulNullPointerExceptionExamplesTest {

	private final HelpfulNullPointerExceptionExamples examples = new HelpfulNullPointerExceptionExamples();

	@Test
	void nullPointerExceptionMessageIdentifiesTheNullPartOfTheChain() {
		Throwable failure = catchThrowable(examples::triggerHelpfulNullPointerException);

		// Helpful NPE details can be disabled with -XX:-ShowCodeDetailsInExceptionMessages.
		assumeTrue(failure.getMessage() != null, "helpful NullPointerException details are disabled in this JVM");

		assertThat(failure)
				.as("Java 14 helpful NullPointerException messages should identify the null expression")
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("address()");
	}
}
