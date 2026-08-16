package net.jrodolfo.java_evolution.java15.hidden_classes;

/**
 * Explains hidden classes, introduced in Java 15 by JEP 371.
 *
 * <p>
 * Hidden classes are aimed mainly at frameworks, language runtimes, expression
 * engines, and proxy libraries that generate implementation classes at runtime.
 * This repository keeps the topic explanatory because a faithful demonstration
 * usually requires generating bytecode and defining it through
 * {@code MethodHandles.Lookup.defineHiddenClass}.
 * </p>
 */
public class HiddenClassesNotes {

	/**
	 * Explains the pre-Java-15 problem.
	 *
	 * @return a short explanation
	 */
	public String generatedClassProblem() {
		return "runtime-generated implementation classes were often discoverable by name even when application code should not use them directly";
	}

	/**
	 * Describes the purpose of hidden classes.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "hidden classes support framework-generated implementation classes that are not discoverable by normal name lookup";
	}

	/**
	 * Names the primary API used to define hidden classes.
	 *
	 * @return the API name
	 */
	public String primaryApi() {
		return "MethodHandles.Lookup.defineHiddenClass";
	}

	/**
	 * Describes a realistic user of hidden classes.
	 *
	 * @return a short use-case explanation
	 */
	public String realisticUseCase() {
		return "a framework can generate implementation classes for proxies, lambdas, expression engines, or dynamic language runtimes";
	}

	/**
	 * Explains why this repository does not generate bytecode for the example.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this project documents hidden classes without bytecode generation because bytecode generation would distract from the Java 15 concept";
	}
}
