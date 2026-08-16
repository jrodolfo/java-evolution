package net.jrodolfo.java_evolution.java25.aot_command_line;

/**
 * Explains Java 25 Ahead-of-Time (AOT) Command-Line Ergonomics, introduced by
 * JEP 514.
 *
 * <p>
 * This is an explanatory learning module because the feature is an operational
 * JVM startup workflow. A faithful demonstration requires launching an
 * application, creating an AOT cache, launching again with that cache, and
 * measuring startup behavior.
 * </p>
 */
public class AotCommandLineErgonomicsNotes {

	/**
	 * Explains the problem that AOT workflows address.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "Java startup can spend time discovering, loading, and linking classes before application code is ready";
	}

	/**
	 * Explains the Java 25 improvement.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "Java 25 simplifies common AOT cache creation with the -XX:AOTCacheOutput command-line option";
	}

	/**
	 * Explains how an AOT cache is used after creation.
	 *
	 * @return a short workflow explanation
	 */
	public String productionWorkflow() {
		return "after creating an AOT cache, the production run can start with -XX:AOTCache to reuse ahead-of-time data";
	}

	/**
	 * Explains why this repository documents the feature without trying to test
	 * startup performance.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository keeps AOT command-line ergonomics as explanatory notes because it is a JVM launch and measurement workflow";
	}
}
