package net.jrodolfo.java_evolution.java22.foreign_function;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

/**
 * Demonstrates a Java 22 foreign function call to the native {@code strlen}
 * function.
 *
 * <p>
 * The example copies a Java string into native memory, passes the memory address
 * to C {@code strlen}, and receives the string length back as a Java
 * {@code long}. This shows the foreign-function side of the Foreign Function
 * and Memory API without requiring a custom native library.
 *
 * <p>
 * The example uses {@code JAVA_LONG} for C {@code size_t}, which matches the
 * 64-bit platforms targeted by this repository. The native linker provides
 * canonical layouts for platform-dependent C types when code must support
 * other data models.
 * </p>
 */
public class NativeStringLength {

	private static final String FUNCTION_NAME = "strlen";

	/**
	 * Calls native {@code strlen} for the supplied text.
	 *
	 * @param text the Java text copied into native memory
	 * @return the native call result
	 * @throws Throwable if the native function cannot be linked or invoked
	 */
	public ForeignFunctionCallResult lengthOf(String text) throws Throwable {
		try (var arena = Arena.ofConfined()) {
			var cString = arena.allocateFrom(text);
			// invokeExact is signature-sensitive: this cast must match the
			// FunctionDescriptor return type, otherwise the call site does not match.
			long length = (long) strlenHandle().invokeExact(cString);

			return new ForeignFunctionCallResult(
					FUNCTION_NAME,
					text,
					length,
					Long.class.getSimpleName());
		}
	}

	private MethodHandle strlenHandle() {
		var descriptor = FunctionDescriptor.of(JAVA_LONG, ADDRESS);
		return Linker.nativeLinker().downcallHandle(nativeFunction(), descriptor);
	}

	private MemorySegment nativeFunction() {
		return Linker.nativeLinker()
				.defaultLookup()
				.find(FUNCTION_NAME)
				.orElseThrow(() -> new IllegalStateException("native function not found: " + FUNCTION_NAME));
	}
}
