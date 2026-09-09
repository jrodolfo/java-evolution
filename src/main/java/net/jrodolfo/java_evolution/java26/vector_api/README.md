# Vector API Eleventh Incubator

The Vector API gives Java a way to express Single Instruction, Multiple Data (SIMD) computations directly.

SIMD matters when the same operation can be applied to many values at once, such as numeric arrays, image processing, compression, or scientific workloads. The JVM can map vector operations to CPU vector instructions when the hardware and runtime support them.

Java 26 continues the Vector API as an eleventh incubator. Incubator APIs are intentionally not final and are delivered in incubator modules while the design evolves.

This is an executable incubator-module demonstration. It uses a child JVM to compile and run a small Java 26 vector addition with `jdk.incubator.vector`, while avoiding any benchmark or hardware-performance claim.
