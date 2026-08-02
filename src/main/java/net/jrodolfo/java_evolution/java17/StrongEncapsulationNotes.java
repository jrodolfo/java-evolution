package net.jrodolfo.java_evolution.java17;

/**
 * Explains strong encapsulation of JDK internals in Java 17.
 *
 * <p>
 * Java 17 strongly encapsulated internal JDK APIs by default. This matters for
 * libraries that previously depended on unsupported internal packages.
 * </p>
 */
public class StrongEncapsulationNotes {

	/**
	 * Explains the practical impact.
	 *
	 * @return a short explanation
	 */
	public String impact() {
		return "code should depend on supported public APIs instead of internal JDK packages";
	}

	/**
	 * Names the migration pressure this change created.
	 *
	 * @return a short migration note
	 */
	public String migrationAdvice() {
		return "replace reflective access to JDK internals with standard APIs or maintained libraries";
	}

	/**
	 * Explains why this feature is represented as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this is a runtime encapsulation change, so the repository documents the behavior instead of relying on illegal access";
	}
}
