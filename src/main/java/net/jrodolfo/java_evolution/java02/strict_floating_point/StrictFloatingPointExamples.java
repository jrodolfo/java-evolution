package net.jrodolfo.java_evolution.java02.strict_floating_point;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates {@code strictfp}, introduced in Java 2.
 */
public class StrictFloatingPointExamples {

	public String problemSolved() {
		return "floating-point calculations could differ when processors used wider intermediate precision";
	}

	public String featureShape() {
		return "strictfp could be applied to classes, interfaces, or methods to request strict floating-point semantics";
	}

	public String modernContext() {
		return "Java 17 restored always-strict floating-point semantics, so modern JDKs treat strictfp as unnecessary";
	}

	public double strictAverage(double left, double right) {
		return StrictCalculator.average(left, right);
	}

	public CompilationResult compileStrictfpProbe(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("StrictfpProbe.java");
		Files.write(sourceFile, strictfpProbeSource().getBytes(StandardCharsets.UTF_8));

		return run(javacCommand(), sourceFile.toString());
	}

	public String strictfpProbeSource() {
		return """
				public strictfp class StrictfpProbe {
				    public strictfp double average(double left, double right) {
				        return (left + right) / 2.0d;
				    }
				}
				""";
	}

	private String javacCommand() {
		String executable = isWindows() ? "javac.exe" : "javac";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CompilationResult run(String... command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new CompilationResult(-1, output.join());
		}

		return new CompilationResult(process.exitValue(), output.join());
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	@SuppressWarnings("strictfp")
	private static strictfp class StrictCalculator {

		private StrictCalculator() {
		}

		static double average(double left, double right) {
			return (left + right) / 2.0d;
		}
	}

	public static final class CompilationResult {

		private final int exitCode;
		private final String output;

		private CompilationResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		public int exitCode() {
			return exitCode;
		}

		public String output() {
			return output;
		}
	}
}
