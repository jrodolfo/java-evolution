package net.jrodolfo.java_evolution.java01;

/**
 * Demonstrates inner classes, added in Java 1.1.
 */
public class InnerClassExamples {

	private final String prefix;

	public InnerClassExamples(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Uses an inner class that can read state from the enclosing instance.
	 *
	 * @param value value to format
	 * @return formatted value
	 */
	public String formatWithInnerClass(String value) {
		Formatter formatter = new Formatter();
		return formatter.format(value);
	}

	class Formatter {
		String format(String value) {
			return prefix + ": " + value;
		}
	}

	/**
	 * Uses an anonymous inner class, the common pre-lambda callback shape.
	 *
	 * @param value value to transform
	 * @return transformed value
	 */
	public String transformWithAnonymousClass(String value) {
		Transformer transformer = new Transformer() {
			public String transform(String input) {
				return input.toUpperCase();
			}
		};
		return transformer.transform(value);
	}

	interface Transformer {
		String transform(String input);
	}
}
