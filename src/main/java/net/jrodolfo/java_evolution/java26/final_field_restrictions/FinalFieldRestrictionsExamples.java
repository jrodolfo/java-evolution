package net.jrodolfo.java_evolution.java26.final_field_restrictions;

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
 * Demonstrates Java 26 warnings for deep-reflective mutation of final fields.
 *
 * <p>
 * The example runs the reflective mutation in a child JVM. That keeps the main
 * Maven test JVM clean while still showing the real Java 26 runtime behavior:
 * final-field mutation still succeeds by default, but the runtime prints a
 * warning and points to explicit enablement for legacy code that still needs the
 * behavior.
 * </p>
 */
public class FinalFieldRestrictionsExamples {

	/**
	 * Compiles and runs a tiny program that mutates a final field reflectively.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return the child compiler and runtime results
	 * @throws IOException when generated source cannot be written or a child
	 * process cannot be started
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public MutationProbeResult runDefaultMutationProbe(Path workspace) throws IOException, InterruptedException {
		return runMutationProbe(workspace, false);
	}

	/**
	 * Runs the same final-field mutation with explicit mutation enablement.
	 *
	 * @param workspace temporary directory for generated source and class files
	 * @return the child compiler and runtime results
	 * @throws IOException when generated source cannot be written or a child
	 * process cannot be started
	 * @throws InterruptedException when waiting for a child process is interrupted
	 */
	public MutationProbeResult runExplicitlyEnabledMutationProbe(Path workspace)
			throws IOException, InterruptedException {
		return runMutationProbe(workspace, true);
	}

	/**
	 * Source for a small program that mutates a final field through reflection.
	 *
	 * @return generated source used by the child-process probe
	 */
	public String reflectiveMutationSource() {
		return """
				import java.lang.reflect.Field;

				public class FinalFieldMutationProbe {
					static final class Account {
						final String owner;

						Account(String owner) {
							this.owner = owner;
						}
					}

					public static void main(String[] args) throws Exception {
						Account account = new Account("before");
						Field field = Account.class.getDeclaredField("owner");
						field.setAccessible(true);
						field.set(account, "after");
						System.out.println("owner=" + account.owner);
					}
				}
				""";
	}

	/**
	 * Explains why final-field mutation is being restricted.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "deep reflection can mutate final fields, weakening immutability and the JVM's ability to trust final values";
	}

	/**
	 * Describes the Java 26 behavior.
	 *
	 * @return a short behavior note
	 */
	public String java26Behavior() {
		return "Java 26 issues runtime warnings when deep reflection mutates final fields";
	}

	/**
	 * Explains the migration direction.
	 *
	 * @return a short migration note
	 */
	public String migrationDirection() {
		return "applications should avoid final-field mutation or explicitly enable it only where legacy frameworks truly require it";
	}

	private MutationProbeResult runMutationProbe(Path workspace, boolean enableFinalFieldMutation)
			throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("FinalFieldMutationProbe.java");
		Files.writeString(sourceFile, reflectiveMutationSource(), StandardCharsets.UTF_8);

		CommandResult compilation = run(javacCommand(), sourceFile.toString());
		CommandResult execution = compilation.exitCode() == 0
				? run(javaCommand(workspace, enableFinalFieldMutation))
				: new CommandResult(-1, compilation.output());

		return new MutationProbeResult(sourceFile, compilation, execution);
	}

	private String javacCommand() {
		return toolCommand("javac");
	}

	private List<String> javaCommand(Path workspace, boolean enableFinalFieldMutation) {
		List<String> command = new ArrayList<String>();
		command.add(toolCommand("java"));
		if (enableFinalFieldMutation) {
			command.add("--enable-final-field-mutation=ALL-UNNAMED");
		}
		command.add("-cp");
		command.add(workspace.toString());
		command.add("FinalFieldMutationProbe");
		return command;
	}

	private String toolCommand(String tool) {
		String executable = isWindows() ? tool + ".exe" : tool;
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		List<String> arguments = new ArrayList<String>();
		for (String argument : command) {
			arguments.add(argument);
		}
		return run(arguments);
	}

	private CommandResult run(List<String> command) throws IOException, InterruptedException {
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

	/**
	 * Complete result of a final-field mutation probe.
	 *
	 * @param sourceFile generated source file
	 * @param compilation child compiler result
	 * @param execution child runtime result
	 */
	public record MutationProbeResult(Path sourceFile, CommandResult compilation, CommandResult execution) {
	}

	/**
	 * Result from a child {@code javac} or {@code java} process.
	 *
	 * @param exitCode child process exit code
	 * @param output merged standard output and standard error
	 */
	public record CommandResult(int exitCode, String output) {
	}
}
