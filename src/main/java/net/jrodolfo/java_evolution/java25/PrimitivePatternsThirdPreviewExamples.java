package net.jrodolfo.java_evolution.java25;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates primitive patterns, previewed for the third time in Java 25 by
 * JEP 507.
 *
 * <p>
 * The repository itself does not compile against preview syntax. Instead, this
 * example writes a small Java 25 source file, compiles it with
 * {@code javac --enable-preview --release 25}, and runs it with
 * {@code java --enable-preview}. That keeps the main Maven build stable while
 * still demonstrating the real preview syntax.
 * </p>
 */
public class PrimitivePatternsThirdPreviewExamples {

	/**
	 * Runs a real Java 25 preview primitive-pattern workflow.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public PrimitivePatternsWorkflowResult runPrimitivePatternsWorkflow(Path workspace)
			throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("PrimitivePatternsProbe.java");
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
				"PrimitivePatternsProbe");

		return new PrimitivePatternsWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise Java 25 preview syntax.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				public class PrimitivePatternsProbe {
				    public static void main(String[] args) {
				        System.out.println("small-byte=" + describeByte(42));
				        System.out.println("large-byte=" + describeByte(1000));
				        System.out.println("exact-int=" + describeInt(42L));
				        System.out.println("too-large-int=" + describeInt(1_000_000_000_000L));

				        System.out.println("byte-case=" + classify(42L));
				        System.out.println("short-case=" + classify(1000L));
				        System.out.println("int-case=" + classify(1_000_000L));
				        System.out.println("long-case=" + classify(1_000_000_000_000L));

				        System.out.println("guard-positive=" + guardedByteStatus(42));
				        System.out.println("guard-negative=" + guardedByteStatus(-1));
				        System.out.println("guard-outside=" + guardedByteStatus(1000));
				    }

				    static String describeByte(int value) {
				        if (value instanceof byte narrowed) {
				            return "fits:" + narrowed;
				        }
				        return "does-not-fit:" + value;
				    }

				    static String describeInt(long value) {
				        if (value instanceof int narrowed) {
				            return "fits:" + narrowed;
				        }
				        return "does-not-fit:" + value;
				    }

				    static String classify(long value) {
				        return switch (value) {
				            case byte b -> "byte:" + b;
				            case short s -> "short:" + s;
				            case int i -> "int:" + i;
				            default -> "long:" + value;
				        };
				    }

				    static String guardedByteStatus(int value) {
				        return switch (value) {
				            case byte b when b >= 0 -> "non-negative-byte:" + b;
				            case byte b -> "negative-byte:" + b;
				            default -> "outside-byte:" + value;
				        };
				    }
				}
				""";
	}

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "make pattern matching more uniform by allowing primitive types in pattern contexts";
	}

	/**
	 * Explains the safety benefit of primitive patterns.
	 *
	 * @return a short safety explanation
	 */
	public String safetyGoal() {
		return "primitive patterns help test whether a primitive conversion is safe before binding the converted value";
	}

	/**
	 * Explains why this example uses a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the preview syntax is compiled and run in a child JVM with --enable-preview so the main Maven build stays non-preview";
	}

	/**
	 * Explains what this executable example can and cannot prove.
	 *
	 * @return the test boundary
	 */
	public String testBoundary() {
		return "this example proves Java 25 preview compiler and runtime behavior, not the future final syntax";
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
		return System.getProperty("os.name").toLowerCase().contains("win");
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
	public record PrimitivePatternsWorkflowResult(
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
