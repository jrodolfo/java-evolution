package net.jrodolfo.java_evolution.java23;

/**
 * Explains flexible constructor bodies as a Java 23 second preview feature.
 *
 * <p>
 * Constructor code often needs to validate or prepare arguments before
 * delegating to another constructor. Older Java syntax forced an explicit
 * constructor invocation to appear first, so this validation had to be moved
 * elsewhere. Flexible constructor bodies relax that rule while preserving
 * initialization safety.
 * </p>
 */
public class FlexibleConstructorBodiesSecondPreviewNotes {

	/**
	 * Explains the constructor problem addressed by the feature.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "allow safe validation or field initialization before an explicit constructor invocation";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 23 and final in Java 25";
	}
}
