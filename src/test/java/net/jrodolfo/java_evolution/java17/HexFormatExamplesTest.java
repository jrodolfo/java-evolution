package net.jrodolfo.java_evolution.java17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HexFormatExamplesTest {

	private final HexFormatExamples examples = new HexFormatExamples();

	@Test
	void hexFormatConvertsBytesToHexAndBack() {
		// Given
		byte[] bytes = new byte[] { 0x0A, 0x0B, 0x0C };

		// When
		String hex = examples.toHex(bytes);
		byte[] parsed = examples.fromHex(hex);

		// Then
		assertThat(hex)
				.as("HexFormat should format bytes as lowercase hexadecimal text")
				.isEqualTo("0a0b0c");
		assertThat(parsed)
				.as("HexFormat should parse hexadecimal text back into bytes")
				.containsExactly(bytes);
	}

	@Test
	void hexFormatCanUseDelimiters() {
		// Given
		byte[] bytes = new byte[] { 0x01, 0x02, 0x03 };

		// When / Then
		assertThat(examples.toColonDelimitedHex(bytes))
				.as("HexFormat can format bytes with a delimiter")
				.isEqualTo("01:02:03");
	}
}
