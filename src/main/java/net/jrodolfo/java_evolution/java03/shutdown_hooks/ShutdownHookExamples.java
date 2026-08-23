package net.jrodolfo.java_evolution.java03.shutdown_hooks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Demonstrates JVM shutdown hooks, introduced in J2SE 1.3.
 */
public class ShutdownHookExamples {

	public static final String MARKER_MESSAGE = "shutdown hook ran";

	/**
	 * Registers a shutdown hook that writes a marker file during orderly JVM
	 * shutdown.
	 *
	 * @param markerFile file to write from the shutdown hook
	 * @param message marker text
	 */
	public void registerMarkerFileHook(final File markerFile, final String message) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					writeMarker(markerFile, message);
				} catch (IOException exception) {
					throw new IllegalStateException("Could not write shutdown marker: " + exception.getMessage());
				}
			}
		}, "java-evolution-shutdown-hook"));
	}

	/**
	 * Entry point used by the test suite's child JVM.
	 *
	 * @param args first argument is the marker file path
	 * @return process exit code
	 */
	public int runChildProcessMode(String[] args) {
		if (args.length != 1) {
			return 2;
		}

		registerMarkerFileHook(new File(args[0]), MARKER_MESSAGE);
		return 0;
	}

	public static void main(String[] args) {
		int exitCode = new ShutdownHookExamples().runChildProcessMode(args);
		if (exitCode != 0) {
			System.exit(exitCode);
		}
	}

	private void writeMarker(File markerFile, String message) throws IOException {
		File parent = markerFile.getParentFile();
		if (parent != null && !parent.exists() && !parent.mkdirs()) {
			throw new IOException("Could not create marker directory: " + parent);
		}

		Writer writer = new OutputStreamWriter(new FileOutputStream(markerFile), "UTF-8");
		try {
			writer.write(message);
		} finally {
			writer.close();
		}
	}
}
