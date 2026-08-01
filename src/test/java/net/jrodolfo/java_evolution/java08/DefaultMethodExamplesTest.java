package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DefaultMethodExamplesTest {

	private final DefaultMethodExamples examples = new DefaultMethodExamples();

	@Test
	void implementationCanInheritDefaultMethods() {
		// Given
		DefaultMethodExamples.Named user = examples.regularUser("Rodolfo Neto");

		// When / Then
		assertThat(user.displayName())
				.as("RegularUser does not implement displayName, so it inherits the default method")
				.isEqualTo("Rodolfo Neto");
		assertThat(user.initials())
				.as("RegularUser also inherits the default initials method")
				.isEqualTo("RN");
	}

	@Test
	void implementationCanOverrideOneDefaultMethodAndKeepAnother() {
		// Given
		DefaultMethodExamples.Named user = examples.adminUser("Rodolfo Neto");

		// When / Then
		assertThat(user.displayName())
				.as("AdminUser overrides displayName for a custom label")
				.isEqualTo("Admin: Rodolfo Neto");
		assertThat(user.initials())
				.as("AdminUser still inherits initials because it did not override that method")
				.isEqualTo("RN");
	}
}
