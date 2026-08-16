package net.jrodolfo.java_evolution.java24.security_manager_disabled;

/**
 * Explains the Security Manager being permanently disabled in Java 24.
 *
 * <p>
 * This is a runtime/platform change rather than a syntax feature. The
 * Security Manager was once used as an in-process sandbox: code running inside
 * the same JVM could be checked against permissions before reading files,
 * opening sockets, or exiting the virtual machine. Java 24 permanently
 * disabled that mechanism, making operating-system, container, process, and
 * deployment boundaries the practical isolation model.
 * </p>
 */
public class SecurityManagerDisabledNotes {
	/**
	 * Describes the original problem the Security Manager tried to solve.
	 *
	 * @return a short problem statement
	 */
	public String originalGoal() {
		return "restrict less-trusted code running inside the same JVM before it performed sensitive actions";
	}

	/**
	 * Describes the old security model.
	 *
	 * @return a short explanation of the old model
	 */
	public String oldModel() {
		return "the Security Manager tried to sandbox code inside the same JVM with permission checks";
	}

	/**
	 * Names examples of actions that historically needed permission checks.
	 *
	 * @return examples of sensitive actions
	 */
	public String permissionExamples() {
		return "file access, network access, reflection, and exiting the JVM could be checked by permissions";
	}

	/**
	 * Explains the direct impact of Java 24.
	 *
	 * @return a short impact note
	 */
	public String java24Impact() {
		return "the Security Manager is permanently disabled and can no longer be used as an application sandbox";
	}

	/**
	 * Gives the preferred migration direction.
	 *
	 * @return a short recommendation
	 */
	public String modernIsolationAdvice() {
		return "use operating system, container, process, and deployment-level isolation instead";
	}
}
