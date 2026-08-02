package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesExamplesTest {

	private final ScopedValuesExamples examples = new ScopedValuesExamples();

	@Test
	void scopedValueIsVisibleOnlyInsideBoundScope() throws Exception {
		assertThat(examples.userInsideScope("Rodolfo"))
				.as("The value should be visible while the scoped binding is active")
				.isEqualTo("current user=Rodolfo");
		assertThat(examples.userIsBoundOutsideScope())
				.as("Scoped value should not remain bound after the scoped call")
				.isFalse();
	}
}
