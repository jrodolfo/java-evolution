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
 * Demonstrates module import declarations, finalized in Java 25.
 *
 * <p>
 * Module import declarations are source-level syntax, so this example writes
 * temporary source files and runs the JDK tools against them instead of placing
 * {@code import module ...;} declarations in this repository's ordinary Maven
 * package tree.
 * </p>
 */
public class ModuleImportDeclarationsExamples {

	/**
	 * Creates a source file that imports {@code java.base} and uses types from
	 * several exported packages without ordinary imports.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createJavaBaseModuleImportSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("BaseModuleImports.java");
		Files.writeString(sourceFile, """
				import module java.base;

				public class BaseModuleImports {
				    public static void main(String[] args) {
				        List<String> names = List.of("java", "module", "imports");
				        Optional<String> firstLongName = names.stream()
				                .filter(name -> name.length() > 4)
				                .findFirst();
				        String summary = names.stream()
				                .map(String::toUpperCase)
				                .collect(Collectors.joining("|"));
				        System.out.println(summary);
				        System.out.println(firstLongName.orElse("missing"));
				    }
				}
				""");
		return sourceFile;
	}

	/**
	 * Creates the same source shape without imports, which should fail to compile.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createSourceWithoutImports(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("MissingImports.java");
		Files.writeString(sourceFile, """
				public class MissingImports {
				    public static void main(String[] args) {
				        List<String> names = List.of("java", "module", "imports");
				        String summary = names.stream().collect(Collectors.joining("|"));
				        System.out.println(summary);
				    }
				}
				""");
		return sourceFile;
	}

	/**
	 * Creates a source file that combines module imports from {@code java.base} and
	 * {@code java.net.http}.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createHttpModuleImportSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("HttpModuleImports.java");
		Files.writeString(sourceFile, """
				import module java.base;
				import module java.net.http;

				public class HttpModuleImports {
				    public static void main(String[] args) {
				        HttpClient client = HttpClient.newHttpClient();
				        HttpRequest request = HttpRequest.newBuilder(URI.create("https://example.com/java25"))
				                .GET()
				                .build();
				        System.out.println(request.method());
				        System.out.println(request.uri().getHost());
				        System.out.println(client.getClass().getName().contains("HttpClient"));
				    }
				}
				""");
		return sourceFile;
	}

	/**
	 * Creates a source file where broad module imports produce a simple-name
	 * ambiguity.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createAmbiguousDateSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("AmbiguousModuleImports.java");
		Files.writeString(sourceFile, """
				import module java.base;
				import module java.sql;

				public class AmbiguousModuleImports {
				    private Date date;
				}
				""");
		return sourceFile;
	}

	/**
	 * Compiles a generated source file.
	 *
	 * @param sourceFile source file to compile
	 * @param outputDirectory destination for compiled classes
	 * @return command result
	 * @throws IOException when the compiler cannot be started
	 * @throws InterruptedException when interrupted while waiting for the compiler
	 */
	public CommandResult compile(Path sourceFile, Path outputDirectory) throws IOException, InterruptedException {
		Files.createDirectories(outputDirectory);
		List<String> command = new ArrayList<String>();
		command.add(javacExecutable());
		command.add("-d");
		command.add(outputDirectory.toString());
		command.add(sourceFile.toString());
		return run(command, sourceFile.getParent());
	}

	/**
	 * Launches a generated source file directly with the JDK {@code java} launcher.
	 *
	 * @param sourceFile source file to launch
	 * @return command result
	 * @throws IOException when the launcher cannot be started
	 * @throws InterruptedException when interrupted while waiting for the launcher
	 */
	public CommandResult launch(Path sourceFile) throws IOException, InterruptedException {
		List<String> command = new ArrayList<String>();
		command.add(javaExecutable());
		command.add(sourceFile.toString());
		return run(command, sourceFile.getParent());
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
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	}

	private String javacExecutable() {
		return System.getProperty("java.home") + File.separator + "bin" + File.separator + "javac";
	}

	/**
	 * Captures JDK tool output.
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
