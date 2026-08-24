package net.jrodolfo.java_evolution.java20.vector_api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Java 20 Vector API, continued as a fifth incubator by JEP
 * 438.
 *
 * <p>
 * The repository itself does not compile against incubator modules. Instead,
 * this example writes a small Java source file, compiles it with
 * {@code javac --add-modules jdk.incubator.vector --release 20}, and runs it
 * with {@code java --add-modules jdk.incubator.vector}. That keeps the main
 * Maven build stable while still demonstrating the real incubator API.
 * </p>
 */
public class VectorApiFifthIncubatorExamples {

	/**
	 * Runs a real Java 20 Vector API workflow in a temporary workspace.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return result containing compilation, class inspection, and execution output
	 * @throws IOException when source files cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public VectorWorkflowResult runVectorWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("VectorApiJava20Probe.java");
		Path classesDirectory = workspace.resolve("classes");
		Files.createDirectories(classesDirectory);
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		CommandResult compilation = run(
				javacCommand(),
				"--add-modules",
				"jdk.incubator.vector",
				"--release",
				"20",
				"-d",
				classesDirectory.toString(),
				sourceFile.toString());

		CommandResult inspection = run(
				javapCommand(),
				"-verbose",
				"-classpath",
				classesDirectory.toString(),
				"VectorApiJava20Probe");

		CommandResult execution = run(
				javaCommand(),
				"--add-modules",
				"jdk.incubator.vector",
				"-cp",
				classesDirectory.toString(),
				"VectorApiJava20Probe");

		return new VectorWorkflowResult(sourceFile, classesDirectory, compilation, inspection, execution);
	}

	/**
	 * Source used by the child JVM to exercise the Java 20 incubator API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.util.Arrays;
				import jdk.incubator.vector.IntVector;
				import jdk.incubator.vector.VectorSpecies;

				public class VectorApiJava20Probe {
				    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_128;

				    public static void main(String[] args) {
				        int[] left = {1, 2, 3, 4, 5};
				        int[] right = {10, 20, 30, 40, 50};
				        int[] result = add(left, right);

				        System.out.println("species-length=" + SPECIES.length());
				        System.out.println("species-bits=" + SPECIES.vectorBitSize());
				        System.out.println("loop-bound=" + SPECIES.loopBound(left.length));
				        System.out.println("tail-elements=" + (left.length - SPECIES.loopBound(left.length)));
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
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "numeric code often repeats the same operation over many values one scalar value at a time";
	}

	/**
	 * Explains the common older approach.
	 *
	 * @return a short explanation
	 */
	public String oldApproachProblem() {
		return "developers relied on JIT auto-vectorization or native libraries when they wanted CPU vector instructions";
	}

	/**
	 * Explains the Vector API idea.
	 *
	 * @return a short explanation
	 */
	public String incubatorIdea() {
		return "the Vector API lets Java code express SIMD-style lane-wise vector computations directly";
	}

	/**
	 * Names realistic use cases.
	 *
	 * @return a short list of examples
	 */
	public String realUseCases() {
		return "image processing, audio processing, compression, and numeric or machine-learning loops can benefit";
	}

	/**
	 * Describes the Java 20 maturity level.
	 *
	 * @return a short status note
	 */
	public String incubatorStatus() {
		return "the Vector API was in its fifth incubator round in Java 20";
	}

	/**
	 * Explains why this example uses a child process.
	 *
	 * @return the incubator-module boundary
	 */
	public String incubatorBoundary() {
		return "the Java 20 incubator API is compiled in a child process with --release 20 and --add-modules jdk.incubator.vector";
	}

	/**
	 * Explains what this executable example can and cannot prove.
	 *
	 * @return the test boundary
	 */
	public String testBoundary() {
		return "this example proves vector API semantics and Java 20 bytecode targeting, not hardware SIMD usage or speed";
	}

	/**
	 * Points learners to the later Vector API module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 vector_api module for the later tenth-incubator learning guide";
	}

	private String javacCommand() {
		return toolCommand("javac");
	}

	private String javaCommand() {
		return toolCommand("java");
	}

	private String javapCommand() {
		return toolCommand("javap");
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
	 * Result from compiling, inspecting, and running the generated Vector API source.
	 *
	 * @param sourceFile generated source file
	 * @param classesDirectory generated classes directory
	 * @param compilation child {@code javac} result
	 * @param inspection child {@code javap} result
	 * @param execution child {@code java} result
	 */
	public record VectorWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			CommandResult compilation,
			CommandResult inspection,
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
