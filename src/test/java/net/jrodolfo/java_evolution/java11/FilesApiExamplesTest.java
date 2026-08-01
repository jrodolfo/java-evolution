package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FilesApiExamplesTest {

	private final FilesApiExamples examples = new FilesApiExamples();

	@TempDir
	Path tempDir;

	@Test
	void writeStringAndReadStringWorkWithTextFiles() throws IOException {
		// Given
		Path file = tempDir.resolve("java11.txt");

		// When
		examples.writeText(file, "Java 11 file API");
		String content = examples.readText(file);

		// Then
		assertThat(content)
				.as("Files.writeString and Files.readString should round-trip text")
				.isEqualTo("Java 11 file API");
	}
}
