# G1 Synchronization Reduction

G1 means Garbage-First. It is a general-purpose garbage collector designed to balance throughput and pause-time goals.

Java 26 improves G1 throughput by reducing synchronization in garbage-collector
internals. Here, synchronization means coordination among GC worker threads
when they access or update shared collector state. Throughput means the amount
of execution capacity available for useful application work rather than
garbage collection and coordination overhead.

The intended chain is: less GC-worker coordination, less synchronization
overhead, potentially less time and CPU spent on that overhead, and potentially
better overall throughput. The result depends on the workload and runtime
conditions.

This is a runtime improvement, not a Java language or library feature. It is best understood through release notes, JVM documentation, and workload measurements.

This is an explanatory module because a small JUnit test would not faithfully
demonstrate garbage-collector throughput. A representative workload and
measurement are required, and the repository does not invent benchmark numbers
or claim that every application will see a measurable improvement.
