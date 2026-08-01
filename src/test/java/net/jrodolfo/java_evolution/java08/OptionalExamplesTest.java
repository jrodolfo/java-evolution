package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OptionalExamplesTest {

	private final OptionalExamples examples = new OptionalExamples();

	@Test
	void wrapsNullableValueInOptional() {
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", "rodolfo@example.com");

		assertThat(examples.findEmail(user))
				.contains("rodolfo@example.com");
		assertThat(examples.findEmail(null))
				.isEmpty();
	}

	@Test
	void providesDefaultValueWhenOptionalIsEmpty() {
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		assertThat(examples.displayEmail(user))
				.isEqualTo("email not provided");
	}

	@Test
	void createsDefaultValueLazilyWhenOptionalIsEmpty() {
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		assertThat(examples.displayEmailGeneratedLazily(user))
				.isEqualTo("email generated only when Optional is empty");
	}

	@Test
	void throwsExceptionWhenRequiredValueIsMissing() {
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		assertThatThrownBy(() -> examples.requireEmail(user))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("email is required");
	}

	@Test
	void transformsAndFiltersOptionalValue() {
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", " RODOLFO@EXAMPLE.COM ");

		assertThat(examples.normalizeEmail(user))
				.contains("rodolfo@example.com");
	}

	@Test
	void flatMapsWhenTransformationAlreadyReturnsOptional() {
		OptionalExamples.User workUser = new OptionalExamples.User("Rodolfo", "rodolfo@example.com");
		OptionalExamples.User personalUser = new OptionalExamples.User("Ana", "ana@gmail.com");

		assertThat(examples.findCompanyDomain(workUser))
				.contains("example.com");
		assertThat(examples.findCompanyDomain(personalUser))
				.isEmpty();
	}
}
