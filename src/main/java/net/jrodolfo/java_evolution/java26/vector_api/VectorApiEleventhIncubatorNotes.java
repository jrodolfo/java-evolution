package net.jrodolfo.java_evolution.java26.vector_api;

/**
 * Explains the eleventh incubator of the Vector API in Java 26.
 */
public class VectorApiEleventhIncubatorNotes {

	/**
	 * Defines the programming model.
	 *
	 * @return a short definition
	 */
	public String programmingModel() {
		return "the Vector API expresses Single Instruction, Multiple Data computations in Java";
	}

	/**
	 * Explains the performance goal.
	 *
	 * @return a short goal statement
	 */
	public String goal() {
		return "the JVM can map vector operations to CPU vector instructions when the platform supports them";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "the Vector API is an eleventh incubator API in Java 26";
	}
}
