package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class TryWithResourcesExamplesTest {

	private final TryWithResourcesExamples examples = new TryWithResourcesExamples();

	@Test
	void effectivelyFinalResourceCanBeUsedDirectlyInTryWithResources() throws IOException {
		// Given
		TrackingBufferedReader reader = new TrackingBufferedReader("first line\nsecond line");

		// When
		String firstLine = examples.readFirstLine(reader);

		// Then
		assertThat(firstLine)
				.as("The method should read the first line from the existing resource variable")
				.isEqualTo("first line");
		assertThat(reader.closed())
				.as("try-with-resources should close the existing effectively final resource")
				.isTrue();
	}

	private static class TrackingBufferedReader extends BufferedReader {
		private boolean closed;

		TrackingBufferedReader(String text) {
			super(new StringReader(text));
		}

		@Override
		public void close() throws IOException {
			closed = true;
			super.close();
		}

		boolean closed() {
			return closed;
		}
	}
}
