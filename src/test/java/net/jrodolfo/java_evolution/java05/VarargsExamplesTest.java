package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

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

	@Test
	void mandatoryFirstParameterPreventsEmptyMaximumCall() {
		assertThat(examples.max(7))
				.as("The first parameter should make a non-empty maximum call possible without runtime empty checks")
				.isEqualTo(7);
		assertThat(examples.max(7, 3, 11, 5))
				.as("The varargs tail should still accept additional values")
				.isEqualTo(11);
	}

	@Test
	void iterableOverloadFitsCallersThatAlreadyHaveACollection() {
		// Given
		List<String> labels = Arrays.asList("varargs", "collections", "api design");

		// When
		String joinedLabels = examples.joinLabels(labels);

		// Then
		assertThat(joinedLabels)
				.as("An Iterable overload should avoid forcing collection callers to create an array")
				.isEqualTo("varargs, collections, api design");
	}
}
