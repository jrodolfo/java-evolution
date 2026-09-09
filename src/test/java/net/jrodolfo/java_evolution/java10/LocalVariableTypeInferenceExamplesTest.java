package net.jrodolfo.java_evolution.java10;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

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
	void varSupportsOperationsOnItsInferredStaticType() {
		// When
		var uppercaseFeature = examples.inferredTypeSupportsStringOperations();

		// Then
		assertThat(uppercaseFeature)
				.as("var should infer String so String operations remain available")
				.isEqualTo("LOCAL VARIABLE TYPE INFERENCE");
	}

	@Test
	void java10VarIsLimitedToLocalVariables() {
		// Given
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileObject invalidSource = new InMemorySource(
				"public class InvalidVarField { private var name = \"not allowed\"; }");

		// When
		boolean compiled = compiler.getTask(null, null, null,
				Arrays.asList("--release", "10"), null, Arrays.asList(invalidSource)).call();

		// Then
		assertThat(compiled)
				.as("Java 10 var is restricted to local variables, not fields")
				.isFalse();
	}

	private static final class InMemorySource extends SimpleJavaFileObject {

		private final String source;

		private InMemorySource(String source) {
			super(URI.create("string:///InvalidVarField.java"), JavaFileObject.Kind.SOURCE);
			this.source = source;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return source;
		}
	}
}
