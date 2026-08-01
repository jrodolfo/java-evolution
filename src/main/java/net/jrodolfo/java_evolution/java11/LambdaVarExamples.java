package net.jrodolfo.java_evolution.java11;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates {@code var} in lambda parameters, introduced in Java 11.
 *
 * <p>
 * This made lambda parameters consistent with local variables and allowed
 * annotations on inferred lambda parameters.
 * </p>
 */
public class LambdaVarExamples {

	/**
	 * Uses {@code var} for an inferred lambda parameter.
	 *
	 * @param names the names to normalize
	 * @return trimmed lowercase names
	 */
	public List<String> normalizeNames(List<String> names) {
		return names.stream()
				.map((var name) -> name.trim().toLowerCase())
				.collect(Collectors.toList());
	}

	/**
	 * Uses an annotation on a lambda parameter whose type is inferred with
	 * {@code var}.
	 *
	 * @param names the names to inspect
	 * @return the name lengths
	 */
	public List<Integer> nameLengths(List<String> names) {
		return names.stream()
				.map((@ExampleParameter var name) -> name.length())
				.collect(Collectors.toList());
	}

	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ExampleParameter {
	}
}
