package net.jrodolfo.java_evolution.java20.structured_concurrency;

/**
 * Explains structured concurrency as a Java 20 second incubator feature.
 *
 * <p>
 * When a parent operation starts several concurrent subtasks, the subtasks
 * should have a clear lifetime relationship with the parent. Structured
 * concurrency tries to make joining, cancellation, failure handling, and
 * observability part of one coordinated unit of work.
 * </p>
 *
 * <p>
 * The Java 20 API was still incubating, so the repository keeps this entry as
 * notes instead of depending on an incubator module.
 * </p>
 */
public class StructuredConcurrencySecondIncubatorNotes {

	/**
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "a parent operation needs a clear lifetime boundary for related concurrent child tasks";
	}

	/**
	 * Explains why manual coordination is difficult.
	 *
	 * @return a short explanation
	 */
	public String oldApproachProblem() {
		return "ExecutorService, Future, and manual coordination can scatter joining, cancellation, and failure handling";
	}

	/**
	 * Explains the incubator idea.
	 *
	 * @return a short explanation
	 */
	public String incubatorIdea() {
		return "structured concurrency groups related subtasks into one structured unit of work owned by the parent";
	}

	/**
	 * Names the practical benefits.
	 *
	 * @return key benefits
	 */
	public String coordinationBenefits() {
		return "joining, cancellation, failure handling, cleanup, and observability are handled together";
	}

	/**
	 * Describes the Java 20 maturity level.
	 *
	 * @return a short status note
	 */
	public String incubatorStatus() {
		return "structured concurrency was in its second incubator round in Java 20";
	}

	/**
	 * Explains why this project uses an explanatory module.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 20 API required an incubator module, so this repository documents the concept without enabling old incubator APIs";
	}

	/**
	 * Points learners to the later preview module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 structured_concurrency module for the later preview workflow";
	}
}
