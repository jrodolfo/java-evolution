package net.jrodolfo.java_evolution.java25;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 25 compact source files and instance main methods.
 */
public class CompactSourceFilesExamples {

	/**
	 * Creates a compact source file that can be launched directly.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the compact source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createHelloWorldSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("HelloWorld.java");
		Files.writeString(sourceFile, """
				void main() {
				    IO.println("hello from compact source");
				}
				""");
		return sourceFile;
	}

	/**
	 * Creates a compact source file that accepts launcher arguments.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the compact source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createGreetingSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("Greeting.java");
		Files.writeString(sourceFile, """
				void main(String[] args) {
				    String name = args.length == 0 ? "learner" : args[0];
				    IO.println("hello, " + name);
				}
				""");
		return sourceFile;
	}

	/**
	 * Launches a compact source file with the JDK {@code java} launcher.
	 *
	 * @param sourceFile compact source file
	 * @param arguments program arguments passed after the source file
	 * @return child-process result
	 * @throws IOException when the child JVM cannot be started
	 * @throws InterruptedException when interrupted while waiting for the child JVM
	 */
	public LaunchResult launch(Path sourceFile, String... arguments) throws IOException, InterruptedException {
		List<String> command = new ArrayList<String>();
		command.add(javaExecutable());
		command.add(sourceFile.toString());
		for (String argument : arguments) {
			command.add(argument);
		}

		Process process = new ProcessBuilder(command)
				.directory(sourceFile.getParent().toFile())
				.redirectErrorStream(true)
				.start();

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new LaunchResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new LaunchResult(process.exitValue(), output);
	}

	private String javaExecutable() {
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	}

	/**
	 * Captures the compact source launcher result.
	 */
	public static final class LaunchResult {

		private final int exitCode;
		private final String output;

		private LaunchResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		/**
		 * @return child process exit code
		 */
		public int exitCode() {
			return exitCode;
		}

		/**
		 * @return merged standard output and standard error
		 */
		public String output() {
			return output;
		}
	}
}
