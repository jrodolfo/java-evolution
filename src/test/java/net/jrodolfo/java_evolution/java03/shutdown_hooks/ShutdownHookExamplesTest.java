package net.jrodolfo.java_evolution.java03.shutdown_hooks;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
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

		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
		}
		String childOutput = output.join();

		assertThat(finished)
				.as("The child JVM should finish instead of leaving the Maven test JVM waiting")
				.isTrue();
		assertThat(process.exitValue())
				.as("The child JVM should exit normally; output was: %s", childOutput)
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
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}
}
