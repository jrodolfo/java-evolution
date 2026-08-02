package net.jrodolfo.java_evolution.java24;

/**
 * Explains Java 24 synchronization improvements for virtual threads.
 *
 * <p>
 * Virtual threads are most useful when blocking operations do not unnecessarily
 * pin platform threads. Java 24 improved synchronization behavior so existing
 * synchronized code can work better with virtual threads.
 * </p>
 */
public class VirtualThreadSynchronizationNotes {
	/**
	 * Explains the runtime goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "allow virtual threads blocked in synchronized code to avoid pinning platform threads";
	}

	/**
	 * Names the practical benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "improve scalability for existing synchronized code running on virtual threads";
	}
}
