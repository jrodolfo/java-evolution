package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

class VirtualThreadsPreviewExamplesTest {

	private final VirtualThreadsPreviewExamples examples = new VirtualThreadsPreviewExamples();

	@Test
	void virtualThreadRunsTask() throws ExecutionException, InterruptedException {
		// When
		String result = examples.runInVirtualThread();

		// Then
		assertThat(result)
				.as("The task should observe that it is running inside a virtual thread")
				.isEqualTo("running in virtual thread");
	}

	@Test
	void threadBuilderCanCreateUnstartedVirtualThread() {
		assertThat(examples.unstartedThreadIsVirtual())
				.as("Thread.ofVirtual should create virtual threads")
				.isTrue();
	}
}
