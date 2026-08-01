package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrivateInterfaceMethodExamplesTest {

	private final PrivateInterfaceMethodExamples examples = new PrivateInterfaceMethodExamples();

	@Test
	void defaultMethodsCanSharePrivateInterfaceHelpers() {
		// Given
		PrivateInterfaceMethodExamples.MessageFormatter formatter = examples.formatter();

		// When / Then
		assertThat(formatter.standard("  modules  "))
				.as("The default method should reuse private normalization and prefix helpers")
				.isEqualTo("[java 9] modules");
		assertThat(formatter.urgent("  modules  "))
				.as("Another default method should reuse the same private helpers")
				.isEqualTo("[java 9] MODULES");
	}
}
