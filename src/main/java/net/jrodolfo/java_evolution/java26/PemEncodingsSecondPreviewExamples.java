package net.jrodolfo.java_evolution.java26;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the second preview of PEM encodings of cryptographic objects
 * from JEP 524.
 *
 * <p>
 * The preview API is exercised in an isolated child JVM. The generated probe
 * performs an in-memory PEM encode/decode round trip using deterministic DER
 * bytes, so the main Maven build does not need preview flags or cryptographic
 * files.
 * </p>
 */
public class PemEncodingsSecondPreviewExamples {

	/**
	 * Runs the Java 26 preview PEM round-trip workflow.
	 *
	 * @param workspace temporary directory for generated source and classes
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public PemWorkflowResult runPemWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("PemProbe.java");
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
				"PemProbe");

		return new PemWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the preview PEM API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.security.PEM;
				import java.security.PEMDecoder;
				import java.security.PEMEncoder;
				import java.util.Arrays;

				public class PemProbe {
				    public static void main(String[] args) {
				        byte[] der = {1, 2, 3, 4, 5};
				        PEM original = new PEM("LEARNING OBJECT", java.util.Base64.getEncoder().encodeToString(der));
				        String encoded = PEMEncoder.of().encodeToString(original);
				        PEM decoded = (PEM) PEMDecoder.of().decode(encoded);

				        System.out.println("label=" + decoded.type());
				        System.out.println("payload-matches=" + Arrays.equals(der, decoded.decode()));
				        System.out.println("has-boundaries=" + (encoded.contains("-----BEGIN LEARNING OBJECT-----")
				                && encoded.contains("-----END LEARNING OBJECT-----")));
				    }
				}
				""";
	}

	/**
	 * Explains the feature goal.
	 *
	 * @return a short feature explanation
	 */
	public String purpose() {
		return "standardize reading and writing cryptographic objects using PEM text encodings";
	}

	/**
	 * Explains the second-preview status.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "PEM encodings are a second preview API in Java 26 after previewing in Java 25";
	}

	/**
	 * Explains why the preview API runs in a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the Java 26 preview API is compiled and run in an isolated child JVM with --enable-preview";
	}

	/**
	 * Explains the scope of this example.
	 *
	 * @return a short scope explanation
	 */
	public String exampleScope() {
		return "this example verifies an in-memory PEM envelope round trip without parsing cryptographic formats or using external files";
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
	public record PemWorkflowResult(
			Path sourceFile,
			Path classesDirectory,
			CommandResult compilation,
			CommandResult execution) {
	}

	/** Result from one child process invocation. */
	public record CommandResult(int exitCode, String output) {
	}
}
