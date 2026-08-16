package net.jrodolfo.java_evolution.java25.generational_shenandoah;

/**
 * Explains Generational Shenandoah, introduced as a product feature in Java 25
 * by JEP 521.
 *
 * <p>
 * This is an explanatory learning module because garbage-collector behavior is
 * JVM runtime behavior. A faithful demonstration requires a realistic workload,
 * JVM options, garbage-collection logs, and measurements rather than ordinary
 * Java source code.
 * </p>
 */
public class GenerationalShenandoahNotes {

	/**
	 * Explains what garbage collection does.
	 *
	 * @return a short problem statement
	 */
	public String garbageCollectionProblem() {
		return "garbage collection reclaims heap memory from objects that are no longer reachable";
	}

	/**
	 * Explains Shenandoah's high-level goal.
	 *
	 * @return a short explanation
	 */
	public String shenandoahGoal() {
		return "Shenandoah is a low-pause garbage collector that performs much of its work concurrently with the application";
	}

	/**
	 * Explains the generational idea.
	 *
	 * @return a short explanation
	 */
	public String generationalIdea() {
		return "generational garbage collection uses the observation that many objects die young";
	}

	/**
	 * Explains the Java 25 status change.
	 *
	 * @return a short status explanation
	 */
	public String java25Status() {
		return "Java 25 changed Shenandoah's generational mode from an experimental feature to a product feature";
	}

	/**
	 * Explains the runtime option.
	 *
	 * @return a short command-line note
	 */
	public String option() {
		return "generational Shenandoah can be selected with -XX:+UseShenandoahGC and -XX:ShenandoahGCMode=generational, but it is not the default Shenandoah mode in Java 25";
	}

	/**
	 * Explains why this project documents the feature without a garbage-collector
	 * benchmark.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps Generational Shenandoah as explanatory notes because it requires realistic workloads, GC logs, and measurements";
	}
}
