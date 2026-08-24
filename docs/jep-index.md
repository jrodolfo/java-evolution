# JEP Index

This index centralizes the JEP links used by the repository. It is a lookup table, not a replacement for the version READMEs. For release months and historical naming context, use [java-release-timeline.md](java-release-timeline.md).

Status labels:

- **Final**: stable feature in the named release.
- **Preview**: available for feedback, may change later.
- **Incubator**: non-final incubating API, usually in an incubator module.
- **Tooling**: command-line, JavaDoc, launcher, or build/development support.
- **Runtime**: JVM, GC, diagnostics, startup, or migration behavior.
- **Security**: cryptography or security platform feature.
- **Removal**: API or platform capability removed from the JDK.
- **Notes**: represented as a notes class in this repository.
- **Explanatory module**: represented by a focused package README and notes class because a small runnable example would be misleading or require special setup.
- **Example**: represented by runnable example code and focused tests.
- **Documentation example**: represented by source documentation that is verified through JavaDoc generation.
- **Experimental**: an additional qualifier used when the referenced JEP describes an experimental runtime or tooling capability.

## Java 1-7

Java 1-7 predate the modern JEP process used elsewhere in this index. Their entries point to official release notes, specifications, or API documentation instead of JEP pages. See [java-release-timeline.md](java-release-timeline.md) for their release dates and naming history.

| Reference | Feature | Status |
|---|---|---|
| [What's New in JDK 1.1](https://courses.cs.washington.edu/courses/cse341/99wi/java/tutorial/post1.0/whatsnew/index.html) | Inner classes, reflection, serialization, JDBC, RMI, JavaBeans, AWT updates, JAR files, JNI, internationalization | Final, Examples |
| [JavaBeans specification](https://www.oracle.com/java/technologies/javase/javabeans-spec.html) | JavaBeans component conventions | Final, Executable Example |
| [Object Serialization FAQ](https://www.oracle.com/java/technologies/javase/serializationfaq-jsp.html) | Object serialization | Final, Executable Example |
| [Java language enhancements history](https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html) | `strictfp` and assertions | Final, Notes and Executable Example |
| [Java 2 platform documentation](https://docs.oracle.com/cd/E19683-01/806-7930/features-2d/index.html) | Java 2D | Final, Executable Example |
| [J2SE 1.4 new features and enhancements](https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/relnotes/features.html) | NIO, regular expressions, XML/JAXP, integrated security APIs, logging, preferences, chained exceptions | Final, Examples and Preferences Notes |
| [Oracle J2SE 5.0 new features](https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html) | Generics, enhanced for loop, autoboxing, enums, varargs, static import, annotations, concurrency utilities | Final, Example |
| [Formatter API](https://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html) | Formatted output | Final, Example |
| [Oracle Java SE 6 features](https://www.oracle.com/java/technologies/javase/features.html) | Navigable collections, scripting, compiler API, console, monitoring, web-service support | Final, Examples and Explanatory Modules |
| [Java SE 6 scripting guide](https://docs.oracle.com/javase/6/docs/technotes/guides/scripting/) | Scripting support | Final, Executable Example |
| [JavaCompiler API](https://docs.oracle.com/javase/6/docs/api/javax/tools/JavaCompiler.html) | Compiler API | Final, Tooling, Executable Example |
| [Console API](https://docs.oracle.com/javase/6/docs/api/java/io/Console.html) | Console API | Final, Tooling, Executable Example |
| [Project Coin / JSR 334](https://cr.openjdk.org/~darcy/ProjectCoin/ProjectCoin-Documentation-v0.9375.html) | Try-with-resources, multi-catch, diamond operator, strings in switch, binary literals, numeric underscores | Final, Example |
| [JDK 7 adoption guide](https://docs.oracle.com/javase/7/docs/webnotes/adoptionGuide/) | NIO.2 and Java 7 migration features | Final, Example |
| [Java SE 7 concurrency enhancements](https://docs.oracle.com/javase/7/docs/technotes/guides/concurrency/changes7.html) | Fork/join framework | Final, Example |
| [java.lang.invoke package](https://docs.oracle.com/javase/7/docs/api/java/lang/invoke/package-summary.html) | `invokedynamic` and method handles | Final, Tooling, Executable Example |

## Java 8

| JEP | Feature | Status |
|---|---|---|
| [JEP 126](https://openjdk.org/jeps/126) | Lambda Expressions and Virtual Extension Methods | Final |
| [JEP 107](https://openjdk.org/jeps/107) | Bulk Data Operations for Collections | Final |
| [JEP 150](https://openjdk.org/jeps/150) | Date & Time API | Final |

## Java 9

| JEP | Feature | Status |
|---|---|---|
| [JEP 261](https://openjdk.org/jeps/261) | Module System | Final, Executable Example |
| [JEP 269](https://openjdk.org/jeps/269) | Convenience Factory Methods for Collections | Final |
| [JEP 102](https://openjdk.org/jeps/102) | Process API Updates | Final |
| [JEP 259](https://openjdk.org/jeps/259) | Stack-Walking API | Final |
| [JEP 213](https://openjdk.org/jeps/213) | Milling Project Coin | Final |

## Java 10

| JEP | Feature | Status |
|---|---|---|
| [JEP 286](https://openjdk.org/jeps/286) | Local-Variable Type Inference | Final |

## Java 11

| JEP | Feature | Status |
|---|---|---|
| [JEP 321](https://openjdk.org/jeps/321) | HTTP Client | Final |
| [JEP 323](https://openjdk.org/jeps/323) | Local-Variable Syntax for Lambda Parameters | Final |

## Java 12

| JEP | Feature | Status |
|---|---|---|
| [JEP 325](https://openjdk.org/jeps/325) | Switch Expressions | Preview |
| [JEP 334](https://openjdk.org/jeps/334) | JVM Constants API | Final |

## Java 13

| JEP | Feature | Status |
|---|---|---|
| [JEP 354](https://openjdk.org/jeps/354) | Switch Expressions | Preview |
| [JEP 355](https://openjdk.org/jeps/355) | Text Blocks | Preview |

## Java 14

| JEP | Feature | Status |
|---|---|---|
| [JEP 361](https://openjdk.org/jeps/361) | Switch Expressions | Final |
| [JEP 358](https://openjdk.org/jeps/358) | Helpful NullPointerExceptions | Runtime |
| [JEP 359](https://openjdk.org/jeps/359) | Records | Preview |
| [JEP 305](https://openjdk.org/jeps/305) | Pattern Matching for instanceof | Preview |
| [JEP 368](https://openjdk.org/jeps/368) | Text Blocks | Second Preview |

## Java 15

| JEP | Feature | Status |
|---|---|---|
| [JEP 378](https://openjdk.org/jeps/378) | Text Blocks | Final |
| [JEP 360](https://openjdk.org/jeps/360) | Sealed Classes | Preview |
| [JEP 375](https://openjdk.org/jeps/375) | Pattern Matching for instanceof | Second Preview |
| [JEP 384](https://openjdk.org/jeps/384) | Records | Second Preview |
| [JEP 371](https://openjdk.org/jeps/371) | Hidden Classes | Final, Executable Example |

## Java 16

| JEP | Feature | Status |
|---|---|---|
| [JEP 395](https://openjdk.org/jeps/395) | Records | Final |
| [JEP 394](https://openjdk.org/jeps/394) | Pattern Matching for instanceof | Final |
| [JEP 397](https://openjdk.org/jeps/397) | Sealed Classes | Second Preview |
| [JEP 380](https://openjdk.org/jeps/380) | Unix-Domain Socket Channels | Final, Explanatory Module |
| [JEP 338](https://openjdk.org/jeps/338) | Vector API | Incubator, Notes |
| [JEP 389](https://openjdk.org/jeps/389) | Foreign Linker API | Incubator, Notes |
| [JEP 393](https://openjdk.org/jeps/393) | Foreign-Memory Access API | Third Incubator, Notes |

## Java 17

| JEP | Feature | Status |
|---|---|---|
| [JEP 409](https://openjdk.org/jeps/409) | Sealed Classes | Final |
| [JEP 406](https://openjdk.org/jeps/406) | Pattern Matching for switch | Preview |
| [JEP 356](https://openjdk.org/jeps/356) | Enhanced Pseudo-Random Number Generators | Final |
| [JEP 403](https://openjdk.org/jeps/403) | Strongly Encapsulate JDK Internals | Runtime, Explanatory Module |

## Java 18

| JEP | Feature | Status |
|---|---|---|
| [JEP 400](https://openjdk.org/jeps/400) | UTF-8 by Default | Final |
| [JEP 408](https://openjdk.org/jeps/408) | Simple Web Server | Tooling, Example |
| [JEP 413](https://openjdk.org/jeps/413) | Code Snippets in Java API Documentation | Tooling, Example |
| [JEP 418](https://openjdk.org/jeps/418) | Internet-Address Resolution SPI | Final, Executable Example |

## Java 19

| JEP | Feature | Status |
|---|---|---|
| [JEP 425](https://openjdk.org/jeps/425) | Virtual Threads | Preview |
| [JEP 428](https://openjdk.org/jeps/428) | Structured Concurrency | Incubator, Explanatory module |
| [JEP 405](https://openjdk.org/jeps/405) | Record Patterns | Preview |
| [JEP 427](https://openjdk.org/jeps/427) | Pattern Matching for switch | Preview |
| [JEP 420](https://openjdk.org/jeps/420) | Pattern Matching for switch | Second Preview |
| [JEP 424](https://openjdk.org/jeps/424) | Foreign Function and Memory API | Preview, Notes |

## Java 20

| JEP | Feature | Status |
|---|---|---|
| [JEP 432](https://openjdk.org/jeps/432) | Record Patterns | Preview |
| [JEP 433](https://openjdk.org/jeps/433) | Pattern Matching for switch | Preview |
| [JEP 436](https://openjdk.org/jeps/436) | Virtual Threads | Preview, Notes |
| [JEP 429](https://openjdk.org/jeps/429) | Scoped Values | Incubator, Explanatory module |
| [JEP 437](https://openjdk.org/jeps/437) | Structured Concurrency | Incubator, Explanatory module |
| [JEP 434](https://openjdk.org/jeps/434) | Foreign Function and Memory API | Preview, Notes |
| [JEP 438](https://openjdk.org/jeps/438) | Vector API | Incubator, Explanatory module |

## Java 21

| JEP | Feature | Status |
|---|---|---|
| [JEP 444](https://openjdk.org/jeps/444) | Virtual Threads | Final |
| [JEP 440](https://openjdk.org/jeps/440) | Record Patterns | Final |
| [JEP 441](https://openjdk.org/jeps/441) | Pattern Matching for switch | Final |
| [JEP 431](https://openjdk.org/jeps/431) | Sequenced Collections | Final |
| [JEP 443](https://openjdk.org/jeps/443) | Unnamed Patterns and Variables | Preview |
| [JEP 446](https://openjdk.org/jeps/446) | Scoped Values | Preview, Explanatory module |
| [JEP 453](https://openjdk.org/jeps/453) | Structured Concurrency | Preview, Explanatory module |
| [JEP 452](https://openjdk.org/jeps/452) | Key Encapsulation Mechanism API | Final, Security, Executable example |
| [JEP 442](https://openjdk.org/jeps/442) | Foreign Function and Memory API | Third Preview, Notes |
| [JEP 445](https://openjdk.org/jeps/445) | Unnamed Classes and Instance Main Methods | Preview, Notes |

## Java 22

| JEP | Feature | Status |
|---|---|---|
| [JEP 456](https://openjdk.org/jeps/456) | Unnamed Variables and Patterns | Final |
| [JEP 454](https://openjdk.org/jeps/454) | Foreign Function and Memory API | Final, Example |
| [JEP 463](https://openjdk.org/jeps/463) | Implicitly Declared Classes and Instance Main Methods | Second Preview, Notes |
| [JEP 461](https://openjdk.org/jeps/461) | Stream Gatherers | Preview, Notes |
| [JEP 457](https://openjdk.org/jeps/457) | Class-File API | Preview, Notes |
| [JEP 447](https://openjdk.org/jeps/447) | Statements before super(...) | Preview, Notes |
| [JEP 458](https://openjdk.org/jeps/458) | Launch Multi-File Source-Code Programs | Final, Tooling, Executable Example |
| [JEP 464](https://openjdk.org/jeps/464) | Scoped Values | Preview, Notes |
| [JEP 462](https://openjdk.org/jeps/462) | Structured Concurrency | Preview, Notes |

## Java 23

| JEP | Feature | Status |
|---|---|---|
| [JEP 467](https://openjdk.org/jeps/467) | Markdown Documentation Comments | Tooling, Executable Example |
| [JEP 477](https://openjdk.org/jeps/477) | Implicitly Declared Classes and Instance Main Methods | Third Preview, Notes |
| [JEP 455](https://openjdk.org/jeps/455) | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
| [JEP 476](https://openjdk.org/jeps/476) | Module Import Declarations | Preview, Notes |
| [JEP 482](https://openjdk.org/jeps/482) | Flexible Constructor Bodies | Preview, Notes |
| [JEP 473](https://openjdk.org/jeps/473) | Stream Gatherers | Preview, Notes |
| [JEP 466](https://openjdk.org/jeps/466) | Class-File API | Preview, Notes |
| [JEP 481](https://openjdk.org/jeps/481) | Scoped Values | Preview, Notes |
| [JEP 480](https://openjdk.org/jeps/480) | Structured Concurrency | Preview, Notes |
| [JEP 471](https://openjdk.org/jeps/471) | Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal | Runtime, Notes |
| [JEP 474](https://openjdk.org/jeps/474) | ZGC: Generational Mode by Default | Runtime, Notes |

## Java 24

| JEP | Feature | Status |
|---|---|---|
| [JEP 485](https://openjdk.org/jeps/485) | Stream Gatherers | Final |
| [JEP 484](https://openjdk.org/jeps/484) | Class-File API | Final, Example |
| [JEP 486](https://openjdk.org/jeps/486) | Permanently Disable the Security Manager | Runtime, Executable Example |
| [JEP 491](https://openjdk.org/jeps/491) | Synchronize Virtual Threads without Pinning | Runtime, Executable Example |
| [JEP 496](https://openjdk.org/jeps/496) | Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism | Final, Security, Example |
| [JEP 497](https://openjdk.org/jeps/497) | Quantum-Resistant Module-Lattice-Based Digital Signature Algorithm | Final, Security, Example |
| [JEP 483](https://openjdk.org/jeps/483) | Ahead-of-Time Class Loading & Linking | Runtime, Tooling, Executable Example |
| [JEP 478](https://openjdk.org/jeps/478) | Key Derivation Function API | Preview, Security, Notes |
| [JEP 492](https://openjdk.org/jeps/492) | Flexible Constructor Bodies | Preview, Notes |
| [JEP 494](https://openjdk.org/jeps/494) | Module Import Declarations | Preview, Notes |
| [JEP 488](https://openjdk.org/jeps/488) | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
| [JEP 487](https://openjdk.org/jeps/487) | Scoped Values | Preview, Notes |
| [JEP 499](https://openjdk.org/jeps/499) | Structured Concurrency | Preview, Notes |
| [JEP 495](https://openjdk.org/jeps/495) | Simple Source Files and Instance Main Methods | Fourth Preview, Notes |
| [JEP 450](https://openjdk.org/jeps/450) | Compact Object Headers | Experimental, Notes |

## Java 25

| JEP | Feature | Status |
|---|---|---|
| [JEP 506](https://openjdk.org/jeps/506) | Scoped Values | Final |
| [JEP 513](https://openjdk.org/jeps/513) | Flexible Constructor Bodies | Final |
| [JEP 511](https://openjdk.org/jeps/511) | Module Import Declarations | Final, Tooling, Executable Example |
| [JEP 512](https://openjdk.org/jeps/512) | Compact Source Files and Instance Main Methods | Final, Tooling, Executable Example |
| [JEP 510](https://openjdk.org/jeps/510) | Key Derivation Function API | Final, Security, Example |
| [JEP 507](https://openjdk.org/jeps/507) | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
| [JEP 502](https://openjdk.org/jeps/502) | Stable Values | Preview, Executable Example |
| [JEP 470](https://openjdk.org/jeps/470) | PEM Encodings of Cryptographic Objects | Preview, Security, Executable Example |
| [JEP 505](https://openjdk.org/jeps/505) | Structured Concurrency | Preview, Explanatory Module |
| [JEP 508](https://openjdk.org/jeps/508) | Vector API | Incubator, Explanatory Module |
| [JEP 514](https://openjdk.org/jeps/514) | Ahead-of-Time Command-Line Ergonomics | Runtime, Tooling, Executable Example |
| [JEP 509](https://openjdk.org/jeps/509) | JFR CPU-Time Profiling | Runtime, Experimental, Explanatory Module |
| [JEP 518](https://openjdk.org/jeps/518) | JFR Cooperative Sampling | Runtime, Explanatory Module |
| [JEP 520](https://openjdk.org/jeps/520) | JFR Method Timing & Tracing | Runtime, Executable Example |
| [JEP 519](https://openjdk.org/jeps/519) | Compact Object Headers | Runtime, Explanatory Module |
| [JEP 521](https://openjdk.org/jeps/521) | Generational Shenandoah | Runtime, Explanatory Module |

## Java 26

| JEP | Feature | Status |
|---|---|---|
| [JEP 500](https://openjdk.org/jeps/500) | Prepare to Make Final Mean Final | Runtime, Explanatory Module |
| [JEP 504](https://openjdk.org/jeps/504) | Remove the Applet API | Final, Removal, Explanatory Module |
| [JEP 516](https://openjdk.org/jeps/516) | Ahead-of-Time Object Caching with Any GC | Runtime, Explanatory Module |
| [JEP 517](https://openjdk.org/jeps/517) | HTTP/3 for the HTTP Client API | Final, Notes |
| [JEP 522](https://openjdk.org/jeps/522) | G1 GC: Improve Throughput by Reducing Synchronization | Runtime, Explanatory Module |
| [JEP 524](https://openjdk.org/jeps/524) | PEM Encodings of Cryptographic Objects | Preview, Security, Notes |
| [JEP 525](https://openjdk.org/jeps/525) | Structured Concurrency | Preview, Explanatory Module |
| [JEP 526](https://openjdk.org/jeps/526) | Lazy Constants | Preview, Explanatory Module |
| [JEP 529](https://openjdk.org/jeps/529) | Vector API | Incubator, Explanatory Module |
| [JEP 530](https://openjdk.org/jeps/530) | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
