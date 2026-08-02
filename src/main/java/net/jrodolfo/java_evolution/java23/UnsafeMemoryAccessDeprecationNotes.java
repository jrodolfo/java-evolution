package net.jrodolfo.java_evolution.java23;

/**
 * Explains the Java 23 deprecation of memory-access methods in
 * {@code sun.misc.Unsafe}.
 *
 * <p>
 * {@code sun.misc.Unsafe} gave libraries access to unsupported low-level memory
 * operations. Those calls are fragile because they depend on JDK internals.
 * Java 23 continued the migration pressure toward supported APIs that have
 * clearer compatibility contracts.
 * </p>
 */
public class UnsafeMemoryAccessDeprecationNotes {

	/**
	 * Explains the migration goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "move code away from unsupported unsafe memory-access methods";
	}

	/**
	 * Names the supported direction for replacement APIs.
	 *
	 * @return a short recommendation
	 */
	public String replacementDirection() {
		return "prefer supported APIs such as VarHandle or the Foreign Function and Memory API";
	}
}
