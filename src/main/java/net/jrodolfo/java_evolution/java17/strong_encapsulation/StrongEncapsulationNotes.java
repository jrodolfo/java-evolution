package net.jrodolfo.java_evolution.java17.strong_encapsulation;

/**
 * Explains strong encapsulation of JDK internals in Java 17.
 *
 * <p>
 * Before Java 17, some applications and libraries reached into internal JDK
 * packages through reflection or unsupported APIs. An internal JDK API is an
 * implementation detail of the JDK, not a public contract promised to
 * application developers. That made upgrades fragile because those internals
 * could change or disappear between releases.
 * </p>
 *
 * <p>
 * Java 17 strongly encapsulated internal JDK APIs by default. This matters for
 * libraries that previously depended on unsupported internal packages. The
 * safer path is to use supported public APIs, which are documented for
 * application use and maintained as part of the Java platform contract.
 * </p>
 */
public class StrongEncapsulationNotes {

	/**
	 * Explains the old habit Java 17 made harder to depend on.
	 *
	 * @return a short before explanation
	 */
	public String before() {
		return "some code used reflection or unsupported packages to reach internal JDK implementation details";
	}

	/**
	 * Explains the safer direction encouraged by strong encapsulation.
	 *
	 * @return a short after explanation
	 */
	public String after() {
		return "code should use documented public APIs that are maintained as Java platform contracts";
	}

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
