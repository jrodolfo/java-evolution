package net.jrodolfo.java_evolution.java05;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates generics, introduced in Java 5.
 *
 * <p>
 * Before generics, collections usually stored {@link Object} values. Callers had
 * to cast values back to the expected type, so type mistakes could survive until
 * runtime. Generics move that check into the type system.
 * </p>
 */
public class GenericsExamples {

	/**
	 * Creates a typed list of names.
	 *
	 * @param names names to copy
	 * @return a new list whose element type is known to the compiler
	 */
	public List<String> typedNames(List<String> names) {
		return new ArrayList<>(names);
	}

	/**
	 * Returns the first element from a typed list without a cast.
	 *
	 * @param values values to inspect
	 * @return the first value
	 * @param <T> the list element type
	 */
	public <T> T first(List<T> values) {
		return values.get(0);
	}

	/**
	 * Shows the old raw-list shape that generics were designed to replace.
	 *
	 * @param rawValues raw values without an element type
	 * @return the first value cast to {@link String}
	 */
	public String firstRawValue(List rawValues) {
		return (String) rawValues.get(0);
	}
}
