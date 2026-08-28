package net.jrodolfo.java_evolution.java26.final_field_restrictions;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FinalFieldRestrictionsExamplesTest {

	private final FinalFieldRestrictionsExamples examples = new FinalFieldRestrictionsExamples();

	@Test
	void childJvmShowsDefaultFinalFieldMutationWarning(@TempDir Path workspace) throws Exception {
		FinalFieldRestrictionsExamples.MutationProbeResult result = examples.runDefaultMutationProbe(workspace);

		assertThat(Files.exists(result.sourceFile()))
				.as("The example should write the reflective mutation source into a temporary workspace")
				.isTrue();
		assertThat(result.compilation().exitCode())
				.as("The generated source should compile with JDK 26")
				.isZero();
		assertThat(result.execution().exitCode())
				.as("Java 26 warns by default but still allows the mutation during the migration period")
				.isZero();
		assertThat(result.execution().output())
				.as("The child JVM output should show the mutation and the Java 26 warning")
				.contains("Final field owner")
				.contains("mutated reflectively")
				.contains("--enable-final-field-mutation=ALL-UNNAMED")
				.contains("blocked in a future release")
				.contains("owner=after");
	}

	@Test
	void explicitEnablementSuppressesTheRuntimeWarning(@TempDir Path workspace) throws Exception {
		FinalFieldRestrictionsExamples.MutationProbeResult result = examples.runExplicitlyEnabledMutationProbe(workspace);

		assertThat(result.compilation().exitCode())
				.as("The generated source should compile before the explicitly enabled run")
				.isZero();
		assertThat(result.execution().exitCode())
				.as("Explicit enablement keeps legacy final-field mutation running")
				.isZero();
		assertThat(result.execution().output())
				.as("The explicitly enabled run should show the mutation without the default warning")
				.contains("owner=after")
				.doesNotContain("mutated reflectively");
	}

	@Test
	void generatedSourceUsesDeepReflectionAgainstAFinalField() {
		assertThat(examples.reflectiveMutationSource())
				.as("The generated source should make the reflective final-field mutation explicit")
				.contains("final String owner")
				.contains("getDeclaredField(\"owner\")")
				.contains("setAccessible(true)")
				.contains("field.set(account, \"after\")");
	}

	@Test
	void notesExplainFinalFieldIntegrityMigration() {
		assertThat(examples.problem())
				.as("Final-field restrictions should be tied to immutability and JVM trust")
				.contains("deep reflection")
				.contains("immutability")
				.contains("JVM");
		assertThat(examples.java26Behavior())
				.as("Java 26 behavior should be warnings, not immediate failure")
				.contains("Java 26")
				.contains("warnings");
		assertThat(examples.migrationDirection())
				.as("Migration guidance should describe selective legacy enablement")
				.contains("avoid")
				.contains("legacy frameworks");
	}
}
