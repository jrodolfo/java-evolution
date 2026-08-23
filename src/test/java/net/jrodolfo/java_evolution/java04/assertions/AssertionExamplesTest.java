package net.jrodolfo.java_evolution.java04.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AssertionExamplesTest {

	static {
		AssertionExamples.class.getClassLoader()
				.setClassAssertionStatus(AssertionExamples.class.getName(), true);
	}

	private final AssertionExamples examples = new AssertionExamples();

	@Test
	void assertionsCanBeEnabledForDevelopmentChecks() {
		assertThat(examples.assertionsEnabled())
				.as("Assertions must be enabled before assert statements execute")
				.isTrue();
	}

	@Test
	void assertionPassesWhenInternalInvariantHolds() {
		assertThat(examples.remainingCapacity(10, 4))
				.as("An assert statement should stay invisible when the internal invariant is true")
				.isEqualTo(6);
	}

	@Test
	void assertionFailsWhenInternalInvariantIsBroken() {
		assertThatThrownBy(() -> examples.remainingCapacity(4, 10))
				.as("Assertions should expose impossible internal states during development and testing")
				.isInstanceOf(AssertionError.class)
				.hasMessageContaining("reserved capacity");
	}

	@Test
	void publicInputValidationUsesOrdinaryExceptions() {
		assertThat(examples.normalizeUsername("  rod  "))
				.as("Public input validation should be normal program logic, not an assert statement")
				.isEqualTo("rod");

		assertThatThrownBy(() -> examples.normalizeUsername("  "))
				.as("Assertions can be disabled, so they should not enforce public API contracts")
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("username");
	}
}
