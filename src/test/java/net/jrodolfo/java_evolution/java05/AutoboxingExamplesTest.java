package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AutoboxingExamplesTest {

	private final AutoboxingExamples examples = new AutoboxingExamples();

	@Test
	void primitivesCanBeBoxedIntoWrapperCollection() {
		// When
		List<Integer> numbers = examples.boxedNumbers(10, 20);

		// Then
		assertThat(numbers)
				.as("Autoboxing should convert int values for a List<Integer>")
				.containsExactly(10, 20);
	}

	@Test
	void boxedNumbersCanBeUnboxedForArithmetic() {
		// When
		int sum = examples.sumBoxedNumbers(Arrays.asList(3, 4, 5));

		// Then
		assertThat(sum)
				.as("Unboxing should let wrapper values participate in primitive arithmetic")
				.isEqualTo(12);
	}

	@Test
	void unboxingNullStillThrowsNullPointerException() {
		NullPointerException thrown = null;
		try {
			examples.unbox(null);
		}
		catch (NullPointerException exception) {
			thrown = exception;
		}

		assertThat(thrown)
				.as("Autounboxing is compiler convenience, not null safety")
				.isNotNull();
	}
}
