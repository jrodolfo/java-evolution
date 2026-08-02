package net.jrodolfo.java_evolution.java10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates local variable type inference, introduced in Java 10 through the
 * {@code var} reserved type name.
 *
 * <p>
 * Before Java 10, local variables sometimes repeated long generic types that
 * were already obvious from the initializer. That made code noisier without
 * adding much information.
 * </p>
 *
 * <p>
 * {@code var} solves that local readability problem. It does not make Java
 * dynamically typed: the compiler still infers a specific static type from the
 * initializer. In Java 10 it is limited to local variables, including variables
 * in loops and try-with-resources blocks.
 * </p>
 */
public class LocalVariableTypeInferenceExamples {

	/**
	 * Uses {@code var} for a local variable whose constructor initializer makes a
	 * longer generic type obvious.
	 *
	 * @param names the names to count
	 * @return the number of names
	 */
	public int countNames(List<String> names) {
		var copiedNames = new ArrayList<String>(names);
		return copiedNames.size();
	}

	/**
	 * Uses {@code var} with a generic collection to avoid repeating the full type
	 * on both sides of the assignment.
	 *
	 * @param words the words to inspect
	 * @return a map from each word to its length
	 */
	public Map<String, Integer> wordLengths(List<String> words) {
		var lengths = new HashMap<String, Integer>();

		for (var word : words) {
			lengths.put(word, word.length());
		}

		return lengths;
	}

	/**
	 * Uses {@code var} in a try-with-resources declaration.
	 *
	 * @param text text read through a {@link BufferedReader}
	 * @return the first line of the text
	 * @throws IOException when reading fails
	 */
	public String readFirstLine(String text) throws IOException {
		try (var reader = new BufferedReader(new StringReader(text))) {
			return reader.readLine();
		}
	}

	/**
	 * Shows that a {@code var} variable still has a real compile-time type.
	 *
	 * @return the runtime class name of a variable inferred as {@link String}
	 */
	public String inferredTypeStillHasAConcreteClass() {
		var feature = "local variable type inference";
		return feature.getClass().getSimpleName();
	}

	/**
	 * Keeps the explicit type in the method signature because Java 10 {@code var}
	 * is only for local variables.
	 *
	 * @return a sentence summarizing the scope of {@code var}
	 */
	public String whereVarCanBeUsed() {
		return "var can be used for local variables, not fields or method return types";
	}
}
