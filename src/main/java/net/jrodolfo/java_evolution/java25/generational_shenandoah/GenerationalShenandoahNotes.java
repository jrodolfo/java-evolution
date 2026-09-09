package net.jrodolfo.java_evolution.java25.generational_shenandoah;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Explains Generational Shenandoah, introduced as a product feature in Java 25
 * by JEP 521.
 *
 * <p>
 * This module keeps the performance discussion explanatory, but includes a
 * deterministic child-JVM probe for the product-feature status. A faithful
 * performance evaluation still requires a realistic workload, JVM options,
 * garbage-collection logs, and measurements.
 * </p>
 */
public class GenerationalShenandoahNotes {

	/** @return a short garbage-collection problem statement */
	public String garbageCollectionProblem() {
		return "garbage collection reclaims heap memory from objects that are no longer reachable";
	}

	/** @return a short explanation of Shenandoah's goal */
	public String shenandoahGoal() {
		return "Shenandoah is a low-pause garbage collector that performs much of its work concurrently with the application";
	}

	/** @return a short explanation of the generational idea */
	public String generationalIdea() {
		return "generational garbage collection uses the observation that many objects die young";
	}

	/** @return a short explanation of the Java 25 status change */
	public String java25Status() {
		return "Java 25 changed Shenandoah's generational mode from an experimental feature to a product feature";
	}

	/** @return a short command-line note */
	public String option() {
		return "generational Shenandoah can be selected with -XX:+UseShenandoahGC and -XX:ShenandoahGCMode=generational, but it is not the default Shenandoah mode in Java 25";
	}

	/** @return the project decision about performance claims */
	public String projectDecision() {
		return "this repository keeps Generational Shenandoah as explanatory notes because it requires realistic workloads, GC logs, and measurements";
	}

	/**
	 * Checks whether this JDK build can start with Shenandoah selected.
	 *
	 * @return {@code true} when Shenandoah is available
	 */
	public boolean shenandoahAvailable() throws IOException, InterruptedException {
		return runJava("-XX:+UseShenandoahGC", "-version").exitCode() == 0;
	}

	/**
	 * Probes Java 25's product-feature behavior: generational mode is selected
	 * without unlocking experimental VM options.
	 *
	 * @return the child JVM result
	 */
	public ProcessResult generationalModeProbe() throws IOException, InterruptedException {
		return runJava("-XX:+UseShenandoahGC", "-XX:ShenandoahGCMode=generational", "-version");
	}

	private ProcessResult runJava(String... arguments) throws IOException, InterruptedException {
		String[] command = new String[arguments.length + 1];
		command[0] = javaExecutable();
		System.arraycopy(arguments, 0, command, 1, arguments.length);

		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new ProcessResult(-1, output.join());
		}
		return new ProcessResult(process.exitValue(), output.join());
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	private String javaExecutable() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	/** Captures the child JVM exit code and merged output. */
	public record ProcessResult(int exitCode, String output) {
	}
}
