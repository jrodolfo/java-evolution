package net.jrodolfo.java_evolution.java05;

/**
 * Demonstrates varargs, introduced in Java 5.
 */
public class VarargsExamples {

	/**
	 * Joins zero or more labels. Callers can pass individual arguments instead of
	 * manually creating an array.
	 *
	 * <p>
	 * This is convenient for call sites such as {@code joinLabels("one", "two")}.
	 * If the caller already has a collection or other {@link Iterable}, an
	 * iterable-based overload avoids forcing the caller to create an array only to
	 * call a varargs method.
	 * </p>
	 *
	 * @param labels labels to join
	 * @return comma-separated labels
	 */
	public String joinLabels(String... labels) {
		StringBuilder joinedLabels = new StringBuilder();

		for (int index = 0; index < labels.length; index++) {
			if (index > 0) {
				joinedLabels.append(", ");
			}
			joinedLabels.append(labels[index]);
		}

		return joinedLabels.toString();
	}

	/**
	 * Joins labels supplied by an existing {@link Iterable}.
	 *
	 * @param labels labels to join
	 * @return comma-separated labels
	 */
	public String joinLabels(Iterable<String> labels) {
		StringBuilder joinedLabels = new StringBuilder();
		boolean firstLabel = true;

		for (String label : labels) {
			if (!firstLabel) {
				joinedLabels.append(", ");
			}
			joinedLabels.append(label);
			firstLabel = false;
		}

		return joinedLabels.toString();
	}

	/**
	 * Shows that a varargs parameter is an array inside the method.
	 *
	 * @param values values passed by the caller
	 * @return number of values
	 */
	public int countValues(int... values) {
		return values.length;
	}

	/**
	 * Returns the maximum value while requiring at least one argument.
	 *
	 * <p>
	 * The first parameter is mandatory, so callers cannot accidentally invoke this
	 * method with an empty argument list. The varargs parameter still lets callers
	 * pass any number of additional values.
	 * </p>
	 *
	 * @param first required first value
	 * @param rest optional remaining values
	 * @return maximum value
	 */
	public int max(int first, int... rest) {
		int maximum = first;
		for (int value : rest) {
			if (value > maximum) {
				maximum = value;
			}
		}
		return maximum;
	}
}
