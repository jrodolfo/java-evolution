package net.jrodolfo.java_evolution.java06.monitoring_management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MonitoringManagementExamplesTest {

	private final MonitoringManagementExamples examples = new MonitoringManagementExamples();

	@Test
	void runtimeMxBeanExposesCurrentJvmIdentity() {
		assertThat(examples.runtimeName())
				.as("RuntimeMXBean should expose the current running JVM identity")
				.isNotBlank();
	}

	@Test
	void classLoadingMxBeanExposesLoadedClassCount() {
		assertThat(examples.loadedClassCount())
				.as("ClassLoadingMXBean should expose a nonnegative loaded-class snapshot")
				.isGreaterThanOrEqualTo(0);
	}

	@Test
	void memoryMxBeanExposesHeapUsageSnapshot() {
		assertThat(examples.usedHeapBytes())
				.as("MemoryMXBean should expose a nonnegative heap usage snapshot")
				.isGreaterThanOrEqualTo(0L);
	}

	@Test
	void threadMxBeanExposesLiveThreadCount() {
		assertThat(examples.liveThreadCount())
				.as("ThreadMXBean should expose a positive live-thread snapshot")
				.isPositive();
	}

	@Test
	void operatingSystemMxBeanExposesJava6LoadAverageCapability() {
		double loadAverage = examples.systemLoadAverage();

		assertThat(loadAverage == -1.0 || loadAverage >= 0.0)
				.as("Java 6 system-load monitoring returns a nonnegative value or -1 when unavailable")
				.isTrue();
	}

	@Test
	void snapshotCollectsMultipleManagementReadings() {
		MonitoringManagementExamples.JvmSnapshot snapshot = examples.snapshot();

		assertThat(snapshot.runtimeName()).isNotBlank();
		assertThat(snapshot.loadedClassCount()).isGreaterThanOrEqualTo(0);
		assertThat(snapshot.usedHeapBytes()).isGreaterThanOrEqualTo(0L);
		assertThat(snapshot.liveThreadCount()).isPositive();
	}
}
