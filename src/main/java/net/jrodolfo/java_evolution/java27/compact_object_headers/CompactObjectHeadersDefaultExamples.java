package net.jrodolfo.java_evolution.java27.compact_object_headers;

import java.io.IOException;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Verifies that Compact Object Headers are enabled by default in Java 27. */
public class CompactObjectHeadersDefaultExamples {

	/** Runs {@code PrintFlagsFinal} in a child JVM and returns its output. */
	public Java27ChildProcess.Result inspectDefaultFlag() throws IOException, InterruptedException {
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "-XX:+PrintFlagsFinal", "-version");
	}

	/** Explains why this is a flag observation rather than a layout benchmark. */
	public String boundary() {
		return "the example verifies the Java 27 default flag without claiming to measure object layout or memory savings";
	}
}
