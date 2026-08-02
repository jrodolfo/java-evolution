package net.jrodolfo.java_evolution.java09;

import java.util.Optional;

/**
 * Demonstrates Process API updates introduced in Java 9.
 *
 * <p>
 * Before Java 9, Java had limited standard APIs for process metadata. Code
 * that needed a process id, parent process, or command information often had
 * to call platform-specific shell commands or native code.
 * </p>
 *
 * <p>
 * {@link ProcessHandle} solves this by giving Java code a standard way to
 * inspect the current process and related process metadata without launching
 * platform-specific commands.
 * </p>
 */
public class ProcessApiExamples {

	/**
	 * Reads the current process id.
	 *
	 * @return the id of the JVM process running this code
	 */
	public long currentProcessId() {
		return ProcessHandle.current().pid();
	}

	/**
	 * Reads the current process command when the JVM can provide it.
	 *
	 * @return an Optional containing the command path or name
	 */
	public Optional<String> currentProcessCommand() {
		return ProcessHandle.current()
				.info()
				.command();
	}

	/**
	 * Reads the parent process when one is visible to the JVM.
	 *
	 * @return the parent process handle, or an empty Optional
	 */
	public Optional<ProcessHandle> parentProcess() {
		return ProcessHandle.current().parent();
	}

	/**
	 * Creates a small immutable summary of the current process.
	 *
	 * @return process metadata that can be asserted in tests
	 */
	public CurrentProcessSummary currentProcessSummary() {
		ProcessHandle currentProcess = ProcessHandle.current();
		return new CurrentProcessSummary(
				currentProcess.pid(),
				currentProcess.isAlive(),
				currentProcess.info().command().isPresent());
	}

	/**
	 * Small Java 9-style data class for process metadata.
	 */
	public static class CurrentProcessSummary {
		private final long pid;
		private final boolean alive;
		private final boolean commandAvailable;

		/**
		 * Creates immutable process metadata for tests and examples.
		 *
		 * @param pid the process id
		 * @param alive whether the process is alive
		 * @param commandAvailable whether command metadata is visible
		 */
		public CurrentProcessSummary(long pid, boolean alive, boolean commandAvailable) {
			this.pid = pid;
			this.alive = alive;
			this.commandAvailable = commandAvailable;
		}

		/**
		 * @return the process id
		 */
		public long pid() {
			return pid;
		}

		/**
		 * @return whether the process is currently alive
		 */
		public boolean alive() {
			return alive;
		}

		/**
		 * @return whether process command metadata was available
		 */
		public boolean commandAvailable() {
			return commandAvailable;
		}
	}
}
