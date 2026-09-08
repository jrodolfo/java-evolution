package net.jrodolfo.java_evolution.java02.strict_floating_point;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

	public CompilationResult compileStrictfpProbe(File workspace) throws IOException, InterruptedException {
		File sourceFile = new File(workspace, "StrictfpProbe.java");
		FileOutputStream output = new FileOutputStream(sourceFile);
		try {
			output.write(strictfpProbeSource().getBytes());
		}
		finally {
			output.close();
		}

		return run(javacCommand(), sourceFile.getPath());
	}

	public String strictfpProbeSource() {
		return "public strictfp class StrictfpProbe {\n"
				+ "    public strictfp double average(double left, double right) {\n"
				+ "        return (left + right) / 2.0d;\n"
				+ "    }\n"
				+ "}\n";
	}

	private String javacCommand() {
		String executable = isWindows() ? "javac.exe" : "javac";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}

	private CompilationResult run(String... command) throws IOException, InterruptedException {
		final Process process = Runtime.getRuntime().exec(command);
		final StringBuffer output = new StringBuffer();
		Thread standardOutputReader = new Thread(new Runnable() {
			public void run() {
				readOutput(process.getInputStream(), output);
			}
		});
		Thread errorOutputReader = new Thread(new Runnable() {
			public void run() {
				readOutput(process.getErrorStream(), output);
			}
		});
		standardOutputReader.start();
		errorOutputReader.start();
		int exitCode = process.waitFor();
		standardOutputReader.join();
		errorOutputReader.join();
		return new CompilationResult(exitCode, output.toString());
	}

	private void readOutput(InputStream input, StringBuffer output) {
		try {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			int read;
			while ((read = input.read(buffer)) != -1) {
				bytes.write(buffer, 0, read);
			}
			output.append(new String(bytes.toByteArray()));
		}
		catch (IOException exception) {
			output.append("could not read child process output: ").append(exception);
		}
	}

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
