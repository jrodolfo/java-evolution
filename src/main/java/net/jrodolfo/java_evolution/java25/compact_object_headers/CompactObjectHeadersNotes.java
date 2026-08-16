package net.jrodolfo.java_evolution.java25.compact_object_headers;

/**
 * Explains Compact Object Headers, introduced as a product feature in Java 25
 * by JEP 519.
 *
 * <p>
 * This is an explanatory learning module because object headers are JVM
 * implementation details. A faithful demonstration requires runtime flags,
 * memory measurement, and object-layout tooling rather than ordinary Java source
 * code.
 * </p>
 */
public class CompactObjectHeadersNotes {

	/**
	 * Explains the object-layout problem.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "every Java object has JVM metadata in addition to the payload fields visible in source code";
	}

	/**
	 * Explains the memory goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "reduce object header size to improve memory footprint for object-heavy applications";
	}

	/**
	 * Explains the Java 25 status change.
	 *
	 * @return a short status explanation
	 */
	public String java25Status() {
		return "Java 25 changed compact object headers from an experimental feature to a product feature";
	}

	/**
	 * Explains the runtime option.
	 *
	 * @return a short command-line note
	 */
	public String option() {
		return "compact object headers can be enabled with -XX:+UseCompactObjectHeaders and are not the default in Java 25";
	}

	/**
	 * Explains why this project documents the feature without a layout-checking
	 * unit test.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps compact object headers as explanatory notes because they require JVM flags, heap measurement, and object-layout tooling";
	}
}
