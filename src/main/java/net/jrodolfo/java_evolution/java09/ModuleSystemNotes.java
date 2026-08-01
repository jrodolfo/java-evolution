package net.jrodolfo.java_evolution.java09;

/**
 * Explains the Java Platform Module System, introduced in Java 9.
 *
 * <p>
 * The module system is Java 9's largest feature. A real modular application
 * normally contains a {@code module-info.java} file at the root of a source
 * set. This Spring Boot reference project intentionally does not become a
 * modular application yet, because the goal is to keep each feature example
 * small and independent.
 * </p>
 */
public class ModuleSystemNotes {

	/**
	 * Shows the shape of a minimal module declaration.
	 *
	 * @return a compact {@code module-info.java} example
	 */
	public String minimalModuleDeclaration() {
		return "module net.jrodolfo.java_evolution {\n"
				+ "    exports net.jrodolfo.java_evolution.java09;\n"
				+ "}";
	}

	/**
	 * Explains the {@code requires} directive.
	 *
	 * @return a short explanation of {@code requires}
	 */
	public String requiresDirective() {
		return "requires declares another module needed to compile and run this module";
	}

	/**
	 * Explains the {@code exports} directive.
	 *
	 * @return a short explanation of {@code exports}
	 */
	public String exportsDirective() {
		return "exports makes a package available to other modules";
	}

	/**
	 * Explains why this project is not modularized at this stage.
	 *
	 * @return the project decision about modules
	 */
	public String projectDecision() {
		return "this repository demonstrates module concepts without converting the Spring Boot app into a module";
	}
}
