package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VarargsExamplesTest {

	private final VarargsExamples examples = new VarargsExamples();

	@Test
	void varargsAcceptZeroOneOrManyArguments() {
		assertThat(examples.joinLabels())
				.as("A varargs call may pass no trailing arguments")
				.isEmpty();
		assertThat(examples.joinLabels("generics"))
				.as("A varargs call may pass one trailing argument")
				.isEqualTo("generics");
		assertThat(examples.joinLabels("generics", "enums", "annotations"))
				.as("A varargs call may pass many trailing arguments")
				.isEqualTo("generics, enums, annotations");
	}

	@Test
	void varargsParameterBehavesLikeArrayInsideMethod() {
		assertThat(examples.countValues(1, 2, 3))
				.as("A varargs parameter is received as an array by the method")
				.isEqualTo(3);
	}
}
