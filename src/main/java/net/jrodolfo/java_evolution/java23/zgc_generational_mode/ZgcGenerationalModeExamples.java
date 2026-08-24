package net.jrodolfo.java_evolution.java23.zgc_generational_mode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstrates the Java 23 ZGC generational-mode runtime boundary.
 *
 * <p>
 * Java 23 made generational mode the default for ZGC. This example launches
 * child JVMs to verify the executable part that is portable on ZGC-capable
 * builds: ZGC can be selected, {@code -XX:+PrintFlagsFinal} reports
 * {@code UseZGC = true}, and GC initialization logs mention young and old
 * generation workers.
 * </p>
 *
 * <p>
 * The example does not benchmark garbage collection. Throughput, pause time,
 * heap footprint, and CPU behavior require real workloads and measurement.
 * </p>
 */
public class ZgcGenerationalModeExamples {

	private static final Pattern USE_ZGC_LINE = Pattern
			.compile("bool\\s+UseZGC\\s+=\\s+(true|false)\\s+\\{product}\\s+\\{([^}]*)}");

	/**
	 * Checks whether the current JVM build supports ZGC.
	 *
	 * @return {@code true} when a child JVM starts with {@code -XX:+UseZGC}
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public boolean zgcAvailable() throws IOException, InterruptedException {
		return runJava("-XX:+UseZGC", "-version").exitCode() == 0;
	}

	/**
	 * Reads the child JVM flag state after selecting ZGC.
	 *
	 * @return the reported {@code UseZGC} flag state
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public VmFlagState zgcFlagState() throws IOException, InterruptedException {
		ProcessResult result = runJava("-XX:+UseZGC", "-XX:+PrintFlagsFinal", "-version");
		if (result.exitCode() != 0) {
			throw new IllegalStateException("child JVM failed\n" + result.output());
		}
		return parseUseZgc(result.output());
	}

	/**
	 * Captures ZGC initialization logs.
	 *
	 * @return child JVM result containing {@code gc+init} output
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public ProcessResult zgcInitializationLog() throws IOException, InterruptedException {
		return runJava("-XX:+UseZGC", "-Xmx64m", "-Xlog:gc+init=info", "-version");
	}

	/**
	 * Captures the status of the old {@code ZGenerational} switch in Java 24+.
	 *
	 * @return child JVM result showing whether the old switch is still meaningful
	 * @throws IOException if the child JVM cannot be started
	 * @throws InterruptedException if interrupted while waiting for the child JVM
	 */
	public ProcessResult oldGenerationalSwitchStatus() throws IOException, InterruptedException {
		return runJava("-XX:+UseZGC", "-XX:-ZGenerational", "-version");
	}

	/**
	 * Explains the generational observation behind the Java 23 default change.
	 *
	 * @return a short explanation
	 */
	public String generationalObservation() {
		return "many Java objects die young, so ZGC now uses young and old generations by default";
	}

	/**
	 * Explains the boundary of the executable example.
	 *
	 * @return a short boundary explanation
	 */
	public String benchmarkBoundary() {
		return "this example verifies ZGC runtime selection and initialization logs, not throughput or pause-time improvement";
	}

	private VmFlagState parseUseZgc(String output) {
		Matcher matcher = USE_ZGC_LINE.matcher(output);
		if (!matcher.find()) {
			throw new IllegalStateException("could not find UseZGC in child JVM output\n" + output);
		}
		return new VmFlagState("UseZGC", Boolean.parseBoolean(matcher.group(1)), matcher.group(2));
	}

	private ProcessResult runJava(String... arguments) throws IOException, InterruptedException {
		List<String> command = new ArrayList<>();
		command.add(javaExecutable());
		command.addAll(List.of(arguments));

		Process process = new ProcessBuilder(command)
				.redirectErrorStream(true)
				.start();

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new ProcessResult(-1, output);
		}
		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new ProcessResult(process.exitValue(), output);
	}

	private String javaExecutable() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
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

	/**
	 * Captures a child process result.
	 *
	 * @param exitCode process exit code
	 * @param output merged standard output and standard error
	 */
	public record ProcessResult(int exitCode, String output) {
	}
}
