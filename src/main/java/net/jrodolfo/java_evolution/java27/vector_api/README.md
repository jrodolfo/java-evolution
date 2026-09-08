# Vector API Twelfth Incubator

SIMD means Single Instruction, Multiple Data: one conceptual vector operation
can process several numeric lanes, or values, at once. That can help
data-parallel numerical workloads when the hardware and JIT compiler can map
the operations efficiently, but it does not guarantee faster execution for
every workload.

Java 27 continues the Vector API as its twelfth incubator iteration. Incubator
means the API is available for experimentation but is not final and may still
change. This repository points to the earlier executable SIMD lesson instead
of duplicating essentially the same demonstration while the API continues to
evolve.

Notes: `VectorApiTwelfthIncubatorNotes`  
Test: `VectorApiTwelfthIncubatorNotesTest`
