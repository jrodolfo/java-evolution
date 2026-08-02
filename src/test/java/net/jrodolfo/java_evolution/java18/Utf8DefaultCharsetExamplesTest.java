package net.jrodolfo.java_evolution.java18;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class Utf8DefaultCharsetExamplesTest {

	private final Utf8DefaultCharsetExamples examples = new Utf8DefaultCharsetExamples();

	@Test
	void defaultCharsetIsUtf8InJava18AndLater() {
		// When / Then
		assertThat(examples.defaultCharset())
				.as("Java 18 standardized the default charset as UTF-8")
				.isEqualTo(StandardCharsets.UTF_8);
		assertThat(examples.defaultCharsetIsUtf8())
				.as("The helper should report UTF-8 as the default charset")
				.isTrue();
	}

	@Test
	void defaultEncodingUsesUtf8Bytes() {
		// When
		byte[] bytes = examples.bytesWithDefaultCharset("cafe");

		// Then
		assertThat(bytes)
				.as("Default charset encoding should match explicit UTF-8 for ASCII-compatible text")
				.containsExactly("cafe".getBytes(StandardCharsets.UTF_8));
	}
}
