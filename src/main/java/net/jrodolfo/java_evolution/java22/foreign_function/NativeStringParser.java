package net.jrodolfo.java_evolution.java22.foreign_function;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

/**
 * Demonstrates a Java 22 foreign function call to the native {@code atoi}
 * function.
 *
 * <p>
 * The C {@code atoi} function parses an integer from a null-terminated C
 * string. Java creates that native string in a confined {@link Arena}, links the
 * native function with a {@link FunctionDescriptor}, and invokes it through a
 * {@link MethodHandle}.
 * </p>
 */
public class NativeStringParser {

	private static final String FUNCTION_NAME = "atoi";

	/**
	 * Parses an integer by calling native {@code atoi}.
	 *
	 * @param text the Java text copied into native memory
	 * @return the native call result
	 * @throws Throwable if the native function cannot be linked or invoked
	 */
	public ForeignFunctionCallResult parseInteger(String text) throws Throwable {
		try (var arena = Arena.ofConfined()) {
			var cString = arena.allocateFrom(text);
			int value = (int) atoiHandle().invokeExact(cString);

			return new ForeignFunctionCallResult(
					FUNCTION_NAME,
					text,
					value,
					Integer.class.getSimpleName());
		}
	}

	private MethodHandle atoiHandle() {
		var descriptor = FunctionDescriptor.of(JAVA_INT, ADDRESS);
		return Linker.nativeLinker().downcallHandle(nativeFunction(), descriptor);
	}

	private MemorySegment nativeFunction() {
		return Linker.nativeLinker()
				.defaultLookup()
				.find(FUNCTION_NAME)
				.orElseThrow(() -> new IllegalStateException("native function not found: " + FUNCTION_NAME));
	}
}
