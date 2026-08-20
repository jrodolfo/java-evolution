package net.jrodolfo.java_evolution.java04.assertions;

/**
 * Explains assertions, introduced in J2SE 1.4.
 */
public class AssertionNotes {

	public String problemSolved() {
		return "developers need a lightweight way to document and check internal assumptions";
	}

	public String enablement() {
		return "assert statements run only when the JVM is launched with -ea or -enableassertions";
	}

	public String appropriateUse() {
		return "assertions are for internal invariants, not user input validation or public API contracts";
	}
}
