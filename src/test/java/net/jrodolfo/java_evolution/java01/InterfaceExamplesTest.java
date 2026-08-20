package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InterfaceExamplesTest {

	private final InterfaceExamples examples = new InterfaceExamples();

	@Test
	void interfaceCanRepresentUnrelatedImplementations() {
		assertThat(examples.renderThroughInterface())
				.as("Different classes should be callable through the same interface contract")
				.isEqualTo("java / [java]");
	}
}
