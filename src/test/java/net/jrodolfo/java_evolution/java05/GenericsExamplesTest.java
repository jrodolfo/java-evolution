package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class GenericsExamplesTest {

	private final GenericsExamples examples = new GenericsExamples();

	@Test
	void typedListPreservesElementTypeWithoutCasts() {
		// Given
		List<String> names = Arrays.asList("Ana", "Maria");

		// When
		List<String> typedNames = examples.typedNames(names);
		String firstName = examples.first(typedNames);

		// Then
		assertThat(firstName)
				.as("Generics should let callers read a String without an explicit cast")
				.isEqualTo("Ana");
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void rawListAllowsWrongTypeUntilRuntime() {
		// Given
		List rawValues = new ArrayList();
		rawValues.add(42);

		// When
		ClassCastException thrown = null;
		try {
			examples.firstRawValue(rawValues);
		}
		catch (ClassCastException exception) {
			thrown = exception;
		}

		// Then
		assertThat(thrown)
				.as("Raw collections can hide a wrong element type until a cast executes")
				.isNotNull();
	}
}
