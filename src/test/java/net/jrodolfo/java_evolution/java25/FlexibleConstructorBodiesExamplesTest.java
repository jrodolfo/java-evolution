package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesExamplesTest {

	private final FlexibleConstructorBodiesExamples examples = new FlexibleConstructorBodiesExamples();

	@Test
	void constructorCanNormalizeInputBeforeDelegating() {
		FlexibleConstructorBodiesExamples.Account account = examples.account(" Rodolfo ");

		String owner = account.owner();
		boolean active = account.active();

		assertThat(owner)
				.as("The public constructor should normalize the argument before delegating")
				.isEqualTo("Rodolfo");
		assertThat(active)
				.as("Delegation should still create the account with its default active state")
				.isTrue();
	}

	@Test
	void constructorCanRejectInvalidInputBeforeDelegating() {
		assertThatThrownBy(() -> examples.account(" "))
				.as("Validation before constructor delegation should reject invalid arguments early")
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("owner is required");
	}
}
