package net.jrodolfo.java_evolution.java09;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Demonstrates the Java 9 try-with-resources improvement.
 *
 * <p>
 * Java 7 introduced try-with-resources, but a resource usually had to be
 * declared inside the {@code try (...)} block. When a resource was already
 * stored in a final or effectively final variable, code often repeated the
 * variable just to satisfy the syntax.
 * </p>
 *
 * <p>
 * Java 9 made it possible to use an existing final or effectively final
 * resource variable directly in the resource list.
 * </p>
 */
public class TryWithResourcesExamples {

	/**
	 * Reads the first line and closes the provided reader using the Java 9
	 * try-with-resources style.
	 *
	 * @param reader an effectively final resource created before the try block
	 * @return the first line from the reader
	 * @throws IOException when reading fails
	 */
	public String readFirstLine(BufferedReader reader) throws IOException {
		try (reader) {
			return reader.readLine();
		}
	}
}
