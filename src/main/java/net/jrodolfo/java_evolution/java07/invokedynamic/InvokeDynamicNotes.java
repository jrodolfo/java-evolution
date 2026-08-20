package net.jrodolfo.java_evolution.java07.invokedynamic;

/**
 * Explains Java 7 {@code invokedynamic} support.
 */
public class InvokeDynamicNotes {

	/**
	 * @return the problem addressed by {@code invokedynamic}
	 */
	public String problemSolved() {
		return "dynamic languages needed a JVM invocation mechanism that did not force every call into statically targeted Java method invocation";
	}

	/**
	 * @return the core runtime pieces
	 */
	public String corePieces() {
		return "invokedynamic, bootstrap methods, dynamic call sites, method handles, and java.lang.invoke";
	}

	/**
	 * @return the linkage lifecycle
	 */
	public String linkageLifecycle() {
		return "an unlinked dynamic call site is linked on first execution by a bootstrap method that provides a method-handle target";
	}

	/**
	 * @return why this repository uses notes
	 */
	public String repositoryDecision() {
		return "ordinary Java source does not directly spell an invokedynamic instruction, so notes are more faithful than source-code strings";
	}
}
