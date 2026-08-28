package net.jrodolfo.java_evolution.java22.launch_multi_file_source_programs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 22 multi-file source-program launching.
 */
public class LaunchMultiFileSourceProgramsExamples {

	/**
	 * Creates a small source tree where {@code Main.java} uses helper source files.
	 *
	 * @param directory directory where source files should be created
	 * @throws IOException when the source tree cannot be written
	 */
	public void createGreetingSourceTree(Path directory) throws IOException {
		Files.createDirectories(directory.resolve("messages"));
		Files.writeString(directory.resolve("Main.java"), """
				import messages.MessageFormatter;

				public class Main {
					public static void main(String[] args) {
						String name = args.length == 0 ? "learner" : args[0];
						Greeting greeting = new Greeting(new MessageFormatter());
						System.out.println(greeting.messageFor(name));
					}
				}
				""");
		Files.writeString(directory.resolve("Greeting.java"), """
				import messages.MessageFormatter;

				public class Greeting {
					private final MessageFormatter formatter;

					public Greeting(MessageFormatter formatter) {
						this.formatter = formatter;
					}

					public String messageFor(String name) {
						return formatter.format("hello", name);
					}
				}
				""");
		Files.writeString(directory.resolve("messages/MessageFormatter.java"), """
				package messages;

				public class MessageFormatter {
					public String format(String greeting, String name) {
						return greeting + ", " + name;
					}
				}
				""");
	}

	/**
	 * Creates a source tree that should fail because {@code Main.java} references a
	 * missing helper type.
	 *
	 * @param directory directory where source files should be created
	 * @throws IOException when the source tree cannot be written
	 */
	public void createBrokenSourceTree(Path directory) throws IOException {
		Files.createDirectories(directory);
		Files.writeString(directory.resolve("Main.java"), """
				public class Main {
					public static void main(String[] args) {
						System.out.println(new MissingGreeting().message());
					}
				}
				""");
	}

	/**
	 * Runs {@code java Main.java} in the supplied source directory.
	 *
	 * @param sourceDirectory directory containing {@code Main.java}
	 * @param arguments application arguments passed after {@code Main.java}
	 * @return child-process result
	 * @throws IOException when the child JVM cannot be started
	 * @throws InterruptedException when interrupted while waiting for the child JVM
	 */
	public LaunchResult launchMain(Path sourceDirectory, String... arguments) throws IOException, InterruptedException {
		List<String> command = new ArrayList<>();
		command.add(javaExecutable());
		command.add("Main.java");
		for (String argument : arguments) {
			command.add(argument);
		}

		Process process = new ProcessBuilder(command)
				.directory(sourceDirectory.toFile())
				.redirectErrorStream(true)
				.start();

		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new LaunchResult(-1, output.join());
		}
		return new LaunchResult(process.exitValue(), output.join());
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	private String javaExecutable() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	/**
	 * Captures the launcher child process result.
	 *
	 * @param exitCode process exit code
	 * @param output merged standard output and standard error
	 */
	public record LaunchResult(int exitCode, String output) {
	}
}
