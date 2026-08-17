package net.jrodolfo.java_evolution.java19.structured_concurrency;

/**
 * Explains structured concurrency as an incubating feature in Java 19.
 *
 * <p>
 * Concurrent code becomes difficult when related subtasks are started,
 * cancelled, joined, and observed in different places. Structured concurrency
 * addresses that by treating related subtasks as one bounded unit of work with
 * clearer failure and cancellation behavior.
 * </p>
 *
 * <p>
 * The structured concurrency API evolved after Java 19, so this repository uses
 * notes instead of depending on an incubator module or a specific preview API
 * shape.
 * </p>
 */
public class StructuredConcurrencyPreviewNotes {

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
	 * Explains the first incubator idea.
	 *
	 * @return a short explanation
	 */
	public String incubatorIdea() {
		return "structured concurrency introduced the idea of treating related subtasks as one structured unit of work";
	}

	/**
	 * Names the practical benefits.
	 *
	 * @return key benefits
	 */
	public String coordinationBenefits() {
		return "joining, cancellation, failure handling, cleanup, and observability become easier to reason about";
	}

	/**
	 * Clarifies the official Java 19 status.
	 *
	 * @return a short status note
	 */
	public String officialStatus() {
		return "structured concurrency was introduced in Java 19 as an incubating API, not as a preview feature";
	}

	/**
	 * Explains why this repository keeps it explanatory.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 19 API required an incubator module, so this repository documents the concept without enabling old incubator APIs";
	}

	/**
	 * Points learners to the next step.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 20 structured_concurrency module next, then the Java 25 structured_concurrency module for the later preview workflow";
	}
}
