# Glossary

This glossary explains acronyms and recurring terms used across the repository. The explanations are intentionally practical: they describe what each term means when reading or running this project.

| Term | Stands for | Meaning in this project |
|---|---|---|
| AES | Advanced Encryption Standard | A widely used symmetric encryption algorithm. In this project, AES is the example of the fast encryption used after key agreement or key derivation has produced suitable key material. |
| AES-256 | Advanced Encryption Standard with a 256-bit key | A common AES key size. In this project, 32 derived bytes can be used as AES-256 key material. |
| ALL-UNNAMED | All unnamed modules | A special Java module-system target used by command-line options such as `--add-opens` when non-modular classpath code needs access during migration or testing. |
| ARM | Advanced RISC Machines | A processor architecture family. In this project, it mainly appears through ARM64 platform notes for Apple Silicon and Linux virtual machines. |
| AOT | Ahead-of-Time | Work done before normal application startup, such as preloading or linking classes to reduce startup cost. |
| API | Application Programming Interface | A public type, method, module, or library contract that code can call. |
| ABI | Application Binary Interface | The platform rules that describe how compiled code calls functions and represents values in memory; relevant to the FFM linker's native function signatures. |
| Applet API | Applet Application Programming Interface | Browser-embedded Java client technology from the plugin era. It was deprecated for removal and removed from the platform in Java 26. |
| ARM64 | 64-bit ARM architecture | The CPU architecture used by modern Apple Silicon machines and some Linux/Windows systems. It matters when installing JDKs or tools such as `lychee`. |
| AWT | Abstract Window Toolkit | Java's original GUI toolkit. In this project, AWT appears as early platform background and as part of the history that led to Swing and Java 2D. |
| CI | Continuous Integration | Automated checks run by GitHub Actions, such as build, link validation, and JavaDoc publishing. |
| CORBA | Common Object Request Broker Architecture | An older distributed-object technology whose Java EE/JDK-bundled modules matter mainly for legacy Java migration discussions. |
| CPU | Central Processing Unit | The processor running Java code. It appears in discussions about platform architecture, virtual threads, and vector computation. |
| CSS | Cascading Style Sheets | A web styling language mentioned when examples use embedded text formats. |
| DHKEM | Diffie-Hellman Key Encapsulation Mechanism | The KEM algorithm name used by the Java 21 example with X25519 keys. |
| DNS | Domain Name System | The naming system that resolves host names to network addresses; relevant to the Java 18 `InetAddress` resolver SPI notes. |
| DOM | Document Object Model | A tree-oriented XML API. In the Java 4 XML notes, DOM contrasts with streaming or event-oriented XML processing. |
| EE | Enterprise Edition | Usually appears as Java EE, the older enterprise Java platform name relevant to migration from older JDKs. |
| FFM | Foreign Function and Memory | Java APIs for calling native code and working with memory outside the Java heap. |
| GC | Garbage Collection | JVM memory management that finds and reclaims objects the application no longer uses. |
| GNU | GNU's Not Unix | Appears in GNU Make, the `make` implementation used for the documented local workflow. |
| HKDF | HMAC-based Key Derivation Function | The key derivation algorithm demonstrated by the Java 25 example. |
| HKDF-SHA256 | HMAC-based Key Derivation Function using SHA-256 | The concrete KDF algorithm name used by the Java 25 example. |
| HMAC | Hash-based Message Authentication Code | A cryptographic construction used by HKDF to derive key material from input secret material. |
| HTML | Hypertext Markup Language | A web document format used in examples about text blocks, JavaDoc, and the Simple Web Server. |
| HTTP | Hypertext Transfer Protocol | The web protocol demonstrated by the Java 11 HTTP Client examples. |
| HTTP/3 | Hypertext Transfer Protocol version 3 | The HTTP version added to the Java 26 HTTP Client API. It runs over QUIC rather than TCP. |
| I/O | Input/Output | Reading from or writing to external resources such as files, sockets, HTTP services, or processes. |
| ID | Identifier | A value that identifies something, such as a request ID in scoped-value examples. |
| IDE | Integrated Development Environment | A developer tool useful for browsing Java packages, tests, and generated documentation. |
| INET | Internet Protocol family | The protocol family used by Internet sockets in Java networking APIs. In this project, it appears when contrasting `StandardProtocolFamily.INET` with Unix-domain sockets. |
| Incubator | Incubator Module or API | A non-final API delivered for experimentation before it is finalized, changed, or removed. |
| IO | Input/Output | Reading from or writing to external resources. In compact source-file examples, `IO.println(...)` is a small console output helper available to simple source programs. |
| JAAS | Java Authentication and Authorization Service | Java security APIs for representing authenticated subjects and checking permissions. In this project, JAAS appears in the Java 4 security integration notes. |
| JAR | Java Archive | A packaged Java artifact produced by build tools such as Maven. |
| JAXB | Java Architecture for XML Binding | A Java/XML binding technology used to map XML documents to Java objects. It appears in the Java 6 web-service notes as part of the older bundled enterprise-facing stack. |
| JAX-WS | Java API for XML Web Services | The Java API for SOAP-based XML web services. It appears in the Java 6 web-service notes as platform support that later moved out of the core JDK. |
| JavaDoc | Java Documentation | Documentation generated from Java source comments, usually browsed as HTML. |
| JDBC | Java Database Connectivity | Java's standard database access API. In this project, JDBC is demonstrated through driver registration and `DriverManager` dispatch without requiring a database fixture. |
| JDK | Java Development Kit | The Java installation used to compile, test, document, and run this project. |
| JEP | JDK Enhancement Proposal | The OpenJDK process and design record used to propose, track, and document significant JDK changes. In this project, JEP links are the authoritative source for when a Java feature was previewed, finalized, changed, or removed. |
| JFR | Java Flight Recorder | JVM observability tooling for recording runtime events and performance data. |
| JIT | Just-In-Time | JVM compilation work performed while a program runs, often discussed with startup, warmup, and runtime optimization. |
| JAXP | Java API for XML Processing | The standard XML processing API added to the Java platform in the Java 4 era. |
| JCE | Java Cryptography Extension | Java cryptography APIs integrated into the standard platform in the Java 4 era. |
| JMX | Java Management Extensions | Java APIs and conventions for monitoring and managing applications and the JVM through managed beans, commonly called MBeans or MXBeans. |
| JNDI | Java Naming and Directory Interface | A naming and directory API used by older enterprise Java systems to look up resources. |
| JNI | Java Native Interface | The older native-integration mechanism that the Foreign Function and Memory API improves on for many use cases. |
| JPMS | Java Platform Module System | The Java 9 module system used to declare module dependencies and exported packages with `module-info.java`, `requires`, and `exports`. |
| JSSE | Java Secure Socket Extension | Java secure-socket APIs integrated into the standard platform in the Java 4 era. |
| JSR | Java Specification Request | The Java Community Process mechanism used for many older Java features before the modern JEP process became the main lookup path in this repository. |
| JSR 199 | Java Compiler API | The Java 6 specification for invoking a Java compiler from Java code through `javax.tools`. |
| JSR 223 | Scripting for the Java Platform | The Java 6 scripting API that lets Java applications host pluggable script engines through `javax.script`. |
| JSR 334 | Small Enhancements to the Java Programming Language | The Java 7 Project Coin specification covering features such as try-with-resources, multi-catch, diamond, strings in switch, binary literals, and numeric underscores. |
| JRE | Java Runtime Environment | The runtime pieces needed to run Java applications; modern JDK installs include the runtime. |
| JSON | JavaScript Object Notation | A structured text data format often embedded in Java strings or text blocks. |
| JUnit | Java unit testing framework | The test framework used for executable examples in this repository. |
| JVM | Java Virtual Machine | The runtime engine that executes compiled Java bytecode. |
| KDF | Key Derivation Function | A cryptographic API for deriving keys from secret material and context. |
| KEM | Key Encapsulation Mechanism | A cryptographic mechanism for establishing shared secret material by sending an encapsulation message instead of transmitting the secret directly. |
| Lazy Constants | Lazy Constants | A Java 26 preview API for values that initialize on demand and then behave like constants. |
| LTS | Long-Term Support | A Java release line commonly supported for a longer maintenance window by vendors. |
| Maven | Apache Maven | The build tool used here for dependency resolution, compilation, tests, and documentation tasks. |
| MIT | Massachusetts Institute of Technology | Appears in MIT License, the open-source license used by this repository. |
| ML-DSA | Module-Lattice-Based Digital Signature Algorithm | A Java 24 quantum-resistant signature algorithm used to sign and verify messages. |
| ML-KEM | Module-Lattice-Based Key Encapsulation Mechanism | A Java 24 quantum-resistant key encapsulation algorithm used to establish shared secret material. |
| NIO | New Input/Output | Java's non-blocking and buffer-oriented I/O APIs, relevant to socket channels and filesystem work. |
| NIO.2 | New Input/Output 2 | The Java 7 filesystem API centered on `Path`, `Files`, file attributes, directory walking, and filesystem providers. |
| Notes | Notes Class | A source file that documents a Java feature when a small portable executable example would be misleading, platform-specific, or too large. |
| PEM | Privacy-Enhanced Mail | A text encoding format commonly used for cryptographic keys and certificates. |
| Preview | Preview Feature | A Java language or API feature available for feedback before becoming final or changing shape. |
| Project Coin | Small Java language enhancements | The Java 7 effort behind small language improvements such as try-with-resources, multi-catch, diamond, strings in switch, binary literals, and numeric underscores. |
| QUIC | Quick UDP Internet Connections | A modern transport protocol used by HTTP/3. In this project, it appears in the Java 26 HTTP/3 notes. |
| Removal | Removal | A Java platform change where a deprecated API or capability is removed from the JDK, such as the Applet API in Java 26. |
| RISC | Reduced Instruction Set Computer | A processor design style referenced by the ARM acronym. |
| RMI | Remote Method Invocation | A Java distributed-object technology mentioned as background when discussing native calls and remote procedure styles. |
| RPC | Remote Procedure Call | A style of calling code across process or network boundaries; useful contrast for local native calls through FFM. |
| SAX | Simple API for XML | An event-driven XML parsing API. In the Java 4 XML notes, SAX is useful contrast to DOM because it streams parser events instead of building a full tree. |
| SDKMAN | Software Development Kit Manager | A tool commonly used on Linux/macOS to install and switch between JDK versions. |
| SE | Standard Edition | Java Standard Edition, the core Java platform specification for the language, JVM, and standard APIs. |
| SHA-256 | Secure Hash Algorithm 256-bit | A cryptographic hash function used as the hash primitive in `HKDF-SHA256`. |
| SIMD | Single Instruction, Multiple Data | A processor execution model where one operation applies to multiple data values at once; relevant to the Vector API notes. |
| SPI | Service Provider Interface | An extension point that lets libraries or runtime components plug in implementations, such as the Java 18 `InetAddress` resolver SPI. |
| SQL | Structured Query Language | A database query language often embedded in Java strings or text blocks. |
| Spring Boot | Spring Boot | The lightweight application shell and Maven parent used by this project. |
| TCP | Transmission Control Protocol | A reliable network protocol; mentioned when comparing sockets, local servers, and process communication. |
| URL | Uniform Resource Locator | A web address, used for documentation links, HTTP examples, and GitHub Pages. |
| UTF-8 | Unicode Transformation Format, 8-bit | The standard default charset starting in Java 18 and the encoding assumed by this project documentation. |
| VM | Virtual Machine | A general term for a runtime or machine abstraction; in this project it usually appears as part of JVM discussions. |
| X25519 | X25519 elliptic-curve Diffie-Hellman function | The key-agreement key type used by the Java 21 DHKEM example. |
| XML | Extensible Markup Language | A structured text data format often embedded in Java strings or text blocks. |
| XSLT | Extensible Stylesheet Language Transformations | An XML transformation language. In the Java 4 XML notes, XSLT represents the transformation side of the standard XML-processing stack. |
| ZGC | Z Garbage Collector | A low-latency garbage collector discussed in Java runtime and GC notes. |
| ZIP | ZIP archive format | A compressed archive format used in examples that treat archives as filesystems. |
