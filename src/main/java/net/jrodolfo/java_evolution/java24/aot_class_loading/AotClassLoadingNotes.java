package net.jrodolfo.java_evolution.java24.aot_class_loading;

/**
 * Explains ahead-of-time class loading and linking, introduced in Java 24.
 *
 * <p>
 * Java startup can include substantial class loading and linking work. This
 * feature moves some of that work ahead of application execution to improve
 * startup behavior for suitable deployments.
 * </p>
 *
 * <p>
 * This is a runtime/deployment feature, not a source-code syntax feature. The
 * useful mental model is that work normally done while the application starts
 * can be prepared earlier, so startup has less class-loading and linking work
 * left to do.
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

	/**
	 * Explains class loading in learner-friendly terms.
	 *
	 * @return a short class-loading explanation
	 */
	public String classLoading() {
		return "class loading finds class data and brings it into the JVM";
	}

	/**
	 * Explains class linking in learner-friendly terms.
	 *
	 * @return a short class-linking explanation
	 */
	public String classLinking() {
		return "class linking prepares a loaded class so the JVM can use it safely";
	}

	/**
	 * Explains the startup cost this feature targets.
	 *
	 * @return a short before explanation
	 */
	public String before() {
		return "application startup normally includes class loading and linking work";
	}

	/**
	 * Explains what ahead-of-time preparation changes.
	 *
	 * @return a short after explanation
	 */
	public String after() {
		return "some class loading and linking work can be prepared before the application run";
	}

	/**
	 * Explains why this repository keeps the feature as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this repository documents AOT class loading as notes because it is a runtime startup feature, not ordinary unit-test behavior";
	}
}
