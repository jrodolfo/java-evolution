package net.jrodolfo.java_evolution.java20;

/**
 * Explains scoped values, introduced as an incubating API in Java 20.
 */
public class ScopedValuesIncubatorNotes {

	public String purpose() {
		return "scoped values share immutable data within and across threads";
	}

	public String relationToThreadLocal() {
		return "scoped values are intended as a safer alternative to many ThreadLocal use cases";
	}

	public String projectDecision() {
		return "the Java 20 API was incubating, so this repository documents the concept without enabling incubator modules";
	}
}
