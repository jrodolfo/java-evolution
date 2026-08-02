package net.jrodolfo.java_evolution.java17;

import java.util.HexFormat;

/**
 * Demonstrates {@link HexFormat}, introduced in Java 17.
 */
public class HexFormatExamples {

	/**
	 * Converts bytes to lowercase hexadecimal text.
	 *
	 * @param bytes the bytes to format
	 * @return hexadecimal text
	 */
	public String toHex(byte[] bytes) {
		return HexFormat.of().formatHex(bytes);
	}

	/**
	 * Parses hexadecimal text into bytes.
	 *
	 * @param hex hexadecimal text
	 * @return parsed bytes
	 */
	public byte[] fromHex(String hex) {
		return HexFormat.of().parseHex(hex);
	}

	/**
	 * Formats bytes with a delimiter.
	 *
	 * @param bytes the bytes to format
	 * @return hexadecimal text separated with colons
	 */
	public String toColonDelimitedHex(byte[] bytes) {
		return HexFormat.ofDelimiter(":").formatHex(bytes);
	}
}
