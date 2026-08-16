package net.jrodolfo.java_evolution.java25.vector_api;

/**
 * Explains the Vector API, continued as a tenth incubator in Java 25 by JEP
 * 508.
 *
 * <p>
 * This is an explanatory learning module because the Vector API is still an
 * incubator API in the {@code jdk.incubator.vector} module. A faithful
 * executable example would require incubator-module build configuration, which
 * would complicate the whole Maven project for an API that is still evolving.
 * </p>
 */
public class VectorApiTenthIncubatorNotes {

	/**
	 * Explains the problem that the Vector API addresses.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "scalar loops process one value at a time even when the same operation could be applied across many values";
	}

	/**
	 * Explains the Java 25 feature idea.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "the Vector API lets Java code express lane-wise vector computations that the JVM can compile to CPU vector instructions";
	}

	/**
	 * Defines SIMD in plain English.
	 *
	 * @return a short terminology explanation
	 */
	public String terminology() {
		return "SIMD means Single Instruction, Multiple Data: one instruction applies the same operation across multiple vector lanes";
	}

	/**
	 * Explains why this project documents the feature without compiling an
	 * incubator API example.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps the Vector API as explanatory notes because Java 25 keeps it incubating in the jdk.incubator.vector module";
	}

	/**
	 * Describes the Java 25 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "Vector API is in tenth incubator in Java 25";
	}
}
