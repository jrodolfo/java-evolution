package net.jrodolfo.java_evolution.java22.launch_multi_file_source_programs;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LaunchMultiFileSourceProgramsExamplesTest {

	private final LaunchMultiFileSourceProgramsExamples examples = new LaunchMultiFileSourceProgramsExamples();

	@Test
	void launcherRunsMainJavaAndDiscoversHelperSourceFiles(@TempDir Path sourceDirectory) throws Exception {
		examples.createGreetingSourceTree(sourceDirectory);

		LaunchMultiFileSourceProgramsExamples.LaunchResult result = examples.launchMain(sourceDirectory, "Rod");

		assertThat(result.exitCode())
				.as("The child JVM should launch Main.java successfully")
				.isZero();
		assertThat(result.output())
				.as("Main.java should run with helper source files discovered by the Java 22 launcher")
				.contains("hello, Rod");
		assertThat(Files.exists(sourceDirectory.resolve("Greeting.java")))
				.as("The source tree should include a helper source file outside Main.java")
				.isTrue();
		assertThat(Files.exists(sourceDirectory.resolve("messages/MessageFormatter.java")))
				.as("The source tree should include a helper source file in a package directory")
				.isTrue();
	}

	@Test
	void launcherReportsCompilationFailureForMissingHelperSource(@TempDir Path sourceDirectory) throws Exception {
		examples.createBrokenSourceTree(sourceDirectory);

		LaunchMultiFileSourceProgramsExamples.LaunchResult result = examples.launchMain(sourceDirectory);

		assertThat(result.exitCode())
				.as("A broken launched source program should return a non-zero process exit code")
				.isNotZero();
		assertThat(result.output())
				.as("The launcher should surface compiler diagnostics from the child JVM")
				.contains("MissingGreeting");
	}
}
