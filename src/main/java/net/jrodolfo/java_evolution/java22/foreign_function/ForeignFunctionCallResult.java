package net.jrodolfo.java_evolution.java22.foreign_function;

/**
 * Captures the observable result of a small native function call.
 *
 * <p>
 * The record keeps the examples easy to test without printing from the example
 * classes. The Java type is included because foreign calls cross a boundary
 * where C types and Java types must be mapped deliberately.
 * </p>
 *
 * @param functionName the native function that was called
 * @param input the Java input copied into native memory
 * @param value the value returned to Java
 * @param javaType the Java type used for the returned value
 */
public record ForeignFunctionCallResult(
		String functionName,
		String input,
		Number value,
		String javaType) {
}
