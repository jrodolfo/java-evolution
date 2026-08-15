package net.jrodolfo.java_evolution.java25.stable_values;

/**
 * Explains Stable Values, previewed in Java 25 by JEP 502.
 *
 * <p>
 * This is an explanatory learning module because Stable Values are a preview
 * API. A faithful executable example would require enabling preview features for
 * compilation and runtime, which would complicate the whole Maven build for a
 * feature that is still evolving.
 * </p>
 */
public class StableValuesPreviewNotes {

	/**
	 * Explains the problem that Stable Values address.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "some expensive values should be initialized lazily but become immutable after the first successful initialization";
	}

	/**
	 * Explains how developers commonly handled lazy initialization before this
	 * preview API.
	 *
	 * @return a short before-Java-25 explanation
	 */
	public String commonAlternative() {
		return "developers often used mutable nullable fields, synchronized access, suppliers, or double-checked locking for lazy initialization";
	}

	/**
	 * Explains the Java 25 feature goal.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "StableValue models deferred immutability: content is initialized at most once and can then be treated as stable by the JVM";
	}

	/**
	 * Explains why this project documents the feature without compiling a preview
	 * API example.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps Stable Values as explanatory notes because the Java 25 API is preview and requires --enable-preview";
	}
}
