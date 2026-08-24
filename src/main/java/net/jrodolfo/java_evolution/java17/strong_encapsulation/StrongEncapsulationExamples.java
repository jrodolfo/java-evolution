package net.jrodolfo.java_evolution.java17.strong_encapsulation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 17 strong encapsulation with isolated child JVMs.
 *
 * <p>
 * The child program deliberately attempts deep reflection into
 * {@code java.lang.String}. That is bad production practice, but it is a
 * faithful way to show what strong encapsulation blocks and how targeted
 * migration flags behave. Running the probe in a child JVM keeps those command
 * line flags out of the Maven test JVM.
 * </p>
 */
public class StrongEncapsulationExamples {

	private static final String ADD_OPENS = "--add-opens";
	private static final String ADD_EXPORTS = "--add-exports";
	private static final String JAVA_LANG_TO_UNNAMED = "java.base/java.lang=ALL-UNNAMED";
	private static final String ILLEGAL_ACCESS_PERMIT = "--illegal-access=permit";

	/**
	 * Runs the probe without module-opening flags.
	 *
	 * @param workspace directory for the temporary probe source
	 * @return child JVM result
	 * @throws IOException when source creation or process launch fails
	 * @throws InterruptedException when interrupted while waiting
	 */
	public CommandResult runWithoutOpens(Path workspace) throws IOException, InterruptedException {
		return runProbe(workspace);
	}

	/**
	 * Runs the probe with a targeted {@code --add-opens} migration flag.
	 *
	 * @param workspace directory for the temporary probe source
	 * @return child JVM result
	 * @throws IOException when source creation or process launch fails
	 * @throws InterruptedException when interrupted while waiting
	 */
	public CommandResult runWithAddOpens(Path workspace) throws IOException, InterruptedException {
		return runProbe(workspace, ADD_OPENS, JAVA_LANG_TO_UNNAMED);
	}

	/**
	 * Runs the probe with {@code --add-exports} only.
	 *
	 * @param workspace directory for the temporary probe source
	 * @return child JVM result
	 * @throws IOException when source creation or process launch fails
	 * @throws InterruptedException when interrupted while waiting
	 */
	public CommandResult runWithAddExportsOnly(Path workspace) throws IOException, InterruptedException {
		return runProbe(workspace, ADD_EXPORTS, JAVA_LANG_TO_UNNAMED);
	}

	/**
	 * Runs the probe with the broad {@code --illegal-access=permit} flag that is
	 * obsolete after Java 17.
	 *
	 * @param workspace directory for the temporary probe source
	 * @return child JVM result
	 * @throws IOException when source creation or process launch fails
	 * @throws InterruptedException when interrupted while waiting
	 */
	public CommandResult runWithIllegalAccessPermit(Path workspace) throws IOException, InterruptedException {
		return runProbe(workspace, ILLEGAL_ACCESS_PERMIT);
	}

	/**
	 * Returns the source code executed by the child JVM.
	 *
	 * @return probe source
	 */
	public String probeSource() {
		return """
				import java.lang.reflect.Field;
				import java.lang.reflect.Modifier;

				public class StrongEncapsulationProbe {
				    public static void main(String[] args) throws Exception {
				        Field privateField = null;
				        for (Field field : String.class.getDeclaredFields()) {
				            if (Modifier.isPublic(field.getModifiers()) == false) {
				                privateField = field;
				                break;
				            }
				        }
				        if (privateField == null) {
				            throw new IllegalStateException("no private String field found");
				        }
				        System.out.println("field=" + privateField.getName());
				        privateField.setAccessible(true);
				        System.out.println("accessible=true");
				    }
				}
				""";
	}

	private CommandResult runProbe(Path workspace, String... flags) throws IOException, InterruptedException {
		Files.createDirectories(workspace);
		Path sourceFile = workspace.resolve("StrongEncapsulationProbe.java");
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		List<String> command = new ArrayList<String>();
		command.add(javaExecutable());
		for (String flag : flags) {
			command.add(flag);
		}
		command.add(sourceFile.toString());
		return run(command, workspace);
	}

	private CommandResult run(List<String> command, Path workingDirectory) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command)
				.directory(workingDirectory.toFile())
				.redirectErrorStream(true)
				.start();

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new CommandResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new CommandResult(process.exitValue(), output);
	}

	private String javaExecutable() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	/**
	 * Captures child JVM output.
	 */
	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		/**
		 * @return process exit code
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
