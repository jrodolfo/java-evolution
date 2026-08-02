package net.jrodolfo.java_evolution.java22;

/**
 * Demonstrates unnamed variables and patterns, finalized in Java 22.
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

	@FunctionalInterface
	public interface IntPairOperation {
		int apply(int left, int right);
	}
}
