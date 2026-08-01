package net.jrodolfo.java_evolution.java13;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileSystemsNewFileSystemExamplesTest {

	private final FileSystemsNewFileSystemExamples examples = new FileSystemsNewFileSystemExamples();

	@TempDir
	Path tempDir;

	@Test
	void newFileSystemCanOpenAnArchiveFromAPath() throws IOException {
		// Given
		Path archive = tempDir.resolve("features.zip");
		createZipArchive(archive, "java13.txt", "FileSystems.newFileSystem(Path)");

		// When
		String content = examples.readTextFromArchive(archive, "java13.txt");

		// Then
		assertThat(content)
				.as("FileSystems.newFileSystem(Path) should open the archive without a URI")
				.isEqualTo("FileSystems.newFileSystem(Path)");
	}

	private void createZipArchive(Path archive, String fileName, String content) throws IOException {
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(archive))) {
			zipOutputStream.putNextEntry(new ZipEntry(fileName));
			zipOutputStream.write(content.getBytes());
			zipOutputStream.closeEntry();
		}
	}
}
