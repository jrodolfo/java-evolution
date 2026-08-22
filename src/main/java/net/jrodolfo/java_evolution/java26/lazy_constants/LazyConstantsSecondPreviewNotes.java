package net.jrodolfo.java_evolution.java26.lazy_constants;

/**
 * Explains Lazy Constants, second preview in Java 26.
 */
public class LazyConstantsSecondPreviewNotes {

	/**
	 * Explains the problem Lazy Constants address.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "some values are expensive to create and should be initialized only when first needed";
	}

	/**
	 * Explains why ordinary final fields are not always enough.
	 *
	 * @return a short comparison
	 */
	public String comparisonWithFinalFields() {
		return "final fields are initialized during construction, while lazy constants defer initialization and then become constant-like";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "Lazy Constants are a second preview API in Java 26, following Stable Values in Java 25";
	}
}
