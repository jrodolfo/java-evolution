package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class Nio2ExamplesTest {

	private final Nio2Examples examples = new Nio2Examples();

	@TempDir
	Path tempDir;

	@Test
	void pathResolvesChildWithoutStringConcatenation() {
		Path resolved = examples.resolveChild(tempDir, "java07.txt");

		assertThat(resolved)
				.as("Path.resolve should create child paths without manual separator handling")
				.isEqualTo(tempDir.resolve("java07.txt"));
	}

	@Test
	void filesApiReadsBasicFileAttributes() throws IOException {
		Path file = tempDir.resolve("feature.txt");
		Files.write(file, "NIO.2".getBytes(StandardCharsets.UTF_8));

		assertThat(examples.fileSize(file))
				.as("Files.readAttributes should expose basic filesystem metadata")
				.isEqualTo(5);
	}

	@Test
	void filesListCanFindRegularFileNames() throws IOException {
		Files.write(tempDir.resolve("b.txt"), "b".getBytes(StandardCharsets.UTF_8));
		Files.write(tempDir.resolve("a.txt"), "a".getBytes(StandardCharsets.UTF_8));
		Files.createDirectory(tempDir.resolve("nested"));

		List<String> fileNames = examples.regularFileNames(tempDir);

		assertThat(fileNames)
				.as("Files.newDirectoryStream should expose Path entries that can be filtered and sorted")
				.containsExactly("a.txt", "b.txt");
	}
}
