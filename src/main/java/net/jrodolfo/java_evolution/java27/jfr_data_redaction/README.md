# JFR Redaction Option Probe

Java Flight Recorder (JFR) captures diagnostic information about a running
JVM. That information can include command-line arguments, initial environment
variable values, and system properties, which may contain passwords, tokens,
credentials, or sensitive configuration.

Java 27 exposes in-process redaction options for matching values before they
are written into a recording. This matters because sanitizing a recording file
afterward is different from preventing a sensitive value from entering the
file in the first place.

This deterministic probe checks that a JDK 27 child JVM exposes the relevant
redaction option/help configuration. It does not create or inspect a JFR
recording, verify that a particular value is redacted at recording time, or
require an application workload.

Example: `JfrRedactionOptionExamples`
Test: `JfrRedactionOptionExamplesTest`
