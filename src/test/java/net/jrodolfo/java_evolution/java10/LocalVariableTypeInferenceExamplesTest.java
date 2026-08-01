package net.jrodolfo.java_evolution.java10;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LocalVariableTypeInferenceExamplesTest {

	private final LocalVariableTypeInferenceExamples examples = new LocalVariableTypeInferenceExamples();

	@Test
	void varCanInferTheTypeOfALocalVariable() {
		// Given
		var names = Arrays.asList("Ana", "Rodolfo", "Bruna");

		// When
		var count = examples.countNames(names);

		// Then
		assertThat(count)
				.as("The compiler should infer that names is a List<String>")
				.isEqualTo(3);
	}

	@Test
	void varCanBeUsedInEnhancedForLoops() {
		// Given
		var words = Arrays.asList("java", "ten");

		// When
		Map<String, Integer> lengths = examples.wordLengths(words);

		// Then
		assertThat(lengths)
				.as("The loop variable declared with var should still be inferred as String")
				.containsEntry("java", 4)
				.containsEntry("ten", 3);
	}

	@Test
	void varCanBeUsedInTryWithResources() throws IOException {
		// Given
		var text = "first line\nsecond line";

		// When
		var firstLine = examples.readFirstLine(text);

		// Then
		assertThat(firstLine)
				.as("The resource variable declared with var should behave like a BufferedReader")
				.isEqualTo("first line");
	}

	@Test
	void varIsStillStaticallyTyped() {
		// When
		var typeName = examples.inferredTypeStillHasAConcreteClass();

		// Then
		assertThat(typeName)
				.as("var should infer a concrete compile-time type, not a dynamic type")
				.isEqualTo("String");
	}

	@Test
	void java10VarIsLimitedToLocalVariables() {
		// When
		var explanation = examples.whereVarCanBeUsed();

		// Then
		assertThat(explanation)
				.as("The example should document the Java 10 scope of var")
				.contains("local variables")
				.contains("not fields")
				.contains("method return types");
	}
}
