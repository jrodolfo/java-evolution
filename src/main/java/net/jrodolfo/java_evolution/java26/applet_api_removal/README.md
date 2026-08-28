# Applet API Removal

Applets were Java programs embedded in web pages through browser plugins.

That model disappeared from mainstream Java development years ago. Browser plugin support went away, applets were deprecated for removal, and Java moved toward server, desktop, command-line, mobile-adjacent, cloud, and embedded use cases instead.

Java 26 removes the Applet API. This is a migration and historical-cleanup topic. The useful lesson is not how to write an applet, but how long-lived platforms retire APIs after deprecation and warning periods.

This is a C1 executable removal example. `AppletApiRemovalExamples` writes a tiny obsolete source file that imports `java.applet.Applet`, runs `javac`, and verifies that JDK 26 rejects it because the package no longer exists.

The example deliberately captures a compile failure. That is the correct behavior for a removed API.
