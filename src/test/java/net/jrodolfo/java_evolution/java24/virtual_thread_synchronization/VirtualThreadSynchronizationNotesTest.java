package net.jrodolfo.java_evolution.java24.virtual_thread_synchronization;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VirtualThreadSynchronizationNotesTest {

	private final VirtualThreadSynchronizationNotes notes = new VirtualThreadSynchronizationNotes();

	@Test
	void notesExplainPinningAndSynchronizedCodeScalability() {
		String pinning = notes.pinning();
		String synchronizedConcern = notes.synchronizedConcern();
		String java24Improvement = notes.java24Improvement();
		String benefit = notes.benefit();

		assertThat(pinning)
				.as("Pinning means a blocked virtual thread still occupies its carrier platform thread")
				.contains("blocked virtual thread")
				.contains("carrier platform thread");
		assertThat(synchronizedConcern)
				.as("The Java 24 improvement matters because synchronized code is common in existing libraries")
				.contains("synchronized code")
				.contains("existing libraries")
				.contains("scalability");
		assertThat(java24Improvement)
				.as("Java 24 improves synchronized blocking behavior for virtual threads")
				.contains("Java 24")
				.contains("synchronized code")
				.contains("avoid pinning");
		assertThat(benefit)
				.as("Existing synchronized code can work better with virtual threads")
				.contains("existing synchronized code")
				.contains("virtual threads");
	}
}
