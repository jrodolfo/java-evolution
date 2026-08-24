package net.jrodolfo.java_evolution.java25.compact_object_headers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstrates the Java 25 Compact Object Headers runtime option.
 *
 * <p>
 * Compact object headers are JVM implementation details, so this example does
 * not try to measure object sizes. Instead, it launches a child JVM and verifies
 * the executable part that is faithful and portable: Java 25 accepts
 * {@code -XX:+UseCompactObjectHeaders} as a product flag, and
 * {@code -XX:+PrintFlagsFinal} reports the flag state selected on the command
 * line.
 * </p>
 */
public class CompactObjectHeadersExamples {

	private static final String FLAG_NAME = "UseCompactObjectHeaders";
	private static final Pattern FLAG_LINE = Pattern
			.compile("bool\\s+" + FLAG_NAME + "\\s+=\\s+(true|false)\\s+\\{product[^}]*}\\s+\\{([^}]*)}");

	/**
	 * Reads the compact-object-headers VM flag after enabling it explicitly.
	 *
	 * @return the VM-reported flag state
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public VmFlagState enabledFlagState() throws IOException, InterruptedException {
		return flagState("-XX:+UseCompactObjectHeaders");
	}

	/**
	 * Reads the compact-object-headers VM flag after disabling it explicitly.
	 *
	 * @return the VM-reported flag state
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public VmFlagState disabledFlagState() throws IOException, InterruptedException {
		return flagState("-XX:-UseCompactObjectHeaders");
	}

	/**
	 * Explains the executable boundary of this example.
	 *
	 * @return a short explanation
	 */
	public String executableBoundary() {
		return "this example verifies the Java 25 JVM option boundary, not object-size measurements";
	}

	/**
	 * Explains why memory savings are not asserted in the unit test.
	 *
	 * @return a short explanation
	 */
	public String measurementCaution() {
		return "real compact-object-header benefits require workload-specific heap measurement and object-layout tooling";
	}

	private VmFlagState flagState(String compactHeaderOption) throws IOException, InterruptedException {
		List<String> command = new ArrayList<>();
		command.add(javaExecutable().toString());
		command.add(compactHeaderOption);
		command.add("-XX:+PrintFlagsFinal");
		command.add("-version");

		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			throw new IllegalStateException("child JVM timed out\n" + output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		int exitCode = process.exitValue();
		if (exitCode != 0) {
			throw new IllegalStateException("child JVM failed with exit code " + exitCode + "\n" + output);
		}
		return parseFlagState(output);
	}

	private VmFlagState parseFlagState(String output) {
		Matcher matcher = FLAG_LINE.matcher(output);
		if (!matcher.find()) {
			throw new IllegalStateException("could not find " + FLAG_NAME + " in child JVM output\n" + output);
		}
		return new VmFlagState(FLAG_NAME, Boolean.parseBoolean(matcher.group(1)), matcher.group(2));
	}

	private Path javaExecutable() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).toPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	/**
	 * A JVM flag state reported by {@code -XX:+PrintFlagsFinal}.
	 *
	 * @param name the VM flag name
	 * @param enabled whether the flag is enabled
	 * @param source where the selected flag value came from
	 */
	public record VmFlagState(String name, boolean enabled, String source) {
	}
}
