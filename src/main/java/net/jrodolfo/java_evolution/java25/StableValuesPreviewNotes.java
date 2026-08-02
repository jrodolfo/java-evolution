package net.jrodolfo.java_evolution.java25;

/**
 * Explains stable values as a Java 25 preview feature.
 *
 * <p>
 * Some values are initialized lazily but then never change. Stable values model
 * that "initialized at most once" pattern so the JVM can optimize stable data
 * safely.
 * </p>
 */
public class StableValuesPreviewNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "model values initialized at most once so the JVM can optimize stable data safely";
	}
}
