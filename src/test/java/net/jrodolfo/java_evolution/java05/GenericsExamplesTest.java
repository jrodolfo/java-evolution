package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

	@Test
	void boundedWildcardAcceptsIterableOfSubtypesWhenOnlyReading() {
		// Given
		List<GenericsExamples.LanguageFeature> languageFeatures =
				Arrays.asList(new GenericsExamples.LanguageFeature(5), new GenericsExamples.LanguageFeature(3));
		Set<GenericsExamples.PlatformFeature> platformFeatures = new LinkedHashSet<GenericsExamples.PlatformFeature>(
				Arrays.asList(new GenericsExamples.PlatformFeature(2), new GenericsExamples.PlatformFeature(4)));

		// When
		int languageTotal = examples.totalImportance(languageFeatures);
		int platformTotal = examples.totalImportance(platformFeatures);

		// Then
		assertThat(languageTotal)
				.as("Iterable<? extends ReleaseFeature> should accept a producer of language-feature subtypes")
				.isEqualTo(8);
		assertThat(platformTotal)
				.as("The same API should accept a different subtype without requiring a copy")
				.isEqualTo(6);
	}

	@Test
	void multipleBoundsRequireMoreThanOneCapability() {
		// Given
		GenericsExamples.RankedFeature feature = new GenericsExamples.RankedFeature("generics", 1);

		// When
		String description = examples.describePrioritizedFeature(feature);

		// Then
		assertThat(description)
				.as("A multiple-bound type parameter should let the method use both required capabilities")
				.isEqualTo("generics has priority 1");
	}
}
