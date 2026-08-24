package net.jrodolfo.java_evolution.java25.aot_command_line;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 25 Ahead-of-Time (AOT) Command-Line Ergonomics, introduced
 * by JEP 514.
 *
 * <p>
 * The example launches a small Java source file twice: first with
 * {@code -XX:AOTCacheOutput} to create a real AOT cache, then with
 * {@code -XX:AOTCache} to use that cache. It proves the command-line workflow,
 * not a startup-speed improvement.
 * </p>
 */
public class AotCommandLineErgonomicsExamples {

	/**
	 * Runs the full AOT cache workflow in a temporary workspace.
	 *
	 * @param workspace temporary directory for the probe source and cache file
	 * @return result containing both child-JVM runs and cache metadata
	 * @throws IOException when files cannot be written or inspected
	 * @throws InterruptedException when waiting for a child JVM is interrupted
	 */
	public AotWorkflowResult runAotWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("AotCacheProbe.java");
		Path cacheFile = workspace.resolve("probe.aot");
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);
		Files.deleteIfExists(cacheFile);

		CommandResult cacheCreation = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTCacheOutput=" + cacheFile,
				"-Daot.phase=training",
				sourceFile.toString());

		CommandResult cacheUsage = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTCache=" + cacheFile,
				"-Daot.phase=production",
				sourceFile.toString());

		long cacheSize = Files.exists(cacheFile) ? Files.size(cacheFile) : 0;
		return new AotWorkflowResult(sourceFile, cacheFile, cacheSize, cacheCreation, cacheUsage);
	}

	/**
	 * Source used by the child JVM.
	 *
	 * @return generated source-launcher program
	 */
	public String probeSource() {
		return """
				public class AotCacheProbe {
				    public static void main(String[] args) {
				        System.out.println("aot-cache-probe=" + System.getProperty("aot.phase", "missing"));
				        System.out.println("probe-class=" + AotCacheProbe.class.getName());
				    }
				}
				""";
	}

	/**
	 * Explains the problem that AOT workflows address.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "Java startup can spend time discovering, loading, and linking classes before application code is ready";
	}

	/**
	 * Explains the Java 25 improvement.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "Java 25 simplifies common AOT cache creation with the -XX:AOTCacheOutput command-line option";
	}

	/**
	 * Explains how an AOT cache is used after creation.
	 *
	 * @return a short workflow explanation
	 */
	public String productionWorkflow() {
		return "after creating an AOT cache, the production run can start with -XX:AOTCache to reuse ahead-of-time data";
	}

	/**
	 * Explains the test boundary for this executable example.
	 *
	 * @return the project decision
	 */
	public String testBoundary() {
		return "this example proves the AOT cache command-line workflow, not startup performance";
	}

	private String javaCommand() {
		String executable = isWindows() ? "java.exe" : "java";
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
	 * Result of the full AOT workflow.
	 *
	 * @param sourceFile generated probe source file
	 * @param cacheFile generated AOT cache file
	 * @param cacheSize size of the generated AOT cache file in bytes
	 * @param cacheCreation child JVM result for {@code -XX:AOTCacheOutput}
	 * @param cacheUsage child JVM result for {@code -XX:AOTCache}
	 */
	public record AotWorkflowResult(
			Path sourceFile,
			Path cacheFile,
			long cacheSize,
			CommandResult cacheCreation,
			CommandResult cacheUsage) {
	}

	/**
	 * Result from one child JVM invocation.
	 *
	 * @param exitCode process exit code, or {@code -1} when the process timed out
	 * @param output combined standard output and standard error
	 */
	public record CommandResult(int exitCode, String output) {
	}
}
