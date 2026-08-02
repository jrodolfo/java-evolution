package net.jrodolfo.java_evolution.java20;

/**
 * Explains virtual threads as a Java 20 second preview feature.
 *
 * <p>
 * Java 20 continued the work started in Java 19: make blocking thread-per-task
 * code scale to very high concurrency without forcing developers into callback
 * chains or reactive pipelines for every workload.
 * </p>
 *
 * <p>
 * Virtual threads became final in Java 21. This package keeps Java 20 as notes
 * because the runnable preview-origin example already appears in Java 19 and
 * the final API is demonstrated in Java 21.
 * </p>
 */
public class VirtualThreadsSecondPreviewNotes {

	/**
	 * Explains the problem virtual threads address.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "virtual threads are lightweight threads for high-throughput blocking-style code";
	}

	/**
	 * Names the release where virtual threads became final.
	 *
	 * @return a short release note
	 */
	public String finalRelease() {
		return "virtual threads became final in Java 21";
	}
}
