package net.jrodolfo.java_evolution.java24.aot_class_loading;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates ahead-of-time class loading and linking, introduced in Java 24.
 *
 * <p>
 * The example runs the explicit JEP 483 workflow: compile a tiny application,
 * package it as a JAR, record AOT configuration, create an AOT cache, and run
 * the application with that cache. It verifies workflow behavior, not startup
 * speed.
 * </p>
 */
public class AotClassLoadingExamples {

	/**
	 * Runs the Java 24 AOT class-loading workflow in a temporary workspace.
	 *
	 * @param workspace temporary directory for source, classes, JAR, configuration,
	 * and cache files
	 * @return result containing generated file paths, sizes, and command output
	 * @throws IOException when files cannot be written or inspected
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public AotClassLoadingWorkflowResult runAotClassLoadingWorkflow(Path workspace)
			throws IOException, InterruptedException {
		Path sourceDirectory = workspace.resolve("src");
		Path classesDirectory = workspace.resolve("classes");
		Path sourceFile = sourceDirectory.resolve("AotClassLoadingProbe.java");
		Path jarFile = workspace.resolve("probe.jar");
		Path configurationFile = workspace.resolve("probe.aotconf");
		Path cacheFile = workspace.resolve("probe.aot");

		Files.createDirectories(sourceDirectory);
		Files.createDirectories(classesDirectory);
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);
		Files.deleteIfExists(jarFile);
		Files.deleteIfExists(configurationFile);
		Files.deleteIfExists(cacheFile);

		CommandResult compilation = run(
				javacCommand(),
				"-d",
				classesDirectory.toString(),
				sourceFile.toString());

		CommandResult packaging = run(
				jarCommand(),
				"--create",
				"--file",
				jarFile.toString(),
				"-C",
				classesDirectory.toString(),
				".");

		CommandResult configurationRecording = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTMode=record",
				"-XX:AOTConfiguration=" + configurationFile,
				"-Daot.phase=record",
				"-cp",
				jarFile.toString(),
				"AotClassLoadingProbe");

		CommandResult cacheCreation = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTMode=create",
				"-XX:AOTConfiguration=" + configurationFile,
				"-XX:AOTCache=" + cacheFile,
				"-cp",
				jarFile.toString());

		CommandResult cachedRun = run(
				javaCommand(),
				"-Xlog:aot",
				"-XX:AOTMode=on",
				"-XX:AOTCache=" + cacheFile,
				"-Daot.phase=run",
				"-cp",
				jarFile.toString(),
				"AotClassLoadingProbe");

		long configurationSize = Files.exists(configurationFile) ? Files.size(configurationFile) : 0;
		long cacheSize = Files.exists(cacheFile) ? Files.size(cacheFile) : 0;

		return new AotClassLoadingWorkflowResult(
				sourceFile,
				classesDirectory,
				jarFile,
				configurationFile,
				cacheFile,
				configurationSize,
				cacheSize,
				compilation,
				packaging,
				configurationRecording,
				cacheCreation,
				cachedRun);
	}

	/**
	 * Source code for the tiny application used by the workflow.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.util.List;
				import java.util.stream.Collectors;

				public class AotClassLoadingProbe {
				    public static void main(String[] args) {
				        String phase = System.getProperty("aot.phase", "missing");
				        String message = List.of("hello", "aot", "class", "loading").stream()
				                .filter(word -> !word.equals("aot"))
				                .collect(Collectors.joining("-"));
				        System.out.println("aot-class-loading-probe=" + phase + ":" + message);
				    }
				}
				""";
	}

	/**
	 * Explains the runtime goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "improve startup by loading and linking classes ahead of application execution";
	}

	/**
	 * Explains class loading in learner-friendly terms.
	 *
	 * @return a short class-loading explanation
	 */
	public String classLoading() {
		return "class loading finds class data and brings it into the JVM";
	}

	/**
	 * Explains class linking in learner-friendly terms.
	 *
	 * @return a short class-linking explanation
	 */
	public String classLinking() {
		return "class linking prepares a loaded class so the JVM can use it safely";
	}

	/**
	 * Explains the explicit Java 24 workflow.
	 *
	 * @return a short workflow explanation
	 */
	public String java24Workflow() {
		return "Java 24 records AOT configuration, creates an AOT cache from it, and then runs the application with that cache";
	}

	/**
	 * Explains the test boundary for this executable example.
	 *
	 * @return a short test-boundary explanation
	 */
	public String testBoundary() {
		return "this example proves the AOT class-loading cache workflow, not startup performance";
	}

	private String javaCommand() {
		return toolCommand("java");
	}

	private String javacCommand() {
		return toolCommand("javac");
	}

	private String jarCommand() {
		return toolCommand("jar");
	}

	private String toolCommand(String command) {
		String executable = isWindows() ? command + ".exe" : command;
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
	 * Result of the complete Java 24 AOT class-loading workflow.
	 *
	 * @param sourceFile generated source file
	 * @param classesDirectory compiled classes directory
	 * @param jarFile generated application JAR
	 * @param configurationFile generated AOT configuration file
	 * @param cacheFile generated AOT cache file
	 * @param configurationSize size of the AOT configuration file in bytes
	 * @param cacheSize size of the AOT cache file in bytes
	 * @param compilation result of the {@code javac} invocation
	 * @param packaging result of the {@code jar} invocation
	 * @param configurationRecording result of the {@code -XX:AOTMode=record} run
	 * @param cacheCreation result of the {@code -XX:AOTMode=create} run
	 * @param cachedRun result of the {@code -XX:AOTCache} application run
	 */
	public record AotClassLoadingWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			Path jarFile,
			Path configurationFile,
			Path cacheFile,
			long configurationSize,
			long cacheSize,
			CommandResult compilation,
			CommandResult packaging,
			CommandResult configurationRecording,
			CommandResult cacheCreation,
			CommandResult cachedRun) {
	}

	/**
	 * Result from one child process invocation.
	 *
	 * @param exitCode process exit code, or {@code -1} when the process timed out
	 * @param output combined standard output and standard error
	 */
	public record CommandResult(int exitCode, String output) {
	}
}
