package net.jrodolfo.java_evolution.java27.jfr_data_redaction;

import java.io.IOException;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Probes Java 27 JFR redaction options for arguments, environment, and properties. */
public class JfrRedactionOptionExamples {

	/** Runs the JDK's documented Flight Recorder option-help probe. */
	public Java27ChildProcess.Result inspectRedactionOptions() throws IOException, InterruptedException {
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"),
				"-XX:FlightRecorderOptions=help", "-version");
	}

	/** Explains what this deterministic probe establishes. */
	public String purpose() {
		return "the JDK 27 JFR option help exposes configuration for redacting matching command-line, environment, and system-property data";
	}

	/** States what this focused, deterministic probe does not establish. */
	public String boundary() {
		return "the probe verifies option availability without recording events, inspecting redacted data, or requiring an application workload";
	}
}
