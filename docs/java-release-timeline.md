# Java Release Timeline

This timeline gives the historical release context for the Java versions covered by this repository. It uses month and year because the learning goal is chronology, not exact release-day memorization.

For early releases, names can be confusing: Java 1.2, 1.3, and 1.4 were marketed as Java 2 / J2SE releases; Java 5 was also known as J2SE 5.0; Java 6 and later use the Java SE naming style.

## Timeline

| Java version | Platform name used at the time | Released | Why the date matters in this project |
|---|---|---|---|
| Java 1.0 | JDK 1.0 | January 1996 | Establishes the original language and class-library foundation. |
| Java 1.1 | JDK 1.1 | February 1997 | Adds many platform features represented in `java01`, such as inner classes, reflection, serialization, JDBC, RMI, and JavaBeans. |
| Java 2 | J2SE 1.2 | December 1998 | Begins the Java 2 era with the Collections Framework, Swing, Java 2D, and a stronger security model. |
| Java 1.3 (repo: Java 3) | J2SE 1.3 | May 2000 | Adds practical runtime and library features such as dynamic proxies, timer tasks, shutdown hooks, and JNDI context. |
| Java 1.4 (repo: Java 4) | J2SE 1.4 | February 2002 | Adds regex, NIO, logging, chained exceptions, assertions, preferences, XML processing, and integrated security APIs. |
| Java 5 | J2SE 5.0 | September 2004 | Major language modernization: generics, enhanced for loops, autoboxing, enums, varargs, annotations, and concurrency utilities. |
| Java 6 | Java SE 6 | December 2006 | Platform and tooling release: scripting, compiler API, console, monitoring, web-service support, and library refinements. |
| Java 7 | Java SE 7 | July 2011 | Project Coin, NIO.2, fork/join, and `invokedynamic` bridge classic Java to the Java 8 era. |
| Java 8 | Java SE 8 | March 2014 | Lambdas, streams, `Optional`, default methods, `CompletableFuture`, and the Date/Time API change everyday Java style. |
| Java 9 | Java SE 9 | September 2017 | Introduces the module system and several practical API improvements. |
| Java 10 | Java SE 10 | March 2018 | Starts the six-month release cadence with local variable type inference as the most visible feature. |
| Java 11 | Java SE 11 | September 2018 | Long-term support release with HTTP Client, string/file API improvements, and lambda `var`. |
| Java 12 | Java SE 12 | March 2019 | Starts the preview path for switch expressions and adds useful library refinements. |
| Java 13 | Java SE 13 | September 2019 | Refines switch expressions and previews text blocks. |
| Java 14 | Java SE 14 | March 2020 | Finalizes switch expressions and previews records and pattern matching for `instanceof`. |
| Java 15 | Java SE 15 | September 2020 | Finalizes text blocks and previews sealed classes. |
| Java 16 | Java SE 16 | March 2021 | Finalizes records and pattern matching for `instanceof`. |
| Java 17 | Java SE 17 | September 2021 | Long-term support release that finalizes sealed classes and strengthens encapsulation. |
| Java 18 | Java SE 18 | March 2022 | Adds UTF-8 by default, Simple Web Server, JavaDoc snippets, and networking extension points. |
| Java 19 | Java SE 19 | September 2022 | Preview-heavy release introducing virtual threads, structured concurrency, record patterns, and FFM work. |
| Java 20 | Java SE 20 | March 2023 | Refinement release for several preview and incubator features. |
| Java 21 | Java SE 21 | September 2023 | Long-term support release that finalizes virtual threads, record patterns, pattern matching for switch, and sequenced collections. |
| Java 22 | Java SE 22 | March 2024 | Finalizes unnamed variables/patterns and the Foreign Function and Memory API. |
| Java 23 | Java SE 23 | September 2024 | Refinement release with Markdown JavaDoc comments, preview language work, and runtime notes. |
| Java 24 | Java SE 24 | March 2025 | Finalizes Stream Gatherers and the Class-File API and adds several runtime/security changes. |
| Java 25 | Java SE 25 | September 2025 | Long-term support release with final scoped values, flexible constructor bodies, module imports, compact source files, and new runtime/platform work. |

## Notes On Sources

The main date source is the Java Virtual Machine Specification table of Java SE releases and class-file versions, which lists release months from JDK 1.1 through Java SE 25. For Java 1.0, this repository uses January 1996 for the original JDK 1.0 release; later JVM specification tables often begin with JDK 1.0.2 in May 1996 because they focus on class-file compatibility.

For modern releases, OpenJDK and Oracle general-availability announcements provide exact release-day context. The repository keeps the documentation at month/year precision so the timeline remains easy to scan.

## References

- [Java SE releases and class-file versions](https://docs.oracle.com/en/java/javase/25/docs/specs/jvms/jvms-1.html)
- [JDK release archive timeline](https://ops.java/releases/)
- [OpenJDK JDK 7 project](https://openjdk.org/projects/jdk7/)
- [OpenJDK JDK 25 general availability announcement](https://mail.openjdk.org/pipermail/announce/2025-September/000360.html)
