package net.jrodolfo.java_evolution.java23.zgc_generational_mode;

/**
 * Explains ZGC generational mode becoming the default in Java 23.
 *
 * <p>
 * Many objects die young. Generational garbage collection uses that observation
 * to manage young and old objects differently. Java 23 made generational mode
 * the default for ZGC to improve efficiency for many workloads.
 * </p>
 */
public class ZgcGenerationalModeNotes {

	/**
	 * Explains the purpose of garbage collection.
	 *
	 * @return a short explanation
	 */
	public String garbageCollectionGoal() {
		return "garbage collection reclaims memory from objects the application can no longer reach";
	}

	/**
	 * Explains the observation behind generational collectors.
	 *
	 * @return a short explanation
	 */
	public String generationalObservation() {
		return "many Java objects die young, so young and old objects can be managed differently";
	}

	/**
	 * Explains the Java 23 runtime change.
	 *
	 * @return a short explanation
	 */
	public String java23Change() {
		return "Java 23 makes ZGC use generational mode by default";
	}

	/**
	 * Names the expected practical benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "improve garbage collection efficiency for many workloads";
	}

	/**
	 * Explains why this repository keeps the feature as notes.
	 *
	 * @return a short project decision
	 */
	public String projectDecision() {
		return "this repository documents ZGC generational mode as notes because it is runtime behavior, not ordinary unit-test behavior";
	}
}
