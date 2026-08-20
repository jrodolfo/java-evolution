package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StaticImportExamplesTest {

	private final StaticImportExamples examples = new StaticImportExamples();

	@Test
	void staticImportCanMakeWellKnownStaticMembersDirect() {
		assertThat(examples.circleArea(2.0))
				.as("The example uses statically imported Math.PI and Math.pow")
				.isCloseTo(12.566, org.assertj.core.data.Offset.offset(0.001));
	}
}
