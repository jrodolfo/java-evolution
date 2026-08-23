package net.jrodolfo.java_evolution.java06.monitoring_management;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

/**
 * Demonstrates platform MXBean monitoring APIs improved around Java 6.
 */
public class MonitoringManagementExamples {

	/**
	 * Reads descriptive runtime information for the current JVM.
	 *
	 * @return JVM runtime name
	 */
	public String runtimeName() {
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		return runtime.getName();
	}

	/**
	 * Reads how many classes are currently loaded in this JVM.
	 *
	 * @return currently loaded class count
	 */
	public int loadedClassCount() {
		ClassLoadingMXBean classLoading = ManagementFactory.getClassLoadingMXBean();
		return classLoading.getLoadedClassCount();
	}

	/**
	 * Reads a heap-memory snapshot.
	 *
	 * @return used heap memory in bytes
	 */
	public long usedHeapBytes() {
		MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memory.getHeapMemoryUsage();
		return heap.getUsed();
	}

	/**
	 * Reads the current live-thread count.
	 *
	 * @return live thread count
	 */
	public int liveThreadCount() {
		ThreadMXBean threads = ManagementFactory.getThreadMXBean();
		return threads.getThreadCount();
	}

	/**
	 * Combines several MXBean readings into a compact operational snapshot.
	 *
	 * @return JVM snapshot
	 */
	public JvmSnapshot snapshot() {
		return new JvmSnapshot(runtimeName(), loadedClassCount(), usedHeapBytes(), liveThreadCount());
	}

	/**
	 * Immutable snapshot of runtime monitoring values.
	 */
	public static class JvmSnapshot {

		private final String runtimeName;
		private final int loadedClassCount;
		private final long usedHeapBytes;
		private final int liveThreadCount;

		public JvmSnapshot(String runtimeName, int loadedClassCount, long usedHeapBytes, int liveThreadCount) {
			this.runtimeName = runtimeName;
			this.loadedClassCount = loadedClassCount;
			this.usedHeapBytes = usedHeapBytes;
			this.liveThreadCount = liveThreadCount;
		}

		public String runtimeName() {
			return runtimeName;
		}

		public int loadedClassCount() {
			return loadedClassCount;
		}

		public long usedHeapBytes() {
			return usedHeapBytes;
		}

		public int liveThreadCount() {
			return liveThreadCount;
		}
	}
}
