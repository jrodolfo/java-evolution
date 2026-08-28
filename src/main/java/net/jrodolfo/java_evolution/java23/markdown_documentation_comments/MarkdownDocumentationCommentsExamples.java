package net.jrodolfo.java_evolution.java23.markdown_documentation_comments;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 23 Markdown documentation comments by invoking JavaDoc.
 */
public class MarkdownDocumentationCommentsExamples {

	/**
	 * Creates a Java source file that uses Markdown documentation comments.
	 *
	 * @param sourceDirectory directory where the source file should be written
	 * @return path to the generated source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createDocumentedSource(Path sourceDirectory) throws IOException {
		Files.createDirectories(sourceDirectory);
		Path sourceFile = sourceDirectory.resolve("DocumentedAccount.java");
		Files.writeString(sourceFile, """
				/// # Documented Account
				///
				/// Represents a small account example.
				///
				/// ## Rules
				///
				/// - The owner must not be blank.
				/// - The account must stay active.
				///
				/// Example:
				///
				/// ```java
				/// DocumentedAccount account = new DocumentedAccount("Rod");
				/// ```
				public class DocumentedAccount {
					private final String owner;

					/// Creates an account for `owner`.
					public DocumentedAccount(String owner) {
						this.owner = owner;
					}

					/// Returns the account owner.
					public String owner() {
						return owner;
					}
				}
				""");
		return sourceFile;
	}

	/**
	 * Runs the JavaDoc tool for one source file.
	 *
	 * @param sourceFile Java source file to document
	 * @param outputDirectory JavaDoc output directory
	 * @return child-process result
	 * @throws IOException when the JavaDoc process cannot be started
	 * @throws InterruptedException when interrupted while waiting for JavaDoc
	 */
	public JavaDocResult generateJavaDoc(Path sourceFile, Path outputDirectory)
			throws IOException, InterruptedException {
		Files.createDirectories(outputDirectory);
		Process process = new ProcessBuilder(javadocExecutable(), "-d", outputDirectory.toString(),
				sourceFile.toString())
				.redirectErrorStream(true)
				.start();

		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new JavaDocResult(-1, output.join(), outputDirectory);
		}

		return new JavaDocResult(process.exitValue(), output.join(), outputDirectory);
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	private String javadocExecutable() {
		String executable = isWindows() ? "javadoc.exe" : "javadoc";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	/**
	 * Captures the JavaDoc child process result.
	 *
	 * @param exitCode process exit code
	 * @param output merged standard output and standard error
	 * @param documentationDirectory generated documentation directory
	 */
	public record JavaDocResult(int exitCode, String output, Path documentationDirectory) {
	}
}
