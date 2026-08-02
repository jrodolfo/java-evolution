package net.jrodolfo.java_evolution.java18;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Demonstrates UTF-8 as the default charset, standardized in Java 18.
 *
 * <p>
 * Before Java 18, APIs that used the default charset could behave differently
 * on different machines because the default came from the operating system and
 * locale. That made simple text examples harder to reason about and made file
 * exchange bugs easy to create accidentally. Java 18 made UTF-8 the standard
 * default so common text handling is predictable across platforms.
 * </p>
 *
 * <p>
 * The examples still keep the lesson visible: defaults are now safer, but
 * explicit charsets are still best when a file format, protocol, or external
 * system requires one.
 * </p>
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
