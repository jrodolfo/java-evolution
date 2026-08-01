package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OptionalExamplesTest {

	private final OptionalExamples examples = new OptionalExamples();

	@Test
	void ofNullableCreatesPresentOrEmptyOptional() {
		// Given
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", "rodolfo@example.com");

		// When / Then
		assertThat(examples.findEmail(user))
				.as("A non-null email should produce a present Optional")
				.contains("rodolfo@example.com");
		assertThat(examples.findEmail(null))
				.as("A null user should produce an empty Optional")
				.isEmpty();
	}

	@Test
	void orElseProvidesADefaultValueWhenOptionalIsEmpty() {
		// Given
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		// When
		String displayedEmail = examples.displayEmail(user);

		// Then
		assertThat(displayedEmail)
				.as("orElse should provide a default value for an empty Optional")
				.isEqualTo("email not provided");
	}

	@Test
	void orElseGetCreatesDefaultValueLazilyWhenOptionalIsEmpty() {
		// Given
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		// When
		String displayedEmail = examples.displayEmailGeneratedLazily(user);

		// Then
		assertThat(displayedEmail)
				.as("orElseGet should call the Supplier only when the Optional is empty")
				.isEqualTo("email generated only when Optional is empty");
	}

	@Test
	void orElseThrowFailsWhenRequiredValueIsMissing() {
		// Given
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", null);

		// When / Then
		assertThatThrownBy(() -> examples.requireEmail(user))
				.as("orElseThrow should make a missing required value explicit")
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("email is required");
	}

	@Test
	void mapAndFilterTransformAndValidateOptionalValue() {
		// Given
		OptionalExamples.User user = new OptionalExamples.User("Rodolfo", " RODOLFO@EXAMPLE.COM ");

		// When / Then
		assertThat(examples.normalizeEmail(user))
				.as("map should transform the email, and filter should keep only valid addresses")
				.contains("rodolfo@example.com");
	}

	@Test
	void flatMapAvoidsNestedOptionalWhenTransformationReturnsOptional() {
		// Given
		OptionalExamples.User workUser = new OptionalExamples.User("Rodolfo", "rodolfo@example.com");
		OptionalExamples.User personalUser = new OptionalExamples.User("Ana", "ana@gmail.com");

		// When / Then
		assertThat(examples.findCompanyDomain(workUser))
				.as("A company email should expose its domain")
				.contains("example.com");
		assertThat(examples.findCompanyDomain(personalUser))
				.as("A Gmail address is treated as personal, so no company domain is returned")
				.isEmpty();
	}
}
