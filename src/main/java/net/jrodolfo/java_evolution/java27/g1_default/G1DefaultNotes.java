package net.jrodolfo.java_evolution.java27.g1_default;

/** Explains the Java 27 change making G1 the default collector everywhere. */
public class G1DefaultNotes {

	/** Returns a learner-friendly summary. */
	public String summary() {
		return "Java 27 makes G1 the default garbage collector in all environments";
	}

	/** Explains why the repository keeps this topic as notes. */
	public String projectDecision() {
		return "collector selection and throughput should be validated with representative workloads, not a tiny unit test";
	}
}
