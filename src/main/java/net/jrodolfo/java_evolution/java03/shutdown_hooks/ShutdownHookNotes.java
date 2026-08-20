package net.jrodolfo.java_evolution.java03.shutdown_hooks;

/**
 * Explains JVM shutdown hooks.
 */
public class ShutdownHookNotes {

	public String problemSolved() {
		return "applications need a last chance to release resources during normal JVM shutdown";
	}

	public String apiShape() {
		return "Runtime.addShutdownHook registers a Thread to run during orderly shutdown";
	}

	public String repositoryDecision() {
		return "a faithful test would terminate a JVM process, so this repository keeps shutdown hooks explanatory";
	}
}
