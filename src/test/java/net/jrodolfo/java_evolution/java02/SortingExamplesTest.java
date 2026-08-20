package net.jrodolfo.java_evolution.java02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SortingExamplesTest {

	private final SortingExamples examples = new SortingExamples();

	@Test
	void collectionsSortUsesNaturalOrdering() {
		assertThat(examples.sortNaturally(Arrays.asList(3, 1, 2)))
				.as("Collections.sort should use natural ordering for Comparable values")
				.containsExactly(1, 2, 3);
	}

	@Test
	void comparatorDefinesCustomOrdering() {
		assertThat(examples.sortByLength(Arrays.asList("Maria", "Li", "Ana")))
				.as("Comparator should define ordering outside the element class")
				.containsExactly("Li", "Ana", "Maria");
	}
}
