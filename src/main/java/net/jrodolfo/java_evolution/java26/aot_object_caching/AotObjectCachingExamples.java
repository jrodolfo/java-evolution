package net.jrodolfo.java_evolution.java26.aot_object_caching;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Java 26 AOT object-caching command-line workflow.
 *
 * <p>
 * The example launches a tiny source file in a child JVM with
 * {@code -XX:AOTCacheOutput}, then launches it again with {@code -XX:AOTCache}.
 * It verifies cache creation and reuse, not startup performance.
 * </p>
 */
public class AotObjectCachingExamples {

	/**
	 * Creates and then uses an AOT object cache in a temporary workspace.
	 *
	 * @param workspace temporary directory for generated source and cache files
	 * @return results from both child-JVM runs and the generated cache metadata
	 * @throws IOException when the source cannot be written or a child process
	 * cannot be started
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public AotObjectCachingWorkflowResult runAotObjectCachingWorkflow(Path workspace)
			throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("AotObjectProbe.java");
		Path cacheFile = workspace.resolve("probe.aot");
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);
		Files.deleteIfExists(cacheFile);

		CommandResult cacheCreation = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTCacheOutput=" + cacheFile,
				"-Daot.phase=creation",
				sourceFile.toString());

		CommandResult cacheUsage = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTCache=" + cacheFile,
				"-Daot.phase=usage",
				sourceFile.toString());

		long cacheSize = Files.exists(cacheFile) ? Files.size(cacheFile) : 0;
		return new AotObjectCachingWorkflowResult(sourceFile, cacheFile, cacheSize, cacheCreation, cacheUsage);
	}

	/**
	 * Source code used by the child JVM.
	 *
	 * @return a small source-launcher program with stable output markers
	 */
	public String probeSource() {
		return """
				public class AotObjectProbe {
				    private static final String MESSAGE = "aot-object-cache";

				    public static void main(String[] args) {
				        System.out.println("phase=" + System.getProperty("aot.phase", "missing"));
				        System.out.println("message=" + MESSAGE);
				    }
				}
				""";
	}

	/**
	 * Explains the startup problem addressed by AOT object caching.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "application startup can repeat object creation and initialization work that is predictable across runs";
	}

	/**
	 * Describes the Java 26 idea.
	 *
	 * @return a short feature explanation
	 */
	public String java26Idea() {
		return "ahead-of-time object caching stores selected initialized objects for faster startup and can be used with any GC";
	}

	/**
	 * Explains the boundary of this executable example.
	 *
	 * @return a short test-boundary explanation
	 */
	public String testBoundary() {
		return "this example proves AOT cache creation and reuse, not startup performance";
	}

	private String javaCommand() {
		return toolCommand("java");
	}

	private String toolCommand(String tool) {
		String executable = isWindows() ? tool + ".exe" : tool;
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));

		boolean finished = process.waitFor(30, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new CommandResult(-1, output.join());
		}

		return new CommandResult(process.exitValue(), output.join());
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	/**
	 * Result of the complete AOT object-caching workflow.
	 *
	 * @param sourceFile generated source file
	 * @param cacheFile generated AOT cache file
	 * @param cacheSize size of the generated cache in bytes
	 * @param cacheCreation result from the cache-creation child JVM
	 * @param cacheUsage result from the cache-usage child JVM
	 */
	public record AotObjectCachingWorkflowResult(
			Path sourceFile,
			Path cacheFile,
			long cacheSize,
			CommandResult cacheCreation,
			CommandResult cacheUsage) {
	}

	/**
	 * Result from one child-JVM invocation.
	 *
	 * @param exitCode process exit code, or {@code -1} when the process timed out
	 * @param output combined standard output and standard error
	 */
	public record CommandResult(int exitCode, String output) {
	}
}
