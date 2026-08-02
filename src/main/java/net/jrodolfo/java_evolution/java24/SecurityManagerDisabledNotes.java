package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Security Manager being permanently disabled in Java 24.
 *
 * <p>
 * The Security Manager was once used as an in-process sandbox, but that model
 * became less effective and increasingly difficult to maintain. Java 24
 * permanently disabled it, reinforcing that isolation belongs at the operating
 * system, container, process, and deployment boundary.
 * </p>
 */
public class SecurityManagerDisabledNotes {
	/**
	 * Explains the direct impact.
	 *
	 * @return a short impact note
	 */
	public String impact() {
		return "the Security Manager can no longer be used as an application sandbox";
	}

	/**
	 * Gives the preferred migration direction.
	 *
	 * @return a short recommendation
	 */
	public String migrationAdvice() {
		return "use operating system, container, and deployment-level isolation instead";
	}
}
