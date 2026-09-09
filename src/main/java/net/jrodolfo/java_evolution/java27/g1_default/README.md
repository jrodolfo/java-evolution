# G1 Default Collector

G1, or Garbage-First, is a HotSpot garbage collector: it reclaims memory that
the application no longer uses while trying to balance useful application
work with pause-time goals. Serial GC is another supported collector that does
collection work with a simpler, more sequential model.

Before JDK 27, HotSpot generally selected G1 in normal or server-like
environments, but could automatically select Serial GC in some constrained
environments. JDK 27 changes that default selection: when the user does not
choose a collector, HotSpot selects G1 across those environments. Improvements
to G1 reduced the reason to maintain a special automatic Serial-GC choice for
constrained environments.

Serial GC still exists, and users can explicitly select another supported
collector. A default-selection probe also cannot show that G1 is optimal for
every workload; meaningful collector comparisons require representative
workloads and measurement.

This remains an explanatory module because a small test can inspect selection,
but cannot establish workload-level collector quality.

Notes: `G1DefaultNotes`
Test: `G1DefaultNotesTest`
