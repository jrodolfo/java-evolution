package net.jrodolfo.java_evolution.java25.stable_values;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 25 Stable Values, previewed by JEP 502.
 *
 * <p>
 * The repository itself does not compile against preview APIs. Instead, this
 * example writes a small Java 25 source file, compiles it with
 * {@code javac --enable-preview --release 25}, and runs it with
 * {@code java --enable-preview} when the active toolchain is JDK 25. That
 * keeps the main Maven build stable while still demonstrating the real preview
 * API.
 * </p>
 */
public class StableValuesPreviewExamples {

	/**
	 * Runs a real Java 25 preview Stable Values workflow in a temporary workspace.
	 * Full child-compilation execution requires a JDK 25 preview compiler.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public StableValuesWorkflowResult runStableValuesWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("StableValuesPreviewProbe.java");
		Path classesDirectory = workspace.resolve("classes");
		Files.createDirectories(classesDirectory);
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		CommandResult compilation = run(
				javacCommand(),
				"--enable-preview",
				"--release",
				"25",
				"-d",
				classesDirectory.toString(),
				sourceFile.toString());

		CommandResult execution = run(
				javaCommand(),
				"--enable-preview",
				"-cp",
				classesDirectory.toString(),
				"StableValuesPreviewProbe");

		return new StableValuesWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the Java 25 preview API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.lang.StableValue;
				import java.util.List;
				import java.util.Map;
				import java.util.Set;
				import java.util.concurrent.atomic.AtomicInteger;
				import java.util.function.Supplier;

				public class StableValuesPreviewProbe {
				    public static void main(String[] args) {
				        StableValue<String> value = StableValue.of();
				        AtomicInteger holderComputations = new AtomicInteger();

				        String first = value.orElseSet(() -> "computed-" + holderComputations.incrementAndGet());
				        String second = value.orElseSet(() -> "computed-" + holderComputations.incrementAndGet());
				        boolean changedAfterSet = value.trySet("different");

				        AtomicInteger supplierComputations = new AtomicInteger();
				        Supplier<String> memoized = StableValue.supplier(
				                () -> "supplier-" + supplierComputations.incrementAndGet());
				        String supplierFirst = memoized.get();
				        String supplierSecond = memoized.get();

				        AtomicInteger listComputations = new AtomicInteger();
				        List<String> lazyList = StableValue.list(
				                3, index -> "item-" + index + "-" + listComputations.incrementAndGet());

				        AtomicInteger mapComputations = new AtomicInteger();
				        Map<String, String> lazyMap = StableValue.map(
				                Set.of("alpha", "beta"),
				                key -> key + "-" + mapComputations.incrementAndGet());

				        System.out.println("first=" + first);
				        System.out.println("second=" + second);
				        System.out.println("holder-computations=" + holderComputations.get());
				        System.out.println("is-set=" + value.isSet());
				        System.out.println("try-set-after-set=" + changedAfterSet);
				        System.out.println("or-else-throw=" + value.orElseThrow());
				        System.out.println("supplier-values=" + supplierFirst + "," + supplierSecond);
				        System.out.println("supplier-computations=" + supplierComputations.get());
				        System.out.println("list-values=" + lazyList.get(1) + "," + lazyList.get(1) + "," + lazyList.get(2));
				        System.out.println("list-computations=" + listComputations.get());
				        System.out.println("map-alpha=" + lazyMap.get("alpha") + "," + lazyMap.get("alpha"));
				        System.out.println("map-computations=" + mapComputations.get());
				    }
				}
				""";
	}

	/**
	 * Explains the problem that Stable Values address.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "some expensive values should be initialized lazily but become immutable after the first successful initialization";
	}

	/**
	 * Explains the Java 25 feature goal.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "StableValue models deferred immutability: content is initialized at most once and can then be reused as stable data";
	}

	/**
	 * Explains why this example uses a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the preview API is compiled and run in a child JVM with --enable-preview so the main Maven build stays non-preview";
	}

	/**
	 * Explains what this executable example can and cannot prove.
	 *
	 * @return the test boundary
	 */
	public String testBoundary() {
		return "this example proves lazy at-most-once API semantics, not JVM optimization or startup performance";
	}

	private String javacCommand() {
		return toolCommand("javac");
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
	 * Result from compiling and running the generated preview source.
	 *
	 * @param sourceFile generated source file
	 * @param classesDirectory generated classes directory
	 * @param compilation child {@code javac} result
	 * @param execution child {@code java} result
	 */
	public record StableValuesWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			CommandResult compilation,
			CommandResult execution) {
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
