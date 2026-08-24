package net.jrodolfo.java_evolution.java24.aot_class_loading;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AotClassLoadingExamplesTest {

	private final AotClassLoadingExamples examples = new AotClassLoadingExamples();

	@Test
	void childProcessesRecordCreateAndUseAnAotCache(@TempDir Path workspace) throws Exception {
		AotClassLoadingExamples.AotClassLoadingWorkflowResult result =
				examples.runAotClassLoadingWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the example should compile the small application before packaging it")
				.isZero();
		assertThat(result.packaging().exitCode())
				.as("the example should package the compiled application as a JAR")
				.isZero();
		assertThat(Files.exists(result.jarFile()))
				.as("the AOT workflow should use a real classpath JAR")
				.isTrue();

		assertThat(result.configurationRecording().exitCode())
				.as("the training run should record AOT configuration successfully")
				.isZero();
		assertThat(result.configurationRecording().output())
				.as("the training run should still execute the application")
				.contains("aot-class-loading-probe=record:hello-class-loading")
				.contains("AOTConfiguration recorded");
		assertThat(Files.exists(result.configurationFile()))
				.as("the training run should create a real AOT configuration file")
				.isTrue();
		assertThat(result.configurationSize())
				.as("the generated AOT configuration should contain runtime data")
				.isPositive();

		assertThat(result.cacheCreation().exitCode())
				.as("the create step should build an AOT cache from the recorded configuration")
				.isZero();
		assertThat(result.cacheCreation().output())
				.as("the create step should read configuration and write a cache")
				.contains("Opened AOT configuration file")
				.contains("AOTCache creation is complete");
		assertThat(Files.exists(result.cacheFile()))
				.as("the create step should produce a real AOT cache file")
				.isTrue();
		assertThat(result.cacheSize())
				.as("the generated AOT cache should contain cached class-loading data")
				.isPositive();

		assertThat(result.cachedRun().exitCode())
				.as("the final application run should require the generated cache")
				.isZero();
		assertThat(result.cachedRun().output())
				.as("the final run should open the cache and still execute application code")
				.contains("Opened AOT cache")
				.contains("Using AOT-linked classes: true")
				.contains("aot-class-loading-probe=run:hello-class-loading");
	}

	@Test
	void probeSourceLoadsMoreThanOneTrivialClass() {
		assertThat(examples.probeSource())
				.as("the probe should use ordinary Java APIs so the AOT recording has class-loading work to observe")
				.contains("java.util.List")
				.contains("java.util.stream.Collectors")
				.contains("AotClassLoadingProbe");
	}

	@Test
	void exampleExplainsStartupOrientedRuntimeWork() {
		assertThat(examples.purpose())
				.as("AOT class loading is a startup/runtime topic, not a language syntax feature")
				.contains("startup");
		assertThat(examples.classLoading())
				.as("The example should define class loading before explaining the optimization")
				.contains("class data")
				.contains("JVM");
		assertThat(examples.classLinking())
				.as("The example should define class linking before explaining the optimization")
				.contains("prepares")
				.contains("JVM");
		assertThat(examples.java24Workflow())
				.as("The example should teach the explicit Java 24 record/create/use workflow")
				.contains("records AOT configuration")
				.contains("creates an AOT cache")
				.contains("runs the application");
		assertThat(examples.testBoundary())
				.as("The example should avoid pretending to benchmark startup performance")
				.contains("cache workflow")
				.contains("not startup performance");
	}
}
