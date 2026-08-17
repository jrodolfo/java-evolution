package net.jrodolfo.java_evolution.java20.vector_api;

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
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "numeric code often repeats the same operation over many values one scalar value at a time";
	}

	/**
	 * Explains the common older approach.
	 *
	 * @return a short explanation
	 */
	public String oldApproachProblem() {
		return "developers relied on JIT auto-vectorization or native libraries when they wanted CPU vector instructions";
	}

	/**
	 * Explains the Vector API idea.
	 *
	 * @return a short explanation
	 */
	public String incubatorIdea() {
		return "the Vector API lets Java code express SIMD-style lane-wise vector computations directly";
	}

	/**
	 * Names realistic use cases.
	 *
	 * @return a short list of examples
	 */
	public String realUseCases() {
		return "image processing, audio processing, compression, and numeric or machine-learning loops can benefit";
	}

	/**
	 * Describes the Java 20 maturity level.
	 *
	 * @return a short status note
	 */
	public String incubatorStatus() {
		return "the Vector API was in its fifth incubator round in Java 20";
	}

	/**
	 * Explains why this project avoids a direct dependency.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 20 API required the jdk.incubator.vector module, so this repository keeps it explanatory";
	}

	/**
	 * Points learners to the later Vector API module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 vector_api module for the later incubator learning guide";
	}
}
