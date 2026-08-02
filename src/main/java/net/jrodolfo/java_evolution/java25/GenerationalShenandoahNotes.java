package net.jrodolfo.java_evolution.java25;

/**
 * Explains Generational Shenandoah introduced in Java 25.
 *
 * <p>
 * Generational garbage collectors use the observation that many objects die
 * young. Generational Shenandoah separates heap management by object age to
 * improve collection behavior for suitable workloads.
 * </p>
 */
public class GenerationalShenandoahNotes {
	/**
	 * Explains the garbage-collector goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "split Shenandoah heap management by object age to improve garbage collection behavior";
	}
}
