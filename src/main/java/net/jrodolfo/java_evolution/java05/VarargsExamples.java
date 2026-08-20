package net.jrodolfo.java_evolution.java05;

/**
 * Demonstrates varargs, introduced in Java 5.
 */
public class VarargsExamples {

	/**
	 * Joins zero or more labels. Callers can pass individual arguments instead of
	 * manually creating an array.
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
	 * Shows that a varargs parameter is an array inside the method.
	 *
	 * @param values values passed by the caller
	 * @return number of values
	 */
	public int countValues(int... values) {
		return values.length;
	}
}
