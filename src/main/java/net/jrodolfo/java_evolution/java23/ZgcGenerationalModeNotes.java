package net.jrodolfo.java_evolution.java23;

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
	 * Explains the runtime change.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "make ZGC use generational mode by default";
	}

	/**
	 * Names the expected practical benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "improve garbage collection efficiency for many workloads";
	}
}
