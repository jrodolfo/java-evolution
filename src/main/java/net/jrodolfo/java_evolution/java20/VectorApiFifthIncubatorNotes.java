package net.jrodolfo.java_evolution.java20;

/**
 * Explains the Vector API fifth incubator in Java 20.
 *
 * <p>
 * Modern CPUs can process multiple values at once with vector instructions, but
 * ordinary Java loops do not always make that intent obvious to the JVM. The
 * Vector API gives Java code a way to express vector computations directly
 * while still letting the runtime map them to efficient hardware instructions
 * when possible.
 * </p>
 *
 * <p>
 * The Java 20 API was incubating and requires an incubator module, so this
 * repository keeps the topic as notes.
 * </p>
 */
public class VectorApiFifthIncubatorNotes {

	/**
	 * Explains the performance-oriented goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "express vector computations that can compile to efficient CPU vector instructions";
	}

	/**
	 * Explains why this project avoids a direct dependency.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Vector API was incubating, so this repository avoids depending on the incubator module";
	}
}
