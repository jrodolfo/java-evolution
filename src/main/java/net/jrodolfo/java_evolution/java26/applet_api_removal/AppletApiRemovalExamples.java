package net.jrodolfo.java_evolution.java26.applet_api_removal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates removal of the Applet API in Java 26.
 *
 * <p>
 * This example does not try to run an applet or restore browser-plugin-era
 * tooling. Instead, it writes a tiny legacy source file that imports
 * {@code java.applet.Applet}, compiles it with the current JDK, and captures the
 * compiler failure. That makes the removal visible without depending on old
 * browser integrations or obsolete platform modules.
 * </p>
 */
public class AppletApiRemovalExamples {

	/**
	 * Compiles a tiny obsolete applet source file with the active JDK.
	 *
	 * @param workspace temporary directory for generated source files
	 * @return compilation result containing exit code and compiler output
	 * @throws IOException when the source file cannot be written or {@code javac}
	 * cannot be started
	 * @throws InterruptedException when waiting for the child compiler is
	 * interrupted
	 */
	public CompilationResult compileLegacyApplet(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("LegacyApplet.java");
		Files.writeString(sourceFile, legacyAppletSource(), StandardCharsets.UTF_8);

		CommandResult commandResult = run(javacCommand(), sourceFile.toString());
		return new CompilationResult(sourceFile, commandResult.exitCode(), commandResult.output());
	}

	/**
	 * Source that would compile only on JDKs where {@code java.applet.Applet}
	 * still existed.
	 *
	 * @return tiny obsolete applet source
	 */
	public String legacyAppletSource() {
		return """
				import java.applet.Applet;

				public class LegacyApplet extends Applet {
				}
				""";
	}

	/**
	 * Explains what applets represented historically.
	 *
	 * @return a short historical note
	 */
	public String historicalContext() {
		return "applets were browser-embedded Java programs from the plugin era of the web";
	}

	/**
	 * Describes the Java 26 change.
	 *
	 * @return a short removal note
	 */
	public String java26Change() {
		return "Java 26 removes the Applet API after a long deprecation-for-removal period";
	}

	/**
	 * Explains the learning value.
	 *
	 * @return a short migration note
	 */
	public String migrationLesson() {
		return "removed APIs show why deprecation-for-removal warnings should be treated as real migration signals";
	}

	private String javacCommand() {
		String executable = isWindows() ? "javac.exe" : "javac";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command)
				.redirectErrorStream(true)
				.start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
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

	private record CommandResult(int exitCode, String output) {
	}

	/**
	 * Result from compiling obsolete applet source.
	 *
	 * @param sourceFile generated source file
	 * @param exitCode child {@code javac} exit code
	 * @param output merged standard output and standard error
	 */
	public record CompilationResult(Path sourceFile, int exitCode, String output) {
	}
}
