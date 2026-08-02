package net.jrodolfo.java_evolution.java18;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Demonstrates UTF-8 as the default charset, standardized in Java 18.
 */
public class Utf8DefaultCharsetExamples {

	/**
	 * Reads the JVM default charset.
	 *
	 * @return the default charset
	 */
	public Charset defaultCharset() {
		return Charset.defaultCharset();
	}

	/**
	 * Checks whether the default charset is UTF-8.
	 *
	 * @return whether the default charset is {@link StandardCharsets#UTF_8}
	 */
	public boolean defaultCharsetIsUtf8() {
		return StandardCharsets.UTF_8.equals(defaultCharset());
	}

	/**
	 * Encodes text using the default charset, which Java 18 standardized as
	 * UTF-8.
	 *
	 * @param text the text to encode
	 * @return encoded bytes
	 */
	public byte[] bytesWithDefaultCharset(String text) {
		return text.getBytes();
	}
}
