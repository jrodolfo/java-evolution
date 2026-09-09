package net.jrodolfo.java_evolution.java26.vector_api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Java 26 Vector API, continued as an eleventh incubator by
 * JEP 529.
 *
 * <p>
 * The repository itself does not compile against incubator modules. Instead,
 * this example writes a small Java source file, compiles it with
 * {@code javac --add-modules jdk.incubator.vector --release 26}, and runs it
 * with {@code java --add-modules jdk.incubator.vector}. That keeps the main
 * Maven build stable while still demonstrating the real incubator API.
 * </p>
 */
public class VectorApiEleventhIncubatorNotes {

	/**
	 * Runs a real Java 26 Vector API workflow in a temporary workspace.
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
				javacCommand(), "--add-modules", "jdk.incubator.vector", "--release", "26", "-d",
				classesDirectory.toString(), sourceFile.toString());
		CommandResult execution = run(
				javaCommand(), "--add-modules", "jdk.incubator.vector", "-cp",
				classesDirectory.toString(), "VectorApiProbe");

		return new VectorWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/** @return generated Java source for the child JVM */
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

	/** @return a short definition of the programming model */
	public String programmingModel() {
		return "the Vector API expresses Single Instruction, Multiple Data computations in Java";
	}

	/** @return a short goal statement without a benchmark claim */
	public String goal() {
		return "the JVM can map vector operations to CPU vector instructions when the platform supports them";
	}

	/** @return a short status note */
	public String status() {
		return "the Vector API is an eleventh incubator API in Java 26";
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

	/** Result from compiling and running the generated incubator source. */
	public record VectorWorkflowResult(
			Path sourceFile, Path classesDirectory, CommandResult compilation, CommandResult execution) {
	}

	/** Result from one child process invocation. */
	public record CommandResult(int exitCode, String output) {
	}
}
