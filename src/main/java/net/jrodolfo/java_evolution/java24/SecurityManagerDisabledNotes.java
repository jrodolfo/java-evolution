package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Security Manager being permanently disabled in Java 24.
 */
public class SecurityManagerDisabledNotes {
	public String impact() {
		return "the Security Manager can no longer be used as an application sandbox";
	}

	public String migrationAdvice() {
		return "use operating system, container, and deployment-level isolation instead";
	}
}
