package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CompactSourceFilesExamplesTest {

	private final CompactSourceFilesExamples examples = new CompactSourceFilesExamples();

	@Test
	void compactSourceFileRunsWithoutExplicitClassDeclaration(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createHelloWorldSource(workspace);

		CompactSourceFilesExamples.LaunchResult result = examples.launch(sourceFile);

		assertThat(result.exitCode())
				.as("The Java 25 launcher should run a compact source file; output was: %s", result.output())
				.isZero();
		assertThat(result.output())
				.as("A compact source file should focus on program body rather than class ceremony")
				.contains("hello from compact source");
		assertThat(Files.readString(sourceFile))
				.as("The teaching source should contain a top-level instance main method, not an explicit class")
				.contains("void main()")
				.doesNotContain("class HelloWorld");
	}

	@Test
	void compactSourceFileCanUseLauncherArguments(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createGreetingSource(workspace);

		CompactSourceFilesExamples.LaunchResult result = examples.launch(sourceFile, "Rod");

		assertThat(result.exitCode())
				.as("The Java 25 launcher should pass arguments to compact source files; output was: %s",
						result.output())
				.isZero();
		assertThat(result.output())
				.as("Instance main methods can still receive String[] args")
				.contains("hello, Rod");
		assertThat(Files.readString(sourceFile))
				.as("The example should show an instance main method with arguments")
				.contains("void main(String[] args)")
				.doesNotContain("static void main");
	}
}
