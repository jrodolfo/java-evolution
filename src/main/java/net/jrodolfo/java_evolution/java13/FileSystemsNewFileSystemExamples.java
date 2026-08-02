package net.jrodolfo.java_evolution.java13;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates the {@link FileSystems#newFileSystem(Path)} convenience overload
 * added in Java 13.
 *
 * <p>
 * Before Java 13, opening an archive or other provider-backed path as a file
 * system required more verbose overloads. That added ceremony to the common
 * case where the path itself contained enough information.
 * </p>
 *
 * <p>
 * The Java 13 overload solves that convenience problem by letting code open a
 * supported file system directly from a {@link Path}.
 * </p>
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
