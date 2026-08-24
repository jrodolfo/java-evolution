package net.jrodolfo.java_evolution.java25.aot_command_line;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AotCommandLineErgonomicsExamplesTest {

	private final AotCommandLineErgonomicsExamples examples = new AotCommandLineErgonomicsExamples();

	@Test
	void childJvmCreatesAndUsesARealAotCache(@TempDir Path workspace) throws Exception {
		AotCommandLineErgonomicsExamples.AotWorkflowResult result = examples.runAotWorkflow(workspace);

		assertThat(result.cacheCreation().exitCode())
				.as("creating the AOT cache should complete successfully")
				.isZero();
		assertThat(result.cacheCreation().output())
				.as("the training run should still execute the application code")
				.contains("aot-cache-probe=training")
				.contains("AOTCache creation is complete");
		assertThat(Files.exists(result.cacheFile()))
				.as("the -XX:AOTCacheOutput run should create a real cache file")
				.isTrue();
		assertThat(result.cacheSize())
				.as("the generated AOT cache should contain runtime data")
				.isPositive();

		assertThat(result.cacheUsage().exitCode())
				.as("running with the generated AOT cache should complete successfully")
				.isZero();
		assertThat(result.cacheUsage().output())
				.as("the production run should open the cache and still execute application code")
				.contains("Opened AOT cache")
				.contains("aot-cache-probe=production");
	}

	@Test
	void probeSourceShowsThatTheApplicationStillRunsNormally() {
		assertThat(examples.probeSource())
				.as("the generated source should be ordinary Java application code")
				.contains("public class AotCacheProbe")
				.contains("public static void main")
				.contains("aot-cache-probe=");
	}

	@Test
	void exampleExplainsStartupProblemAndWorkflowBoundary() {
		assertThat(examples.problem())
				.as("AOT ergonomics should start from the startup work being optimized")
				.contains("startup")
				.contains("loading")
				.contains("linking");
		assertThat(examples.java25Idea())
				.as("The example should name the Java 25 one-command cache creation option")
				.contains("AOT cache")
				.contains("-XX:AOTCacheOutput");
		assertThat(examples.productionWorkflow())
				.as("The example should show that production uses the created AOT cache")
				.contains("-XX:AOTCache")
				.contains("ahead-of-time data");
		assertThat(examples.testBoundary())
				.as("The example should avoid pretending to benchmark startup performance")
				.contains("command-line workflow")
				.contains("not startup performance");
	}
}
