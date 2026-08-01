package net.jrodolfo.java_evolution.java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates file convenience methods introduced in Java 11.
 */
public class FilesApiExamples {

	/**
	 * Writes text to a file using {@link Files#writeString(Path, CharSequence,
	 * java.nio.file.OpenOption...)}.
	 *
	 * @param path the target path
	 * @param content the text to write
	 * @throws IOException when writing fails
	 */
	public void writeText(Path path, String content) throws IOException {
		Files.writeString(path, content);
	}

	/**
	 * Reads text from a file using {@link Files#readString(Path)}.
	 *
	 * @param path the source path
	 * @return the file content
	 * @throws IOException when reading fails
	 */
	public String readText(Path path) throws IOException {
		return Files.readString(path);
	}
}
