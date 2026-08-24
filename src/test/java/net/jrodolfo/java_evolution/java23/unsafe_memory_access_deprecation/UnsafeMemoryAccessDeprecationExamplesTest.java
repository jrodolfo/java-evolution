package net.jrodolfo.java_evolution.java23.unsafe_memory_access_deprecation;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class UnsafeMemoryAccessDeprecationExamplesTest {

	private final UnsafeMemoryAccessDeprecationExamples examples = new UnsafeMemoryAccessDeprecationExamples();

	@TempDir
	private Path temporaryDirectory;

	@Test
	void generatedUnsafeProbeIsKeptOutsideTheMainSourceTree() throws Exception {
		Path sourceFile = examples.createUnsafeMemoryAccessProbe(temporaryDirectory);

		assertThat(sourceFile)
				.as("The negative Unsafe example should be generated as child source, not compiled into main code")
				.exists()
				.hasFileName("UnsafeMemoryAccessProbe.java");
		assertThat(Files.readString(sourceFile))
				.as("The generated source intentionally contains the API being deprecated")
				.contains("sun.misc.Unsafe")
				.contains("objectFieldOffset")
				.contains("getInt");
	}

	@Test
	void compilerReportsUnsafeMemoryAccessAsDeprecatedForRemoval() throws Exception {
		UnsafeMemoryAccessDeprecationExamples.ProcessResult result = examples
				.compileUnsafeMemoryAccessProbe(temporaryDirectory);

		assertThat(result.exitCode())
				.as("The unsafe child source should still compile so migration diagnostics can be observed")
				.isZero();
		assertThat(result.output())
				.as("javac should warn that Unsafe is unsupported internal API")
				.contains("Unsafe is internal proprietary API")
				.contains("may be removed in a future release");
		assertThat(result.output())
				.as("Java 23 deprecated Unsafe memory-access methods for removal")
				.contains("objectFieldOffset")
				.contains("getInt")
				.contains("deprecated and marked for removal");
	}

	@Test
	void denyModeTurnsUnsafeMemoryAccessIntoRuntimeFailure() throws Exception {
		UnsafeMemoryAccessDeprecationExamples.ProcessResult result = examples
				.runUnsafeProbeWithDeny(temporaryDirectory);

		assertThat(result.exitCode())
				.as("--sun-misc-unsafe-memory-access=deny should reject terminally deprecated memory access")
				.isNotZero();
		assertThat(result.output())
				.as("The runtime failure should name the denied Unsafe operation")
				.contains("UnsupportedOperationException")
				.contains("objectFieldOffset");
	}

	@Test
	void varHandleDemonstratesSupportedReplacementDirection() throws Exception {
		assertThat(examples.updateWithVarHandle(42))
				.as("VarHandle should support controlled variable access without sun.misc.Unsafe")
				.isEqualTo(42);
	}

	@Test
	void exampleExplainsNegativeBoundaryAndMigrationLesson() {
		assertThat(examples.exampleBoundary())
				.as("The example should not normalize new Unsafe usage")
				.contains("generated child source")
				.contains("migration diagnostics")
				.contains("without teaching new Unsafe usage");
		assertThat(examples.migrationLesson())
				.as("The migration lesson should point to supported replacement APIs")
				.contains("deprecated-for-removal")
				.contains("Unsafe memory access")
				.contains("VarHandle");
	}
}
