package net.jrodolfo.java_evolution.java26.structured_concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the sixth preview of Structured Concurrency from JEP 525.
 *
 * <p>
 * The preview API is exercised in an isolated child JVM. The generated probe
 * uses structured scopes for deterministic success and failure workflows,
 * keeping preview flags out of the main Maven build.
 * </p>
 */
public class StructuredConcurrencySixthPreviewExamples {

	/**
	 * Runs the Java 26 preview Structured Concurrency workflow.
	 *
	 * @param workspace temporary directory for generated source and classes
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public StructuredConcurrencyWorkflowResult runStructuredConcurrencyWorkflow(Path workspace)
			throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("StructuredConcurrencyProbe.java");
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
				"StructuredConcurrencyProbe");

		return new StructuredConcurrencyWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the preview API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.util.concurrent.StructuredTaskScope;
				import java.util.concurrent.StructuredTaskScope.FailedException;
				import java.util.concurrent.StructuredTaskScope.Subtask;

				public class StructuredConcurrencyProbe {
				    public static void main(String[] args) throws Throwable {
				        successfulScope();
				        failingScope();
				    }

				    private static void successfulScope() throws Throwable {
				        try (StructuredTaskScope<String, Void> scope =
				                StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow())) {
				            Subtask<String> user = scope.fork(() -> "user:ana");
				            Subtask<String> order = scope.fork(() -> "order:42");
				            scope.join();

				            System.out.println("joined-count=2");
				            System.out.println("user-state=" + user.state());
				            System.out.println("order-state=" + order.state());
				            System.out.println("combined=" + user.get() + "," + order.get());
				            System.out.println("success-cancelled=" + scope.isCancelled());
				        }
				    }

				    private static void failingScope() throws Exception {
				        try (StructuredTaskScope<String, Void> scope =
				                StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow())) {
				            Subtask<String> ok = scope.fork(() -> "ok");
				            Subtask<String> failed = scope.fork(() -> {
				                throw new IllegalStateException("missing-user");
				            });

				            try {
				                scope.join();
				                System.out.println("join-result=unexpected-success");
				            }
				            catch (FailedException exception) {
				                System.out.println("join-result=failed-as-unit");
				                System.out.println("failure-cause=" + exception.getCause().getClass().getSimpleName());
				            }

				            System.out.println("ok-state=" + ok.state());
				            System.out.println("failed-state=" + failed.state());
				            System.out.println("failed-exception=" + failed.exception().getClass().getSimpleName());
				        }
				    }
				}
				""";
	}

	/**
	 * Explains the concurrency problem.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "unstructured concurrent tasks can outlive their caller and make failures, cancellation, and observability harder";
	}

	/**
	 * Explains the structured-concurrency idea.
	 *
	 * @return a short feature explanation
	 */
	public String idea() {
		return "structured concurrency treats related subtasks as one scoped unit of work with clear fork, join, failure, and close points";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "structured concurrency is a sixth preview API in Java 26";
	}

	/**
	 * Explains why the preview API runs in a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the preview API is compiled and run in an isolated child JVM with --enable-preview so the main Maven build stays non-preview";
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
	public record StructuredConcurrencyWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			CommandResult compilation,
			CommandResult execution) {
	}

	/** Result from one child process invocation. */
	public record CommandResult(int exitCode, String output) {
	}
}
