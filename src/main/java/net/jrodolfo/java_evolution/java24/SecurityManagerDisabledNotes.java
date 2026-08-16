package net.jrodolfo.java_evolution.java24;

/**
 * Explains the Security Manager being permanently disabled in Java 24.
 *
 * <p>
 * The Security Manager was once used as an in-process sandbox, but that model
 * became less effective and increasingly difficult to maintain. Its goal was
 * to let code running inside the same JVM be restricted by permissions, such
 * as whether it could read files, open sockets, or exit the VM. Java 24
 * permanently disabled this mechanism, reinforcing that isolation belongs at
 * the operating system, container, process, and deployment boundary.
 * </p>
 */
public class SecurityManagerDisabledNotes {
	/**
	 * Describes the old security model.
	 *
	 * @return a short explanation of the old model
	 */
	public String oldModel() {
		return "the Security Manager tried to sandbox code inside the same JVM with permission checks";
	}

	/**
	 * Explains the direct impact of Java 24.
	 *
	 * @return a short impact note
	 */
	public String impact() {
		return "the Security Manager is permanently disabled and can no longer be used as an application sandbox";
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
