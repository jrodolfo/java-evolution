package net.jrodolfo.java_evolution.java15;

/**
 * Explains hidden classes, introduced in Java 15.
 *
 * <p>
 * Before hidden classes, framework-generated implementation classes were more
 * discoverable and name-addressable than they often needed to be. That was a
 * poor fit for runtime-generated implementation details used by proxies,
 * expression engines, and language runtimes.
 * </p>
 *
 * <p>
 * Hidden classes solve this by supporting dynamically generated classes that
 * are not intended to be found by normal name lookup. A full demonstration
 * usually requires bytecode generation, so this repository keeps the example
 * explanatory.
 * </p>
 */
public class HiddenClassesNotes {

	/**
	 * Describes the purpose of hidden classes.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "hidden classes support framework-generated implementation classes that are not discoverable by name";
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
	 * Explains why this repository does not generate bytecode for the example.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "this project documents hidden classes without bytecode generation to keep the examples lightweight";
	}
}
