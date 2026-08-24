package net.jrodolfo.java_evolution.java24.virtual_thread_synchronization;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 24 synchronization improvements for virtual threads.
 *
 * <p>
 * The executable example runs a child JVM with virtual-thread scheduler
 * parallelism set to {@code 1}. Many virtual threads block inside
 * {@code synchronized} methods, and another virtual thread must still run to
 * release them. This demonstrates a behavioral boundary of JEP 491 without
 * turning the repository into a benchmark.
 * </p>
 */
public class VirtualThreadSynchronizationExamples {

	private static final String SUCCESS_MARKER = "virtual-thread-synchronization-probe=completed";

	/**
	 * Runs the virtual-thread synchronization probe in a child JVM.
	 *
	 * @param workspace temporary workspace for the generated source file
	 * @return command result from the child JVM
	 * @throws IOException when the probe source cannot be written or launched
	 * @throws InterruptedException when waiting for the child JVM is interrupted
	 */
	public CommandResult runSynchronizedBlockingProbe(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("VirtualThreadSynchronizationProbe.java");
		Files.writeString(sourceFile, probeSource(), StandardCharsets.UTF_8);

		return run(
				javaCommand(),
				"-Djdk.virtualThreadScheduler.parallelism=1",
				sourceFile.toString());
	}

	/**
	 * Source code for the child-JVM probe.
	 *
	 * @return Java source launched by {@link #runSynchronizedBlockingProbe(Path)}
	 */
	public String probeSource() {
		return """
				import java.time.Duration;
				import java.util.ArrayList;
				import java.util.List;
				import java.util.concurrent.CountDownLatch;
				import java.util.concurrent.TimeUnit;

				public class VirtualThreadSynchronizationProbe {
				    static final class BlockingLibraryCall {
				        synchronized void waitForRelease(CountDownLatch entered, CountDownLatch release)
				                throws InterruptedException {
				            entered.countDown();
				            release.await();
				        }
				    }

				    public static void main(String[] args) throws Exception {
				        int workers = 32;
				        CountDownLatch entered = new CountDownLatch(workers);
				        CountDownLatch release = new CountDownLatch(1);
				        List<Thread> threads = new ArrayList<>();

				        for (int index = 0; index < workers; index++) {
				            BlockingLibraryCall call = new BlockingLibraryCall();
				            Thread thread = Thread.ofVirtual().start(() -> {
				                try {
				                    call.waitForRelease(entered, release);
				                } catch (InterruptedException exception) {
				                    Thread.currentThread().interrupt();
				                }
				            });
				            threads.add(thread);
				        }

				        Thread releaser = Thread.ofVirtual().start(() -> {
				            try {
				                if (!entered.await(3, TimeUnit.SECONDS)) {
				                    throw new IllegalStateException("workers did not all enter synchronized wait");
				                }
				                release.countDown();
				            } catch (InterruptedException exception) {
				                Thread.currentThread().interrupt();
				            }
				        });

				        releaser.join(Duration.ofSeconds(5));
				        if (releaser.isAlive()) {
				            throw new IllegalStateException("releaser virtual thread could not run");
				        }

				        for (Thread thread : threads) {
				            thread.join(Duration.ofSeconds(5));
				            if (thread.isAlive()) {
				                throw new IllegalStateException("worker virtual thread did not finish");
				            }
				        }

				        System.out.println("virtual-thread-synchronization-probe=completed");
				        System.out.println("scheduler-parallelism="
				                + System.getProperty("jdk.virtualThreadScheduler.parallelism"));
				    }
				}
				""";
	}

	/**
	 * Defines the runtime problem.
	 *
	 * @return a short explanation of pinning
	 */
	public String pinning() {
		return "pinning means a blocked virtual thread keeps its carrier platform thread occupied";
	}

	/**
	 * Explains why synchronized code matters.
	 *
	 * @return a short explanation of the synchronized-code concern
	 */
	public String synchronizedConcern() {
		return "synchronized code is common in existing libraries, so pinning there could reduce virtual-thread scalability";
	}

	/**
	 * Explains the Java 24 runtime goal.
	 *
	 * @return a short explanation
	 */
	public String java24Improvement() {
		return "Java 24 lets virtual threads blocked in synchronized code avoid pinning carrier threads in more cases";
	}

	/**
	 * Names the practical benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "existing synchronized code can scale better when it runs on virtual threads";
	}

	/**
	 * Explains why the probe limits scheduler parallelism.
	 *
	 * @return a short explanation
	 */
	public String schedulerBoundary() {
		return "the child JVM uses one virtual-thread carrier so the test proves blocked synchronized virtual threads do not monopolize the scheduler";
	}

	/**
	 * @return the success marker printed by the child JVM probe
	 */
	public String successMarker() {
		return SUCCESS_MARKER;
	}

	private String javaCommand() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();

		boolean finished = process.waitFor(15, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new CommandResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new CommandResult(process.exitValue(), output);
	}

	/**
	 * Result from running the child JVM probe.
	 */
	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		/**
		 * @return process exit code, or {@code -1} when the process timed out
		 */
		public int exitCode() {
			return exitCode;
		}

		/**
		 * @return combined standard output and standard error
		 */
		public String output() {
			return output;
		}
	}
}
