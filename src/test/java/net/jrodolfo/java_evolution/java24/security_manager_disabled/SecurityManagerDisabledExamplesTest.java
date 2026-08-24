package net.jrodolfo.java_evolution.java24.security_manager_disabled;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SecurityManagerDisabledExamplesTest {

	private final SecurityManagerDisabledExamples examples = new SecurityManagerDisabledExamples();

	@Test
	void childJvmShowsThatSettingASecurityManagerIsNotSupported(@TempDir Path workspace) throws Exception {
		SecurityManagerDisabledExamples.CommandResult result = examples.runSecurityManagerProbe(workspace);

		assertThat(result.exitCode())
				.as("the probe catches the runtime failure so the test can inspect the output")
				.isZero();
		assertThat(result.output())
				.as("the child JVM should start without an installed Security Manager")
				.contains("before=null");
		assertThat(result.output())
				.as("Java 24 permanently disabled installing a Security Manager")
				.contains("thrown=java.lang.UnsupportedOperationException")
				.contains("Setting a Security Manager is not supported");
	}

	@Test
	void childJvmCompilationShowsDeprecationForRemovalWarnings(@TempDir Path workspace) throws Exception {
		SecurityManagerDisabledExamples.CommandResult result = examples.runSecurityManagerProbe(workspace);

		assertThat(result.output())
				.as("source-launching the probe should expose the deprecation-for-removal migration signal")
				.contains("deprecated and marked for removal")
				.contains("SecurityManager")
				.contains("setSecurityManager");
	}

	@Test
	void probeSourceKeepsObsoleteApiUsageInsideTheChildProcess() {
		assertThat(examples.probeSource())
				.as("the project should demonstrate the obsolete call only in generated child-JVM source")
				.contains("System.setSecurityManager(new SecurityManager())")
				.contains("catch (Throwable throwable)");
	}

	@Test
	void exampleExplainsTheOldModelAndModernIsolationDirection() {
		assertThat(examples.oldModel())
				.as("the old feature was an in-process sandbox for less-trusted code")
				.contains("same JVM")
				.contains("permission checks");
		assertThat(examples.permissionExamples())
				.as("the example should name sensitive actions that historically triggered checks")
				.contains("file access")
				.contains("network access")
				.contains("permissions");
		assertThat(examples.java24Impact())
				.as("the Java 24 impact should be described as permanent disablement")
				.contains("Java 24")
				.contains("permanently disabled")
				.contains("sandbox");
		assertThat(examples.modernIsolationAdvice())
				.as("modern isolation belongs outside the JVM process")
				.contains("operating system")
				.contains("container")
				.contains("process")
				.contains("deployment");
	}
}
