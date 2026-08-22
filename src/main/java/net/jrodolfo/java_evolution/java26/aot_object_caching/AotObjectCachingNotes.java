package net.jrodolfo.java_evolution.java26.aot_object_caching;

/**
 * Explains Java 26 ahead-of-time object caching with any garbage collector.
 */
public class AotObjectCachingNotes {

	/**
	 * Explains the runtime problem.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "application startup can repeat object creation and initialization work that is predictable across runs";
	}

	/**
	 * Describes the Java 26 idea.
	 *
	 * @return a short feature explanation
	 */
	public String java26Idea() {
		return "ahead-of-time object caching stores selected initialized objects for faster startup and can be used with any GC";
	}

	/**
	 * Explains why this is notes-only.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "AOT object caching is runtime and command-line behavior, so this repository documents it as notes";
	}
}
