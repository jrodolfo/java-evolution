# JFR Redaction Option Probe

Java 27 adds Flight Recorder redaction for matching arguments and environment
data. This deterministic probe checks that the JDK documents the redaction
options in a child JVM; it does not create or inspect a recording.

Example: `JfrRedactionOptionExamples`  
Test: `JfrRedactionOptionExamplesTest`
