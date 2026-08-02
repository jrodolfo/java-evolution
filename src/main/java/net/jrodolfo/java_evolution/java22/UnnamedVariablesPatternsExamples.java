package net.jrodolfo.java_evolution.java22;

/**
 * Demonstrates unnamed variables and patterns, finalized in Java 22.
 *
 * <p>
 * Before Java 22, Java often required names for values that were intentionally
 * unused. Those names could make code look as if the value mattered. The
 * underscore marks the value as deliberately ignored in loops, lambdas, catch
 * clauses, and patterns.
 * </p>
 */
public class UnnamedVariablesPatternsExamples {

	/**
	 * Uses an unnamed lambda parameter to show that one argument is intentionally
	 * ignored.
	 *
	 * @param left the useful value
	 * @param right ignored value
	 * @return a computed value based only on the first argument
	 */
	public int useOnlyFirstValue(int left, int right) {
		IntPairOperation operation = (value, _) -> value * 2;
		return operation.apply(left, right);
	}

	/**
	 * Uses unnamed variables in a loop when only iteration count matters.
	 *
	 * @param values values to iterate over
	 * @return count of values
	 */
	public int countValues(Iterable<String> values) {
		int count = 0;
		for (String _ : values) {
			count++;
		}
		return count;
	}

	/**
	 * Uses an unnamed pattern variable in a switch pattern.
	 *
	 * @param value the value to classify
	 * @return a broad type label
	 */
	public String broadType(Object value) {
		return switch (value) {
			case String _ -> "text";
			case Number _ -> "number";
			case null -> "null";
			default -> "other";
		};
	}

	/**
	 * Small functional interface used to demonstrate an ignored lambda
	 * parameter.
	 */
	@FunctionalInterface
	public interface IntPairOperation {
		/**
		 * Applies an operation to two integer values.
		 *
		 * @param left first value
		 * @param right second value
		 * @return operation result
		 */
		int apply(int left, int right);
	}
}
