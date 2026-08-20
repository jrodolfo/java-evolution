package net.jrodolfo.java_evolution.java06.monitoring_management;

/**
 * Explains Java 6 monitoring and management improvements.
 */
public class MonitoringManagementNotes {

	/**
	 * @return the operational problem
	 */
	public String problemSolved() {
		return "running JVM applications need standard visibility into memory, threads, class loading, logging, and runtime state";
	}

	/**
	 * @return important Java 6 monitoring terms
	 */
	public String importantTerms() {
		return "JMX, platform MXBeans, attach-on-demand diagnostics, JConsole, jps, and jstat";
	}

	/**
	 * @return why this is notes-only here
	 */
	public String repositoryDecision() {
		return "the meaningful demonstration is a running process observed by management tools, so notes are more faithful than a tiny pure method";
	}
}
