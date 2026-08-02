package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FlexibleConstructorBodiesExamplesTest {

	private final FlexibleConstructorBodiesExamples examples = new FlexibleConstructorBodiesExamples();

	@Test
	void constructorCanValidateBeforeDelegating() {
		FlexibleConstructorBodiesExamples.Account account = examples.account(" Rodolfo ");

		assertThat(account.owner()).isEqualTo("Rodolfo");
		assertThat(account.active()).isTrue();
		assertThatThrownBy(() -> examples.account(" "))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("owner is required");
	}
}
