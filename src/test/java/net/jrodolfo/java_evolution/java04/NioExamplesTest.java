package net.jrodolfo.java_evolution.java04;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

import org.junit.jupiter.api.Test;

class NioExamplesTest {

	private final NioExamples examples = new NioExamples();

	@Test
	void writableChannelReceivesBytesFromBuffer() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		examples.writeText(Channels.newChannel(output), "nio");

		assertThat(output.toString(java.nio.charset.StandardCharsets.UTF_8))
				.as("WritableByteChannel should receive all bytes from the buffer")
				.isEqualTo("nio");
	}

	@Test
	void readableChannelUsesFlipAndClearBufferCycle() throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream("buffer".getBytes(java.nio.charset.StandardCharsets.UTF_8));

		assertThat(examples.readText(Channels.newChannel(input)))
				.as("ReadableByteChannel should feed data through explicit buffer phases")
				.isEqualTo("buffer");
	}
}
