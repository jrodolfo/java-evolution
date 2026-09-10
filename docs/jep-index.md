# JEP Index

This index centralizes the JEP links used by the repository. It is a lookup table, not a replacement for the version READMEs. For release months and historical naming context, use [java-release-timeline.md](java-release-timeline.md).

Java 27 entries describe features in the current pre-GA JDK 27 build; they do
not mean that the Java 27 platform has reached general availability.

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
| <a target="_blank" rel="noopener noreferrer" href="https://courses.cs.washington.edu/courses/cse341/99wi/java/tutorial/post1.0/whatsnew/index.html">What's New in JDK 1.1</a> | Inner classes, reflection, serialization, JDBC, RMI, JavaBeans, AWT updates, JAR files, JNI, internationalization | Final, Examples |
| <a target="_blank" rel="noopener noreferrer" href="https://www.oracle.com/java/technologies/javase/javabeans-spec.html">JavaBeans specification</a> | JavaBeans component conventions | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://www.oracle.com/java/technologies/javase/serializationfaq-jsp.html">Object Serialization FAQ</a> | Object serialization | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html">Java language enhancements history</a> | `strictfp` and assertions | Final, Executable Examples |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/cd/E19683-01/806-7930/features-2d/index.html">Java 2 platform documentation</a> | Java 2D | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/relnotes/features.html">J2SE 1.4 new features and enhancements</a> | NIO, regular expressions, XML/JAXP, integrated security APIs, logging, preferences, chained exceptions | Final, Examples and Preferences Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html">Oracle J2SE 5.0 new features</a> | Generics, enhanced for loop, autoboxing, enums, varargs, static import, annotations, concurrency utilities | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html">Formatter API</a> | Formatted output | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://www.oracle.com/java/technologies/javase/features.html">Oracle Java SE 6 features</a> | Navigable collections, scripting, compiler API, console, monitoring, web-service support | Final, Examples and Explanatory Modules |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/6/docs/technotes/guides/scripting/">Java SE 6 scripting guide</a> | Scripting support | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/6/docs/api/javax/tools/JavaCompiler.html">JavaCompiler API</a> | Compiler API | Final, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/6/docs/api/java/io/Console.html">Console API</a> | Console API | Final, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://cr.openjdk.org/~darcy/ProjectCoin/ProjectCoin-Documentation-v0.9375.html">Project Coin / JSR 334</a> | Try-with-resources, multi-catch, diamond operator, strings in switch, binary literals, numeric underscores | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/7/docs/webnotes/adoptionGuide/">JDK 7 adoption guide</a> | NIO.2 and Java 7 migration features | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/7/docs/technotes/guides/concurrency/changes7.html">Java SE 7 concurrency enhancements</a> | Fork/join framework | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/7/docs/api/java/lang/invoke/package-summary.html">java.lang.invoke package</a> | `invokedynamic` and method handles | Final, Tooling, Executable Example |

## Java 8

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/126">JEP 126</a> | Lambda Expressions and Virtual Extension Methods | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/107">JEP 107</a> | Bulk Data Operations for Collections | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/150">JEP 150</a> | Date & Time API | Final |

## Java 9

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/261">JEP 261</a> | Module System | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/269">JEP 269</a> | Convenience Factory Methods for Collections | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/102">JEP 102</a> | Process API Updates | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/259">JEP 259</a> | Stack-Walking API | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/213">JEP 213</a> | Milling Project Coin | Final |

## Java 10

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/286">JEP 286</a> | Local-Variable Type Inference | Final |

## Java 11

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/321">JEP 321</a> | HTTP Client | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/323">JEP 323</a> | Local-Variable Syntax for Lambda Parameters | Final |

## Java 12

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/325">JEP 325</a> | Switch Expressions | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/334">JEP 334</a> | JVM Constants API | Final |

## Java 13

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/354">JEP 354</a> | Switch Expressions | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/355">JEP 355</a> | Text Blocks | Preview |

## Java 14

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/361">JEP 361</a> | Switch Expressions | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/358">JEP 358</a> | Helpful NullPointerExceptions | Runtime |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/359">JEP 359</a> | Records | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/305">JEP 305</a> | Pattern Matching for instanceof | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/368">JEP 368</a> | Text Blocks | Second Preview |

## Java 15

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/378">JEP 378</a> | Text Blocks | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/360">JEP 360</a> | Sealed Classes | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/375">JEP 375</a> | Pattern Matching for instanceof | Second Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/384">JEP 384</a> | Records | Second Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/371">JEP 371</a> | Hidden Classes | Final, Executable Example |

## Java 16

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/395">JEP 395</a> | Records | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/394">JEP 394</a> | Pattern Matching for instanceof | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/397">JEP 397</a> | Sealed Classes | Second Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/380">JEP 380</a> | Unix-Domain Socket Channels | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/338">JEP 338</a> | Vector API | Incubator, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/389">JEP 389</a> | Foreign Linker API | Incubator, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/393">JEP 393</a> | Foreign-Memory Access API | Third Incubator, Notes |

## Java 17

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/409">JEP 409</a> | Sealed Classes | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/406">JEP 406</a> | Pattern Matching for switch | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/356">JEP 356</a> | Enhanced Pseudo-Random Number Generators | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/403">JEP 403</a> | Strongly Encapsulate JDK Internals | Runtime, Executable Example |

## Java 18

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/400">JEP 400</a> | UTF-8 by Default | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/408">JEP 408</a> | Simple Web Server | Tooling, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/413">JEP 413</a> | Code Snippets in Java API Documentation | Tooling, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/418">JEP 418</a> | Internet-Address Resolution SPI | Final, Executable Example |

## Java 19

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/425">JEP 425</a> | Virtual Threads | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/428">JEP 428</a> | Structured Concurrency | Incubator, Explanatory module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/405">JEP 405</a> | Record Patterns | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/427">JEP 427</a> | Pattern Matching for switch | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/420">JEP 420</a> | Pattern Matching for switch | Second Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/424">JEP 424</a> | Foreign Function and Memory API | Preview, Notes |

## Java 20

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/432">JEP 432</a> | Record Patterns | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/433">JEP 433</a> | Pattern Matching for switch | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/436">JEP 436</a> | Virtual Threads | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/429">JEP 429</a> | Scoped Values | Incubator, Explanatory module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/437">JEP 437</a> | Structured Concurrency | Incubator, Explanatory module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/434">JEP 434</a> | Foreign Function and Memory API | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/438">JEP 438</a> | Vector API | Incubator, Executable Example |

## Java 21

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/444">JEP 444</a> | Virtual Threads | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/440">JEP 440</a> | Record Patterns | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/441">JEP 441</a> | Pattern Matching for switch | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/431">JEP 431</a> | Sequenced Collections | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/443">JEP 443</a> | Unnamed Patterns and Variables | Preview |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/446">JEP 446</a> | Scoped Values | Preview, Explanatory module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/453">JEP 453</a> | Structured Concurrency | Preview, Explanatory module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/452">JEP 452</a> | Key Encapsulation Mechanism API | Final, Security, Executable example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/442">JEP 442</a> | Foreign Function and Memory API | Third Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/445">JEP 445</a> | Unnamed Classes and Instance Main Methods | Preview, Notes |

## Java 22

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/456">JEP 456</a> | Unnamed Variables and Patterns | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/454">JEP 454</a> | Foreign Function and Memory API | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/463">JEP 463</a> | Implicitly Declared Classes and Instance Main Methods | Second Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/461">JEP 461</a> | Stream Gatherers | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/457">JEP 457</a> | Class-File API | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/447">JEP 447</a> | Statements before super(...) | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/458">JEP 458</a> | Launch Multi-File Source-Code Programs | Final, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/464">JEP 464</a> | Scoped Values | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/462">JEP 462</a> | Structured Concurrency | Preview, Notes |

## Java 23

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/467">JEP 467</a> | Markdown Documentation Comments | Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/477">JEP 477</a> | Implicitly Declared Classes and Instance Main Methods | Third Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/455">JEP 455</a> | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/476">JEP 476</a> | Module Import Declarations | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/482">JEP 482</a> | Flexible Constructor Bodies | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/473">JEP 473</a> | Stream Gatherers | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/466">JEP 466</a> | Class-File API | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/481">JEP 481</a> | Scoped Values | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/480">JEP 480</a> | Structured Concurrency | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/471">JEP 471</a> | Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/474">JEP 474</a> | ZGC: Generational Mode by Default | Runtime, Executable Example |

## Java 24

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/485">JEP 485</a> | Stream Gatherers | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/484">JEP 484</a> | Class-File API | Final, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/486">JEP 486</a> | Permanently Disable the Security Manager | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/491">JEP 491</a> | Synchronize Virtual Threads without Pinning | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/496">JEP 496</a> | Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism | Final, Security, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/497">JEP 497</a> | Quantum-Resistant Module-Lattice-Based Digital Signature Algorithm | Final, Security, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/483">JEP 483</a> | Ahead-of-Time Class Loading & Linking | Runtime, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/478">JEP 478</a> | Key Derivation Function API | Preview, Security, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/492">JEP 492</a> | Flexible Constructor Bodies | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/494">JEP 494</a> | Module Import Declarations | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/488">JEP 488</a> | Primitive Types in Patterns, instanceof, and switch | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/487">JEP 487</a> | Scoped Values | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/499">JEP 499</a> | Structured Concurrency | Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/495">JEP 495</a> | Simple Source Files and Instance Main Methods | Fourth Preview, Notes |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/450">JEP 450</a> | Compact Object Headers | Experimental, Notes |

## Java 25

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/506">JEP 506</a> | Scoped Values | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/513">JEP 513</a> | Flexible Constructor Bodies | Final |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/511">JEP 511</a> | Module Import Declarations | Final, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/512">JEP 512</a> | Compact Source Files and Instance Main Methods | Final, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/510">JEP 510</a> | Key Derivation Function API | Final, Security, Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/507">JEP 507</a> | Primitive Types in Patterns, instanceof, and switch | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/502">JEP 502</a> | Stable Values | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/470">JEP 470</a> | PEM Encodings of Cryptographic Objects | Preview, Security, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/505">JEP 505</a> | Structured Concurrency | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/508">JEP 508</a> | Vector API | Incubator, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/514">JEP 514</a> | Ahead-of-Time Command-Line Ergonomics | Runtime, Tooling, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/509">JEP 509</a> | JFR CPU-Time Profiling | Runtime, Experimental, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/518">JEP 518</a> | JFR Cooperative Sampling | Runtime, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/520">JEP 520</a> | JFR Method Timing & Tracing | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/519">JEP 519</a> | Compact Object Headers | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/521">JEP 521</a> | Generational Shenandoah | Runtime, Explanatory Module |

## Java 26

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/500">JEP 500</a> | Prepare to Make Final Mean Final | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/504">JEP 504</a> | Remove the Applet API | Final, Removal, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/516">JEP 516</a> | Ahead-of-Time Object Caching with Any GC | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/517">JEP 517</a> | HTTP/3 for the HTTP Client API | Final, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/522">JEP 522</a> | G1 GC: Improve Throughput by Reducing Synchronization | Runtime, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/524">JEP 524</a> | PEM Encodings of Cryptographic Objects | Preview, Security, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/525">JEP 525</a> | Structured Concurrency | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/526">JEP 526</a> | Lazy Constants | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/529">JEP 529</a> | Vector API | Incubator, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/530">JEP 530</a> | Primitive Types in Patterns, instanceof, and switch | Preview, Executable Example |

## Java 27

| JEP | Feature | Status |
|---|---|---|
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/523">JEP 523</a> | Make G1 the Default Garbage Collector in All Environments | Runtime, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/527">JEP 527</a> | TLS 1.3 Hybrid Key Exchange | Final, Security, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/531">JEP 531</a> | Lazy Constants | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/532">JEP 532</a> | Primitive Types in Patterns, instanceof, and switch | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/533">JEP 533</a> | Structured Concurrency | Preview, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/534">JEP 534</a> | Compact Object Headers by Default | Runtime, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/536">JEP 536</a> | JFR in-process data redaction | Runtime, Security, Executable Example |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/537">JEP 537</a> | Vector API | Incubator, Explanatory Module |
| <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/538">JEP 538</a> | PEM Encodings of Cryptographic Objects | Preview, Security, Executable Example |
