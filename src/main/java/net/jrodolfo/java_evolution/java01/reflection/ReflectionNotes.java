package net.jrodolfo.java_evolution.java01.reflection;

/**
 * Explains reflection, introduced in Java 1.1.
 */
public class ReflectionNotes {

	public String problemSolved() {
		return "tools and frameworks need to inspect and use types that were not known at compile time";
	}

	public String coreApi() {
		return "java.lang.Class and java.lang.reflect expose constructors, methods, fields, and runtime type metadata";
	}

	public String tradeoff() {
		return "reflection enables runtime discovery but reduces compile-time checking and can weaken encapsulation";
	}
}
