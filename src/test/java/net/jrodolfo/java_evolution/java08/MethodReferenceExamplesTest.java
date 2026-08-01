package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MethodReferenceExamplesTest {

	private final MethodReferenceExamples examples = new MethodReferenceExamples();

	@Test
	void staticMethodReferenceParsesTextNumbers() {
		// Given
		List<String> numbers = Arrays.asList("8", "21", "25");

		// When
		List<Integer> parsedNumbers = examples.parseNumbers(numbers);

		// Then
		assertThat(parsedNumbers)
				.as("Integer::parseInt should convert each String into an Integer")
				.containsExactly(8, 21, 25);
	}

	@Test
	void boundInstanceMethodReferenceUsesExistingObject() {
		// Given
		List<String> names = Arrays.asList("Ana", "Rodolfo");

		// When
		List<String> formattedNames = examples.formatNames(names);

		// Then
		assertThat(formattedNames)
				.as("formatter::format should call the format method on the same formatter instance")
				.containsExactly("Name: Ana", "Name: Rodolfo");
	}

	@Test
	void unboundInstanceMethodReferenceUsesEachElementAsReceiver() {
		// Given
		List<String> names = Arrays.asList("bruna", "Ana", "rodolfo");

		// When
		List<String> sortedNames = examples.sortIgnoringCase(names);

		// Then
		assertThat(sortedNames)
				.as("String::compareToIgnoreCase should compare each pair of String values")
				.containsExactly("Ana", "bruna", "rodolfo");
	}

	@Test
	void constructorReferenceCreatesObjectsFromValues() {
		// Given
		List<String> names = Arrays.asList("Ana", "Rodolfo");

		// When
		List<MethodReferenceExamples.User> users = examples.createUsers(names);

		// Then
		assertThat(users)
				.as("User::new should create one User for each name")
				.extracting(MethodReferenceExamples.User::name)
				.containsExactly("Ana", "Rodolfo");
	}

	@Test
	void constructorReferenceCanBeAssignedToFunction() {
		// When
		MethodReferenceExamples.User user = examples.createUserWithFunction("Rodolfo");

		// Then
		assertThat(user.name())
				.as("A Function<String, User> can point to User::new")
				.isEqualTo("Rodolfo");
	}

	@Test
	void zeroArgumentConstructorReferenceCanBeAssignedToSupplier() {
		// When
		MethodReferenceExamples.NameFormatter formatter = examples.createFormatterWithSupplier();

		// Then
		assertThat(formatter.format("Ana"))
				.as("A Supplier<NameFormatter> can point to NameFormatter::new")
				.isEqualTo("Name: Ana");
	}
}
