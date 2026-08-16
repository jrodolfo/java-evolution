package net.jrodolfo.java_evolution.java22.stream_gatherers;

/**
 * Explains Stream Gatherers, introduced as a Java 22 preview feature.
 *
 * <p>
 * Streams already had many built-in intermediate operations, such as
 * {@code map}, {@code filter}, and {@code limit}. Some transformations were
 * still awkward because they needed buffering, state, or a custom rule for
 * when to emit output elements. Gatherers were introduced as a standard
 * extension point for those custom intermediate stream operations.
 * </p>
 *
 * <p>
 * This Java 22 package keeps the feature explanatory because gatherers were
 * still preview in Java 22. The final runnable example lives in the Java 24
 * package, where Stream Gatherers became final.
 * </p>
 */
public class StreamGatherersPreviewNotes {

	/**
	 * Explains the central problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "some stream transformations need buffering, state, or custom emission rules";
	}

	/**
	 * Explains the central API idea.
	 *
	 * @return a short explanation
	 */
	public String whatJavaIntroduced() {
		return "stream gatherers are a standard extension point for custom intermediate stream operations";
	}

	/**
	 * Names transformations that show why gatherers are useful.
	 *
	 * @return example transformations
	 */
	public String exampleUseCases() {
		return "fixed windows, running scans, batching, and other stateful transformations";
	}

	/**
	 * Explains the preview status.
	 *
	 * @return a short preview-status note
	 */
	public String previewStatus() {
		return "Stream Gatherers were preview in Java 22 and became final in Java 24";
	}

	/**
	 * Points learners to the final Java 24 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read StreamGatherersExamples in Java 24 for final runnable examples using windowFixed and scan";
	}
}
