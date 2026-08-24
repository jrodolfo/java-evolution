/**
 * Executable migration-boundary example for Java 23 deprecation of
 * {@code sun.misc.Unsafe} memory-access methods for removal.
 *
 * <p>
 * The example captures compiler diagnostics and runtime denial behavior from a
 * generated child source file, then points learners toward supported replacement
 * APIs such as {@code VarHandle}.
 * </p>
 */
package net.jrodolfo.java_evolution.java23.unsafe_memory_access_deprecation;
