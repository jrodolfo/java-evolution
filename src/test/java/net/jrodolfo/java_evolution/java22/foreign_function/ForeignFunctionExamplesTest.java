package net.jrodolfo.java_evolution.java22.foreign_function;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests the Java 22 Foreign Function and Memory API examples.
 *
 * <p>
 * These tests use native symbols discovered through the platform linker's
 * default lookup. The examples call {@code atoi} and {@code strlen}, standard C
 * library functions exposed by the validated macOS, Linux, and Windows
 * environments used for this project. Symbol lookup is still platform and
 * runtime dependent, which is one reason the examples stay intentionally small.
 * </p>
 */
class ForeignFunctionExamplesTest {

	@Test
	void atoiParsesANumberFromNativeMemory() throws Throwable {
		var parser = new NativeStringParser();

		var result = parser.parseInteger("25");

		assertThat(result.functionName())
				.as("the example links the native atoi function")
				.isEqualTo("atoi");
		assertThat(result.input())
				.as("the Java input is copied into native memory before the call")
				.isEqualTo("25");
		assertThat(result.value())
				.as("the native int result comes back to Java")
				.isEqualTo(25);
		assertThat(result.javaType())
				.as("C int is mapped to Java int for atoi")
				.isEqualTo("Integer");
	}

	@Test
	void strlenReturnsTheLengthOfANativeCString() throws Throwable {
		var length = new NativeStringLength();

		var result = length.lengthOf("java");

		assertThat(result.functionName())
				.as("the example links the native strlen function")
				.isEqualTo("strlen");
		assertThat(result.input())
				.as("the Java string is allocated as a null-terminated native string")
				.isEqualTo("java");
		assertThat(result.value())
				.as("the native size_t result is represented as a Java long in this example")
				.isEqualTo(4L);
		assertThat(result.javaType())
				.as("strlen returns a native size value mapped to Java long here")
				.isEqualTo("Long");
	}
}
