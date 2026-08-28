package net.jrodolfo.java_evolution.java23.unsafe_memory_access_deprecation;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 23's migration pressure away from {@code sun.misc.Unsafe}
 * memory-access methods.
 *
 * <p>
 * The main project source deliberately does not import {@code sun.misc.Unsafe}.
 * Instead, this example writes a tiny child source file, compiles it with
 * {@code javac -Xlint:removal}, and captures the deprecation-for-removal
 * diagnostics. It also runs the child class with
 * {@code --sun-misc-unsafe-memory-access=deny} to show the runtime boundary.
 * </p>
 */
public class UnsafeMemoryAccessDeprecationExamples {

	private static final String PROBE_CLASS_NAME = "UnsafeMemoryAccessProbe";
	private static final String UNSAFE_PROBE_SOURCE = """
			import java.lang.reflect.Field;
			import sun.misc.Unsafe;

			public class UnsafeMemoryAccessProbe {
				static final class Holder {
					int value = 7;
				}

				public static void main(String[] args) throws Exception {
					Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
					unsafeField.setAccessible(true);
					Unsafe unsafe = (Unsafe) unsafeField.get(null);
					long offset = unsafe.objectFieldOffset(Holder.class.getDeclaredField("value"));
					System.out.println("value=" + unsafe.getInt(new Holder(), offset));
				}
			}
			""";

	/**
	 * Writes the generated child source used only for migration-boundary testing.
	 *
	 * @param sourceDirectory directory where the probe source should be written
	 * @return path to the generated source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createUnsafeMemoryAccessProbe(Path sourceDirectory) throws IOException {
		Files.createDirectories(sourceDirectory);
		Path sourceFile = sourceDirectory.resolve(PROBE_CLASS_NAME + ".java");
		Files.writeString(sourceFile, UNSAFE_PROBE_SOURCE, StandardCharsets.UTF_8);
		return sourceFile;
	}

	/**
	 * Compiles the generated child source and captures compiler diagnostics.
	 *
	 * @param workDirectory directory where source and class files should be created
	 * @return the child process result
	 * @throws IOException when files cannot be written or {@code javac} cannot be started
	 * @throws InterruptedException when interrupted while waiting for {@code javac}
	 */
	public ProcessResult compileUnsafeMemoryAccessProbe(Path workDirectory) throws IOException, InterruptedException {
		Path sourceDirectory = workDirectory.resolve("src");
		Path classesDirectory = workDirectory.resolve("classes");
		Path sourceFile = createUnsafeMemoryAccessProbe(sourceDirectory);
		Files.createDirectories(classesDirectory);

		List<String> command = new ArrayList<>();
		command.add(javacExecutable());
		command.add("-Xlint:removal");
		command.add("-d");
		command.add(classesDirectory.toString());
		command.add(sourceFile.toString());

		return run(command, workDirectory);
	}

	/**
	 * Runs the generated child class with unsafe memory access denied.
	 *
	 * @param workDirectory directory where source and class files should be created
	 * @return the child process result
	 * @throws IOException when files cannot be written or the child JVM cannot be started
	 * @throws InterruptedException when interrupted while waiting for the child JVM
	 */
	public ProcessResult runUnsafeProbeWithDeny(Path workDirectory) throws IOException, InterruptedException {
		ProcessResult compilation = compileUnsafeMemoryAccessProbe(workDirectory);
		if (compilation.exitCode() != 0) {
			return compilation;
		}

		List<String> command = new ArrayList<>();
		command.add(javaExecutable());
		command.add("--sun-misc-unsafe-memory-access=deny");
		command.add("-cp");
		command.add(workDirectory.resolve("classes").toString());
		command.add(PROBE_CLASS_NAME);

		return run(command, workDirectory);
	}

	/**
	 * Demonstrates the supported replacement direction for ordinary variable access.
	 *
	 * @param replacementValue value to store through a {@link VarHandle}
	 * @return the value read back through the same supported API
	 * @throws ReflectiveOperationException when the field handle cannot be created
	 */
	public int updateWithVarHandle(int replacementValue) throws ReflectiveOperationException {
		var holder = new Holder();
		VarHandle valueHandle = MethodHandles.lookup().findVarHandle(Holder.class, "value", int.class);
		valueHandle.set(holder, replacementValue);
		return (int) valueHandle.get(holder);
	}

	/**
	 * Explains why {@code sun.misc.Unsafe} appears only in generated child source.
	 *
	 * @return a short boundary explanation
	 */
	public String exampleBoundary() {
		return "Unsafe appears only in generated child source so the example can capture migration diagnostics without teaching new Unsafe usage";
	}

	/**
	 * Explains the migration lesson.
	 *
	 * @return a short migration lesson
	 */
	public String migrationLesson() {
		return "deprecated-for-removal Unsafe memory access should move toward supported APIs such as VarHandle";
	}

	private ProcessResult run(List<String> command, Path workDirectory) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command)
				.directory(workDirectory.toFile())
				.redirectErrorStream(true)
				.start();

		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new ProcessResult(-1, output.join());
		}
		return new ProcessResult(process.exitValue(), output.join());
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
		return toolExecutable("java");
	}

	private String javacExecutable() {
		return toolExecutable("javac");
	}

	private String toolExecutable(String tool) {
		String executable = isWindows() ? tool + ".exe" : tool;
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private static final class Holder {
		private int value = 7;
	}

	/**
	 * Captures a child process result.
	 *
	 * @param exitCode process exit code
	 * @param output merged standard output and standard error
	 */
	public record ProcessResult(int exitCode, String output) {
	}
}
