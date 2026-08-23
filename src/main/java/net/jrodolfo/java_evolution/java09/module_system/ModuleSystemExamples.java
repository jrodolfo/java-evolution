package net.jrodolfo.java_evolution.java09.module_system;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Demonstrates the Java Platform Module System (JPMS), introduced in Java 9.
 */
public class ModuleSystemExamples {

	/**
	 * Creates a small modular source tree with one library module and one
	 * application module.
	 *
	 * @param sourceRoot root directory for the module source tree
	 * @throws IOException when source files cannot be written
	 */
	public void createModularSourceTree(Path sourceRoot) throws IOException {
		createGreetingsModule(sourceRoot);
		Files.createDirectories(sourceRoot.resolve("com.example.app/com/example/app"));
		write(sourceRoot.resolve("com.example.app/module-info.java"),
				"module com.example.app {\n"
						+ "    requires com.example.greetings;\n"
						+ "}\n");
		write(sourceRoot.resolve("com.example.app/com/example/app/Main.java"),
				"package com.example.app;\n"
						+ "\n"
						+ "import com.example.greetings.GreetingService;\n"
						+ "\n"
						+ "public class Main {\n"
						+ "    public static void main(String[] args) {\n"
						+ "        System.out.println(new GreetingService().message());\n"
						+ "    }\n"
						+ "}\n");
	}

	/**
	 * Creates a modular source tree where the app module incorrectly imports an
	 * unexported package from the greetings module.
	 *
	 * @param sourceRoot root directory for the module source tree
	 * @throws IOException when source files cannot be written
	 */
	public void createSourceTreeWithInternalPackageAccess(Path sourceRoot) throws IOException {
		createGreetingsModule(sourceRoot);
		Files.createDirectories(sourceRoot.resolve("com.example.app/com/example/app"));
		write(sourceRoot.resolve("com.example.app/module-info.java"),
				"module com.example.app {\n"
						+ "    requires com.example.greetings;\n"
						+ "}\n");
		write(sourceRoot.resolve("com.example.app/com/example/app/Main.java"),
				"package com.example.app;\n"
						+ "\n"
						+ "import com.example.greetings.internal.InternalFormatter;\n"
						+ "\n"
						+ "public class Main {\n"
						+ "    public static void main(String[] args) {\n"
						+ "        System.out.println(new InternalFormatter().format(\"hello from module\"));\n"
						+ "    }\n"
						+ "}\n");
	}

	/**
	 * Compiles all module source files under a module source root.
	 *
	 * @param sourceRoot root containing module directories
	 * @param moduleOutputDirectory output directory for compiled modules
	 * @return compiler process result
	 * @throws IOException when the compiler cannot be started or source files cannot
	 *         be listed
	 * @throws InterruptedException when interrupted while waiting for the compiler
	 */
	public CommandResult compileModules(Path sourceRoot, Path moduleOutputDirectory)
			throws IOException, InterruptedException {
		Files.createDirectories(moduleOutputDirectory);

		List<String> command = new ArrayList<String>();
		command.add(javacExecutable());
		command.add("--module-source-path");
		command.add(sourceRoot.toString());
		command.add("-d");
		command.add(moduleOutputDirectory.toString());
		command.addAll(javaSourceFiles(sourceRoot));

		return run(command, sourceRoot);
	}

	/**
	 * Runs the modular application.
	 *
	 * @param moduleOutputDirectory directory containing compiled modules
	 * @return Java process result
	 * @throws IOException when the child JVM cannot be started
	 * @throws InterruptedException when interrupted while waiting for the child JVM
	 */
	public CommandResult runApplicationModule(Path moduleOutputDirectory) throws IOException, InterruptedException {
		List<String> command = new ArrayList<String>();
		command.add(javaExecutable());
		command.add("--module-path");
		command.add(moduleOutputDirectory.toString());
		command.add("-m");
		command.add("com.example.app/com.example.app.Main");

		return run(command, moduleOutputDirectory);
	}

	private void createGreetingsModule(Path sourceRoot) throws IOException {
		Files.createDirectories(sourceRoot.resolve("com.example.greetings/com/example/greetings/internal"));
		write(sourceRoot.resolve("com.example.greetings/module-info.java"),
				"module com.example.greetings {\n"
						+ "    exports com.example.greetings;\n"
						+ "}\n");
		write(sourceRoot.resolve("com.example.greetings/com/example/greetings/GreetingService.java"),
				"package com.example.greetings;\n"
						+ "\n"
						+ "import com.example.greetings.internal.InternalFormatter;\n"
						+ "\n"
						+ "public class GreetingService {\n"
						+ "    public String message() {\n"
						+ "        return new InternalFormatter().format(\"hello from module\");\n"
						+ "    }\n"
						+ "}\n");
		write(sourceRoot.resolve("com.example.greetings/com/example/greetings/internal/InternalFormatter.java"),
				"package com.example.greetings.internal;\n"
						+ "\n"
						+ "public class InternalFormatter {\n"
						+ "    public String format(String value) {\n"
						+ "        return value;\n"
						+ "    }\n"
						+ "}\n");
	}

	private List<String> javaSourceFiles(Path sourceRoot) throws IOException {
		List<String> sourceFiles = new ArrayList<String>();
		try (Stream<Path> paths = Files.walk(sourceRoot)) {
			paths.filter(path -> path.toString().endsWith(".java"))
					.sorted()
					.forEach(path -> sourceFiles.add(path.toString()));
		}
		return sourceFiles;
	}

	private CommandResult run(List<String> command, Path workingDirectory) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command)
				.directory(workingDirectory.toFile())
				.redirectErrorStream(true)
				.start();

		boolean finished = process.waitFor(15, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new CommandResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new CommandResult(process.exitValue(), output);
	}

	private void write(Path file, String content) throws IOException {
		Files.createDirectories(file.getParent());
		Files.write(file, content.getBytes(StandardCharsets.UTF_8));
	}

	private String javaExecutable() {
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	}

	private String javacExecutable() {
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "javac";
	}

	/**
	 * Captures child process output.
	 */
	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
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
