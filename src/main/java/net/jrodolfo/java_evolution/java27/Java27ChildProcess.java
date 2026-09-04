package net.jrodolfo.java_evolution.java27;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** Shared bounded child-process support used by Java 27 preview examples. */
public final class Java27ChildProcess {

	private Java27ChildProcess() {
	}

	/** Returns the JDK tool path for the current operating system. */
	public static String tool(String name) {
		String executable = isWindows() ? name + ".exe" : name;
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	/** Runs a child command while draining output before waiting for termination. */
	public static Result run(String... command) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		if (!process.waitFor(30, TimeUnit.SECONDS)) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new Result(-1, output.join());
		}
		return new Result(process.exitValue(), output.join());
	}

	private static String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	private static boolean isWindows() {
		return System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("win");
	}

	/** Result from one child command. */
	public record Result(int exitCode, String output) {
	}
}
