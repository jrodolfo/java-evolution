package net.jrodolfo.java_evolution.java07;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demonstrates NIO.2 filesystem APIs introduced in Java 7.
 */
public class Nio2Examples {

	/**
	 * Resolves a child path from a root path.
	 *
	 * @param root root directory
	 * @param child child name
	 * @return resolved path
	 */
	public Path resolveChild(Path root, String child) {
		return root.resolve(child);
	}

	/**
	 * Reads basic file attributes through {@link Files}.
	 *
	 * @param path file to inspect
	 * @return size in bytes
	 * @throws IOException when attributes cannot be read
	 */
	public long fileSize(Path path) throws IOException {
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		return attributes.size();
	}

	/**
	 * Lists regular file names in a directory.
	 *
	 * @param directory directory to inspect
	 * @return sorted regular file names
	 * @throws IOException when listing fails
	 */
	public List<String> regularFileNames(Path directory) throws IOException {
		List<String> names = new ArrayList<>();

		try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
			for (Path path : paths) {
				if (Files.isRegularFile(path)) {
					names.add(path.getFileName().toString());
				}
			}
		}

		Collections.sort(names);
		return names;
	}
}
