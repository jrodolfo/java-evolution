package net.jrodolfo.java_evolution.java24;

/**
 * Explains ahead-of-time class loading and linking, introduced in Java 24.
 *
 * <p>
 * Java startup can include substantial class loading and linking work. This
 * feature moves some of that work ahead of application execution to improve
 * startup behavior for suitable deployments.
 * </p>
 */
public class AotClassLoadingNotes {
	/**
	 * Explains the runtime goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "improve startup by loading and linking classes ahead of application execution";
	}
}
