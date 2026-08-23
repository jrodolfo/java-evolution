package net.jrodolfo.java_evolution.java03.shutdown_hooks;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ShutdownHookExamplesTest {

	@Test
	void shutdownHookRunsDuringOrderlyChildJvmShutdown(@TempDir Path workspace) throws Exception {
		Path markerFile = workspace.resolve("shutdown-hook-marker.txt");
		Process process = new ProcessBuilder(javaExecutable(), "-cp", System.getProperty("java.class.path"),
				ShutdownHookExamples.class.getName(), markerFile.toString())
				.redirectErrorStream(true)
				.start();

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		String output = new String(process.getInputStream().readAllBytes());

		assertThat(finished)
				.as("The child JVM should finish instead of leaving the Maven test JVM waiting")
				.isTrue();
		assertThat(process.exitValue())
				.as("The child JVM should exit normally; output was: %s", output)
				.isZero();
		assertThat(markerFile)
				.as("The shutdown hook should write a marker during orderly JVM shutdown")
				.exists();
		assertThat(Files.readString(markerFile))
				.as("The marker text should come from the registered shutdown hook")
				.isEqualTo(ShutdownHookExamples.MARKER_MESSAGE);
	}

	@Test
	void childProcessModeRejectsMissingMarkerPath() {
		assertThat(new ShutdownHookExamples().runChildProcessMode(new String[0]))
				.as("The child-process entry point should report invalid command-line usage")
				.isEqualTo(2);
	}

	private String javaExecutable() {
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	}
}
