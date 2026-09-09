package net.jrodolfo.java_evolution.java27.g1_default;

import java.io.IOException;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Explains the Java 27 HotSpot change making G1 the default collector broadly. */
public class G1DefaultNotes {

	/** Returns a learner-friendly summary. */
	public String summary() {
		return "Java 27 HotSpot makes G1 the default garbage collector when no collector is selected";
	}

	/** Explains why the repository keeps this topic as notes. */
	public String projectDecision() {
		return "default collector selection is deterministic JVM configuration, but throughput consequences require representative workloads and are not proved by this test";
	}

	/** Runs HotSpot without explicitly selecting a garbage collector. */
	public Java27ChildProcess.Result inspectDefaultCollector() throws IOException, InterruptedException {
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "-XX:+PrintFlagsFinal", "-version");
	}
}
