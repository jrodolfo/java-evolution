package net.jrodolfo.java_evolution.java05;

import static net.jrodolfo.java_evolution.java05.EnumExamples.Priority.URGENT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EnumExamplesTest {

	private final EnumExamples examples = new EnumExamples();

	@Test
	void enumValueIsAClosedTypedConstantWithBehavior() {
		assertThat(examples.score(URGENT))
				.as("An enum constant should carry typed behavior instead of being just an int")
				.isEqualTo(10);

		assertThat(examples.label(URGENT))
				.as("Enums can keep display behavior near the constants")
				.isEqualTo("urgent priority");
	}

	@Test
	void enumValuesCanBeLookedUpByName() {
		assertThat(EnumExamples.Priority.valueOf("NORMAL"))
				.as("Enum.valueOf should resolve a declared constant by name")
				.isEqualTo(EnumExamples.Priority.NORMAL);
	}
}
