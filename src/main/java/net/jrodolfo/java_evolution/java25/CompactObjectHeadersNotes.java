package net.jrodolfo.java_evolution.java25;

/**
 * Explains compact object headers in Java 25.
 *
 * <p>
 * Every Java object has header metadata. Reducing header size can improve memory
 * footprint, especially for applications with many objects.
 * </p>
 */
public class CompactObjectHeadersNotes {
	/**
	 * Explains the memory goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "reduce object header size to improve memory footprint";
	}
}
