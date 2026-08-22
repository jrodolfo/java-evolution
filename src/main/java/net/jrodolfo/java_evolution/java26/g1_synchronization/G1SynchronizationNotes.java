package net.jrodolfo.java_evolution.java26.g1_synchronization;

/**
 * Explains Java 26 G1 throughput improvements from reduced synchronization.
 */
public class G1SynchronizationNotes {

	/**
	 * Defines G1 in learner-friendly terms.
	 *
	 * @return a short definition
	 */
	public String definition() {
		return "G1 is the Garbage-First garbage collector, the default server collector for many Java workloads";
	}

	/**
	 * Explains the Java 26 runtime improvement.
	 *
	 * @return a short improvement note
	 */
	public String improvement() {
		return "Java 26 improves G1 throughput by reducing synchronization inside garbage-collector work";
	}

	/**
	 * Explains why the repository keeps this as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "GC throughput improvements are runtime behavior and should be measured with workloads, not tiny unit tests";
	}
}
