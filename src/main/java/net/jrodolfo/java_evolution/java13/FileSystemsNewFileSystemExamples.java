package net.jrodolfo.java_evolution.java13;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates the {@link FileSystems#newFileSystem(Path)} convenience overload
 * added in Java 13.
 */
public class FileSystemsNewFileSystemExamples {

	/**
	 * Opens a file system from a path, reads one file, and closes the file system.
	 *
	 * @param archivePath path to an archive supported by an installed file system
	 * provider
	 * @param fileName the file inside the archive to read
	 * @return the file content
	 * @throws IOException when the archive cannot be opened or read
	 */
	public String readTextFromArchive(Path archivePath, String fileName) throws IOException {
		try (FileSystem fileSystem = FileSystems.newFileSystem(archivePath)) {
			Path pathInsideArchive = fileSystem.getPath(fileName);
			return Files.readString(pathInsideArchive);
		}
	}
}
