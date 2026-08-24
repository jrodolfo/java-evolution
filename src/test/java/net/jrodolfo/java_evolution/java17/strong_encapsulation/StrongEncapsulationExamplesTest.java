package net.jrodolfo.java_evolution.java17.strong_encapsulation;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StrongEncapsulationExamplesTest {

	private final StrongEncapsulationExamples examples = new StrongEncapsulationExamples();

	@Test
	void probeSourceShowsTheIntentionalDeepReflectionAttempt() {
		assertThat(examples.probeSource())
				.as("The probe should make the reflective access attempt visible to learners")
				.contains("String.class.getDeclaredFields()")
				.contains("setAccessible(true)");
	}

	@Test
	void deepReflectionIntoJavaLangFailsWithoutAddOpens(@TempDir Path workspace) throws Exception {
		StrongEncapsulationExamples.CommandResult result = examples.runWithoutOpens(workspace);

		assertThat(result.exitCode())
				.as("Strong encapsulation should reject deep reflection into java.lang without an opens edge")
				.isNotZero();
		assertThat(result.output())
				.as("The child JVM should report that java.base/java.lang is not open")
				.contains("InaccessibleObjectException")
				.contains("does not \"opens java.lang\"");
	}

	@Test
	void targetedAddOpensAllowsDeepReflectionForMigration(@TempDir Path workspace) throws Exception {
		StrongEncapsulationExamples.CommandResult result = examples.runWithAddOpens(workspace);

		assertThat(result.exitCode())
				.as("--add-opens should create a targeted reflective access bridge for the child JVM")
				.isZero();
		assertThat(result.output())
				.as("The probe should reach setAccessible(true) only when java.lang is opened")
				.contains("accessible=true");
	}

	@Test
	void addExportsDoesNotAllowDeepReflection(@TempDir Path workspace) throws Exception {
		StrongEncapsulationExamples.CommandResult result = examples.runWithAddExportsOnly(workspace);

		assertThat(result.exitCode())
				.as("--add-exports is not the same as opening a package for deep reflection")
				.isNotZero();
		assertThat(result.output())
				.as("The child JVM should still reject reflective access when only exports are added")
				.contains("InaccessibleObjectException")
				.contains("does not \"opens java.lang\"");
	}

	@Test
	void illegalAccessPermitNoLongerRestoresRelaxedAccess(@TempDir Path workspace) throws Exception {
		StrongEncapsulationExamples.CommandResult result = examples.runWithIllegalAccessPermit(workspace);

		assertThat(result.exitCode())
				.as("--illegal-access=permit should not restore the pre-Java-17 relaxed behavior")
				.isNotZero();
		assertThat(result.output())
				.as("Modern JDKs should ignore the obsolete broad illegal-access escape hatch")
				.contains("--illegal-access=permit")
				.contains("InaccessibleObjectException");
	}
}
