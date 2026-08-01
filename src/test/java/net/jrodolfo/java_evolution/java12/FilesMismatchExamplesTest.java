package net.jrodolfo.java_evolution.java12;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FilesMismatchExamplesTest {

	private final FilesMismatchExamples examples = new FilesMismatchExamples();

	@TempDir
	Path tempDir;

	@Test
	void mismatchReturnsMinusOneForEqualFiles() throws IOException {
		// Given
		Path first = tempDir.resolve("first.txt");
		Path second = tempDir.resolve("second.txt");
		Files.writeString(first, "same content");
		Files.writeString(second, "same content");

		// When
		long mismatch = examples.firstMismatchPosition(first, second);

		// Then
		assertThat(mismatch)
				.as("Files.mismatch should return -1 when both files have identical content")
				.isEqualTo(-1);
	}

	@Test
	void mismatchReturnsFirstDifferentBytePosition() throws IOException {
		// Given
		Path first = tempDir.resolve("first.txt");
		Path second = tempDir.resolve("second.txt");
		Files.writeString(first, "java12");
		Files.writeString(second, "java13");

		// When
		long mismatch = examples.firstMismatchPosition(first, second);

		// Then
		assertThat(mismatch)
				.as("Files.mismatch should return the first zero-based byte position that differs")
				.isEqualTo(5);
	}
}
