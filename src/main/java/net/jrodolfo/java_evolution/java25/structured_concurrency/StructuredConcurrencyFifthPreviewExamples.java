package net.jrodolfo.java_evolution.java25.structured_concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Structured Concurrency, previewed for the fifth time in Java 25
 * by JEP 505.
 *
 * <p>
 * The repository itself does not compile against preview APIs. Instead, this
 * example writes a small Java 25 source file, compiles it with
 * {@code javac --enable-preview --release 25}, and runs it with
 * {@code java --enable-preview}. That keeps the main Maven build stable while
 * still demonstrating the real preview API.
 * </p>
 */
public class StructuredConcurrencyFifthPreviewExamples {

	/**
	 * Runs a real Java 25 preview structured-concurrency workflow.
	 *
	 * @param workspace temporary directory for generated source and class files
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
				"25",
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
	 * Source used by the child JVM to exercise the Java 25 preview API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.util.concurrent.StructuredTaskScope;
				import java.util.concurrent.StructuredTaskScope.FailedException;
				import java.util.concurrent.StructuredTaskScope.Subtask;
				import java.util.stream.Collectors;
				import java.util.stream.Stream;

				public class StructuredConcurrencyProbe {
				    public static void main(String[] args) throws Throwable {
				        runSuccessfulScope();
				        runFailingScope();
				    }

				    private static void runSuccessfulScope() throws Throwable {
				        try (StructuredTaskScope<String, Stream<Subtask<String>>> scope =
				                StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>allSuccessfulOrThrow())) {
				            Subtask<String> user = scope.fork(() -> "user:ana");
				            Subtask<String> order = scope.fork(() -> "order:42");
				            var subtasks = scope.join().collect(Collectors.toList());

				            System.out.println("joined-count=" + subtasks.size());
				            System.out.println("user-state=" + user.state());
				            System.out.println("order-state=" + order.state());
				            System.out.println("combined=" + user.get() + "," + order.get());
				            System.out.println("success-cancelled=" + scope.isCancelled());
				        }
				    }

				    private static void runFailingScope() throws Exception {
				        try (StructuredTaskScope<String, Void> scope =
				                StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow())) {
				            Subtask<String> ok = scope.fork(() -> "ok");
				            Subtask<String> failed = scope.fork(() -> {
				                throw new IllegalStateException("missing-user");
				            });

				            try {
				                scope.join();
				                System.out.println("join-result=unexpected-success");
				            } catch (FailedException exception) {
				                System.out.println("join-result=failed-as-unit");
				                System.out.println("failure-cause=" + exception.getCause().getClass().getSimpleName());
				                System.out.println("failure-message=" + exception.getCause().getMessage());
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
	 * Explains the problem that structured concurrency addresses.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "related concurrent subtasks can otherwise be started, joined, cancelled, and observed in scattered places";
	}

	/**
	 * Explains how this problem is commonly handled without structured
	 * concurrency.
	 *
	 * @return a short before-Java-25 explanation
	 */
	public String commonAlternative() {
		return "developers often coordinate related work with ExecutorService, Future objects, manual cancellation, and try-finally cleanup";
	}

	/**
	 * Explains the Java 25 structured-concurrency idea.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "StructuredTaskScope treats related subtasks as one structured unit of work with clear fork, join, failure, cancellation, and close points";
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
		return "this example proves structured scope success and failure semantics, not scheduler timing or cancellation races";
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

		boolean finished = process.waitFor(30, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new CommandResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new CommandResult(process.exitValue(), output);
	}

	/**
	 * Result from compiling and running the generated preview source.
	 *
	 * @param sourceFile generated source file
	 * @param classesDirectory generated classes directory
	 * @param compilation child {@code javac} result
	 * @param execution child {@code java} result
	 */
	public record StructuredConcurrencyWorkflowResult(
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
