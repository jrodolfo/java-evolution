package net.jrodolfo.java_evolution.java23.unsafe_memory_access_deprecation;

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
	 * Explains why libraries historically used {@code sun.misc.Unsafe}.
	 *
	 * @return a short explanation of the old use case
	 */
	public String oldUseCase() {
		return "advanced libraries used sun.misc.Unsafe for unsupported low-level memory access";
	}

	/**
	 * Explains the migration goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "move code away from unsupported unsafe memory-access methods";
	}

	/**
	 * Explains why depending on {@code sun.misc.Unsafe} is risky.
	 *
	 * @return a short risk explanation
	 */
	public String risk() {
		return "unsafe memory access depends on JDK internals with weaker compatibility guarantees";
	}

	/**
	 * Explains what deprecated for removal means.
	 *
	 * @return a short deprecation explanation
	 */
	public String deprecationMeaning() {
		return "deprecated for removal means existing code should migrate because the API may be removed later";
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
