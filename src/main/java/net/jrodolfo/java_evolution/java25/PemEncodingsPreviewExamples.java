package net.jrodolfo.java_evolution.java25;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 25 PEM encodings of cryptographic objects, previewed by
 * JEP 470.
 *
 * <p>
 * The repository itself does not compile against preview APIs. Instead, this
 * example writes a small Java 25 source file, compiles it with
 * {@code javac --enable-preview --release 25}, and runs it with
 * {@code java --enable-preview} when the active toolchain is JDK 25. That
 * keeps the main build stable while still demonstrating the real preview API.
 * </p>
 */
public class PemEncodingsPreviewExamples {

	/**
	 * Runs a real Java 25 preview PEM workflow in a temporary workspace.
	 * Full child-compilation execution requires a JDK 25 preview compiler.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return result containing compilation and execution output
	 * @throws IOException when the source file cannot be written
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public PemWorkflowResult runPemWorkflow(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("PemPreviewProbe.java");
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
				"PemPreviewProbe");

		return new PemWorkflowResult(sourceFile, classesDirectory, compilation, execution);
	}

	/**
	 * Source used by the child JVM to exercise the Java 25 preview API.
	 *
	 * @return generated Java source
	 */
	public String probeSource() {
		return """
				import java.security.KeyPair;
				import java.security.KeyPairGenerator;
				import java.security.PEMDecoder;
				import java.security.PEMEncoder;
				import java.security.PEMRecord;
				import java.security.PublicKey;
				import java.util.Arrays;

				public class PemPreviewProbe {
				    public static void main(String[] args) throws Exception {
				        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
				        generator.initialize(2048);
				        KeyPair pair = generator.generateKeyPair();

				        String publicKeyPem = PEMEncoder.of().encodeToString(pair.getPublic());
				        PublicKey decodedPublicKey = PEMDecoder.of().decode(publicKeyPem, PublicKey.class);

				        System.out.println("pem-header=" + publicKeyPem.lines().findFirst().orElse("missing"));
				        System.out.println("decoded-algorithm=" + decodedPublicKey.getAlgorithm());
				        System.out.println("encoded-bytes-match="
				                + Arrays.equals(pair.getPublic().getEncoded(), decodedPublicKey.getEncoded()));

				        PEMRecord record = new PEMRecord("TRAINING OBJECT", "AQIDBAU=");
				        String recordPem = PEMEncoder.of().encodeToString(record);
				        PEMRecord decodedRecord = PEMDecoder.of().decode(recordPem, PEMRecord.class);

				        System.out.println("record-header=" + recordPem.lines().findFirst().orElse("missing"));
				        System.out.println("record-type=" + decodedRecord.type());
				        System.out.println("record-content=" + decodedRecord.content());
				    }
				}
				""";
	}

	/**
	 * Explains the problem that Java 25 PEM support addresses.
	 *
	 * @return a short problem statement
	 */
	public String manualParsingProblem() {
		return "before Java 25, applications often combined key factories, Base64, headers, footers, and parsing code by hand";
	}

	/**
	 * Explains what the Java 25 preview API provides.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "PEMEncoder and PEMDecoder convert DER-encodable cryptographic objects to and from PEM text";
	}

	/**
	 * Explains why this example uses a child process.
	 *
	 * @return the preview boundary
	 */
	public String previewBoundary() {
		return "the preview API is compiled and run in a child JVM with --enable-preview so the main Maven build stays non-preview";
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
	public record PemWorkflowResult(
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
