package net.jrodolfo.java_evolution.java04;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/**
 * Demonstrates Java 4 NIO buffers and channels.
 */
public class NioExamples {
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * Writes text through a channel backed by a byte buffer.
	 *
	 * @param channel destination channel
	 * @param text text to write
	 * @throws IOException when writing fails
	 */
	public void writeText(WritableByteChannel channel, String text) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(text.getBytes(UTF_8));

		while (buffer.hasRemaining()) {
			channel.write(buffer);
		}
	}

	/**
	 * Reads text from a channel using explicit buffer flip and clear phases.
	 *
	 * @param channel source channel
	 * @return decoded text
	 * @throws IOException when reading fails
	 * @apiNote This compact example decodes each buffer chunk independently and
	 * is intended for ASCII-compatible sample text. Streaming non-ASCII text with
	 * multibyte characters split across buffers requires a stateful
	 * {@link java.nio.charset.CharsetDecoder}.
	 */
	public String readText(ReadableByteChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		StringBuilder text = new StringBuilder();
		int bytesRead = channel.read(buffer);

		while (bytesRead != -1) {
			buffer.flip();
			text.append(UTF_8.decode(buffer));
			buffer.clear();
			bytesRead = channel.read(buffer);
		}

		return text.toString();
	}
}
