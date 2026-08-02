package net.jrodolfo.java_evolution.java23;

/**
 * Explains the Java 23 deprecation of memory-access methods in
 * {@code sun.misc.Unsafe}.
 */
public class UnsafeMemoryAccessDeprecationNotes {

	public String purpose() {
		return "move code away from unsupported unsafe memory-access methods";
	}

	public String replacementDirection() {
		return "prefer supported APIs such as VarHandle or the Foreign Function and Memory API";
	}
}
