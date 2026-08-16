package net.jrodolfo.java_evolution.java25.jfr_enhancements;

/**
 * Explains Java Flight Recorder (JFR) enhancements introduced in Java 25.
 *
 * <p>
 * This is an explanatory learning module because JFR features are runtime
 * observability features. A faithful demonstration requires a running
 * application, a workload, a JFR recording, and analysis of that recording.
 * </p>
 */
public class JfrEnhancementsNotes {

	/**
	 * Explains the problem that JFR helps solve.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "developers need runtime evidence before deciding which application behavior or performance problem to optimize";
	}

	/**
	 * Names the Java 25 JFR areas represented by this learning module.
	 *
	 * @return feature summary
	 */
	public String features() {
		return "CPU-time profiling, cooperative sampling, and method timing and tracing";
	}

	/**
	 * Explains CPU-time profiling.
	 *
	 * @return a short explanation
	 */
	public String cpuTimeProfiling() {
		return "JFR CPU-time profiling is an experimental Linux feature that samples time spent actually running on the CPU";
	}

	/**
	 * Explains cooperative sampling.
	 *
	 * @return a short explanation
	 */
	public String cooperativeSampling() {
		return "JFR cooperative sampling improves stack-sampling stability by reconstructing Java stacks at safepoints";
	}

	/**
	 * Explains method timing and tracing.
	 *
	 * @return a short explanation
	 */
	public String methodTimingAndTracing() {
		return "JFR method timing and tracing can collect invocation timing and stack information for selected methods";
	}

	/**
	 * Explains why this project documents the feature without starting a JFR
	 * recording in unit tests.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps JFR enhancements as explanatory notes because they require a running application, workload, recording, and analysis";
	}
}
