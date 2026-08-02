package net.jrodolfo.java_evolution.java12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates {@link Files#mismatch(Path, Path)}, introduced in Java 12.
 *
 * <p>
 * Before Java 12, finding the first byte where two files differed required
 * manual byte comparison or an external tool. That was too much plumbing for a
 * common diagnostic and testing task.
 * </p>
 *
 * <p>
 * {@code Files.mismatch} solves this by returning {@code -1} when files match
 * or the first differing byte position when they do not.
 * </p>
 */
public class FilesMismatchExamples {

	/**
	 * Finds the first byte position where two files differ.
	 *
	 * @param first the first file
	 * @param second the second file
	 * @return {@code -1} when files match, otherwise the first mismatch position
	 * @throws IOException when file access fails
	 */
	public long firstMismatchPosition(Path first, Path second) throws IOException {
		return Files.mismatch(first, second);
	}
}
