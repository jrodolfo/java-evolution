package net.jrodolfo.java_evolution.java24.virtual_thread_synchronization;

/**
 * Explains Java 24 synchronization improvements for virtual threads.
 *
 * <p>
 * Virtual threads are most useful when blocking operations do not unnecessarily
 * pin carrier threads. Java 24 improved synchronization behavior so virtual
 * threads blocked in synchronized code can avoid pinning carrier threads in
 * more cases. That helps existing synchronized code work better with virtual
 * threads.
 * </p>
 */
public class VirtualThreadSynchronizationNotes {
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
}
