package net.jrodolfo.java_evolution.java09.module_system;

/**
 * Explains the Java Platform Module System (JPMS), introduced in Java 9 by
 * JEP 261.
 *
 * <p>
 * This is an explanatory learning module because a faithful module-system
 * example is naturally a multi-module source layout with {@code module-info.java}
 * files and module-path commands. This Spring Boot reference project remains
 * non-modular so the rest of the examples stay simple to compile and run.
 * </p>
 */
public class ModuleSystemNotes {

	/**
	 * Explains the classpath problem JPMS was designed to address.
	 *
	 * @return a short explanation
	 */
	public String classpathProblem() {
		return "the classpath could not declare strong boundaries, so code could accidentally depend on internal packages";
	}

	/**
	 * Explains the role of {@code module-info.java}.
	 *
	 * @return a short explanation
	 */
	public String moduleDescriptorPurpose() {
		return "module-info.java declares the module name, the modules it requires, and the packages it exports";
	}

	/**
	 * Shows the shape of a minimal module declaration.
	 *
	 * @return a compact {@code module-info.java} example
	 */
	public String minimalModuleDeclaration() {
		return "module net.jrodolfo.java_evolution.examples {\n"
				+ "    requires java.net.http;\n"
				+ "    exports net.jrodolfo.java_evolution.examples;\n"
				+ "}";
	}

	/**
	 * Explains the {@code requires} directive.
	 *
	 * @return a short explanation of {@code requires}
	 */
	public String requiresDirective() {
		return "requires declares another module that this module depends on to compile and run";
	}

	/**
	 * Explains the {@code exports} directive.
	 *
	 * @return a short explanation of {@code exports}
	 */
	public String exportsDirective() {
		return "exports makes one package's public types available to other modules";
	}

	/**
	 * Contrasts classpath and module path.
	 *
	 * @return a short explanation
	 */
	public String modulePathVsClasspath() {
		return "the classpath is a flat list of classes and JARs, while the module path lets the JVM resolve named modules and their declared dependencies";
	}

	/**
	 * Explains why this project is not modularized at this stage.
	 *
	 * @return the project decision about modules
	 */
	public String projectDecision() {
		return "this repository explains JPMS without converting the Spring Boot app into a modular application";
	}
}
