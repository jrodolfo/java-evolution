package net.jrodolfo.java_evolution.java25.vector_api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Java 25 Vector API, continued as a tenth incubator by JEP
 * 508.
 *
 * <p>
 * The repository itself does not compile against incubator modules. Instead,
 * this example writes a small Java source file, compiles it with
 * {@code javac --add-modules jdk.incubator.vector --release 25}, and runs it
 * with {@code java --add-modules jdk.incubator.vector}. That keeps the main
 * Maven build stable while still demonstrating the real incubator API.
 * </p>
 */
public class VectorApiTenthIncubatorExamples {

	/**
	 * Runs a real Java 25 Vector API workflow in a temporary workspace.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public VectorWorkflowResult runVectorWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("VectorApiProbe.java");
		Path classesDirectory = workspace.resolve("classes");
		Files.createDirectories(classesDirectory);
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		CommandResult compilation = run(
				javacCommand(),
				"--add-modules",
				"jdk.incubator.vector",
				"--release",
				"25",
				"-d",
				classesDirectory.toString(),
				sourceFile.toString());

		CommandResult execution = run(
				javaCommand(),
				"--add-modules",
				"jdk.incubator.vector",
				"-cp",
				classesDirectory.toString(),
				"VectorApiProbe");

		return new VectorWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the Java 25 incubator API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.util.Arrays;
				import jdk.incubator.vector.IntVector;
				import jdk.incubator.vector.VectorSpecies;

				public class VectorApiProbe {
				    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

				    public static void main(String[] args) {
				        int[] left = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
				        int[] right = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
				        int[] result = add(left, right);

				        int loopBound = SPECIES.loopBound(left.length);
				        System.out.println("species-length=" + SPECIES.length());
				        System.out.println("species-bits=" + SPECIES.vectorBitSize());
				        System.out.println("loop-bound=" + loopBound);
				        System.out.println("tail-elements=" + (left.length - loopBound));
				        System.out.println("result=" + Arrays.toString(result));
				    }

				    static int[] add(int[] left, int[] right) {
				        int[] result = new int[left.length];
				        int index = 0;
				        int upperBound = SPECIES.loopBound(left.length);

				        for (; index < upperBound; index += SPECIES.length()) {
				            IntVector leftVector = IntVector.fromArray(SPECIES, left, index);
				            IntVector rightVector = IntVector.fromArray(SPECIES, right, index);
				            leftVector.add(rightVector).intoArray(result, index);
				        }

				        for (; index < left.length; index++) {
				            result[index] = left[index] + right[index];
				        }

				        return result;
				    }
				}
				""";
	}

	/**
	 * Explains the problem that the Vector API addresses.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "scalar loops process one value at a time even when the same operation could be applied across many values";
	}

	/**
	 * Explains the Java 25 feature idea.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "the Vector API lets Java code express lane-wise vector computations that the JVM can compile to CPU vector instructions";
	}

	/**
	 * Defines SIMD in plain English.
	 *
	 * @return a short terminology explanation
	 */
	public String terminology() {
		return "SIMD means Single Instruction, Multiple Data: one instruction applies the same operation across multiple vector lanes";
	}

	/**
	 * Explains why this example uses a child process.
	 *
	 * @return the incubator-module boundary
	 */
	public String incubatorBoundary() {
		return "the incubator API is compiled and run in a child JVM with --add-modules jdk.incubator.vector";
	}

	/**
	 * Explains what this executable example can and cannot prove.
	 *
	 * @return the test boundary
	 */
	public String testBoundary() {
		return "this example proves vector API semantics and numeric correctness, not hardware SIMD usage or speed";
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
	 * Result from compiling and running the generated incubator-module source.
	 *
	 * @param sourceFile generated source file
	 * @param classesDirectory generated classes directory
	 * @param compilation child {@code javac} result
	 * @param execution child {@code java} result
	 */
	public record VectorWorkflowResult(
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
