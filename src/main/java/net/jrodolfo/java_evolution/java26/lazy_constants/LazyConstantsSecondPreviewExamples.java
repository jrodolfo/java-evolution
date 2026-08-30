package net.jrodolfo.java_evolution.java26.lazy_constants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the second preview of Lazy Constants from JEP 526.
 *
 * <p>
 * The preview API is exercised in an isolated child JVM. The generated probe
 * verifies deferred initialization, one-time supplier evaluation, stable reuse,
 * and the non-initializing {@code orElse} operation without external state.
 * </p>
 */
public class LazyConstantsSecondPreviewExamples {

	/**
	 * Runs the Java 26 preview Lazy Constants workflow.
	 *
	 * @param workspace temporary directory for generated source and classes
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public LazyConstantsWorkflowResult runLazyConstantsWorkflow(Path workspace)
			throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("LazyConstantsProbe.java");
		Path classesDirectory = workspace.resolve("classes");
		Files.createDirectories(classesDirectory);
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		CommandResult compilation = run(
				javacCommand(),
				"--enable-preview",
				"--release",
				"26",
				"-d",
				classesDirectory.toString(),
				sourceFile.toString());

		CommandResult execution = run(
				javaCommand(),
				"--enable-preview",
				"-cp",
				classesDirectory.toString(),
				"LazyConstantsProbe");

		return new LazyConstantsWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the preview Lazy Constants API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.lang.LazyConstant;
				import java.util.concurrent.atomic.AtomicInteger;

				public class LazyConstantsProbe {
				    public static void main(String[] args) {
				        AtomicInteger evaluations = new AtomicInteger();
				        LazyConstant<String> value = LazyConstant.of(() -> {
				            evaluations.incrementAndGet();
				            return "initialized-once";
				        });

				        System.out.println("before-initialized=" + value.isInitialized());
				        System.out.println("fallback=" + value.orElse("fallback"));
				        System.out.println("after-fallback-initialized=" + value.isInitialized());
				        String first = value.get();
				        String second = value.get();
				        System.out.println("first=" + first);
				        System.out.println("same-value=" + first.equals(second));
				        System.out.println("after-get-initialized=" + value.isInitialized());
				        System.out.println("evaluations=" + evaluations.get());
				    }
				}
				""";
	}

	/**
	 * Explains the problem addressed by Lazy Constants.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "some values are expensive to create and should be initialized only when first needed";
	}

	/**
	 * Explains the relationship to ordinary final fields.
	 *
	 * @return a short comparison
	 */
	public String comparisonWithFinalFields() {
		return "final fields are initialized during construction, while lazy constants defer initialization and then become constant-like";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "Lazy Constants are a second preview API in Java 26, following Stable Values in Java 25";
	}

	/**
	 * Explains why the preview API runs in a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the Java 26 preview API is compiled and run in an isolated child JVM with --enable-preview";
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
		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
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

	/** Result from compiling and running the generated preview source. */
	public record LazyConstantsWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			CommandResult compilation,
			CommandResult execution) {
	}

	/** Result from one child process invocation. */
	public record CommandResult(int exitCode, String output) {
	}
}
