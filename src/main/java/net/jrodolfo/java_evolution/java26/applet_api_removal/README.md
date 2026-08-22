# Applet API Removal

Applets were Java programs embedded in web pages through browser plugins.

That model disappeared from mainstream Java development years ago. Browser plugin support went away, applets were deprecated for removal, and Java moved toward server, desktop, command-line, mobile-adjacent, cloud, and embedded use cases instead.

Java 26 removes the Applet API. This is a migration and historical-cleanup topic. The useful lesson is not how to write an applet, but how long-lived platforms retire APIs after deprecation and warning periods.

This is a C2 explanatory module because demonstrating removed APIs would require obsolete source code that should not compile in the current project.
