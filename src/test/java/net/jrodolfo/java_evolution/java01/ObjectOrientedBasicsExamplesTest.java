package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ObjectOrientedBasicsExamplesTest {

	private final ObjectOrientedBasicsExamples examples = new ObjectOrientedBasicsExamples();

	@Test
	void runtimeDispatchUsesSubclassOverride() {
		assertThat(examples.dispatchThroughSuperclass())
				.as("A superclass reference should dispatch to the subclass override at runtime")
				.isEqualTo("rectangle 4x3");
	}

	@Test
	void objectEncapsulatesStateBehindBehavior() {
		assertThat(examples.rectangleArea())
				.as("The rectangle should keep dimensions private and expose area as behavior")
				.isEqualTo(10);
	}
}
