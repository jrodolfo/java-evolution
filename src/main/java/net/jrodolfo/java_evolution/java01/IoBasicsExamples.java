package net.jrodolfo.java_evolution.java01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Refreshes classic {@code java.io} stream concepts.
 */
public class IoBasicsExamples {

	/**
	 * Copies bytes from an input stream to an output stream.
	 *
	 * @param text text to copy
	 * @return copied text
	 * @throws IOException when stream operations fail
	 */
	public String copyBytes(String text) throws IOException {
		InputStream input = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toString(StandardCharsets.UTF_8);
	}

	private void copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4];
		int bytesRead = input.read(buffer);

		while (bytesRead != -1) {
			output.write(buffer, 0, bytesRead);
			bytesRead = input.read(buffer);
		}
	}
}
