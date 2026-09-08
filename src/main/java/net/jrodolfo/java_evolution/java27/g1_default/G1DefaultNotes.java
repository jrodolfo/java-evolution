package net.jrodolfo.java_evolution.java27.g1_default;

/** Explains the Java 27 HotSpot change making G1 the default collector broadly. */
public class G1DefaultNotes {

	/** Returns a learner-friendly summary. */
	public String summary() {
		return "Java 27 HotSpot makes G1 the default garbage collector when no collector is selected";
	}

	/** Explains why the repository keeps this topic as notes. */
	public String projectDecision() {
		return "collector selection and throughput should be validated with representative workloads, not a tiny unit test";
	}
}
