package net.jrodolfo.java_evolution.java24;

/**
 * Explains Java 24 synchronization improvements for virtual threads.
 */
public class VirtualThreadSynchronizationNotes {
	public String purpose() {
		return "allow virtual threads blocked in synchronized code to avoid pinning platform threads";
	}

	public String benefit() {
		return "improve scalability for existing synchronized code running on virtual threads";
	}
}
