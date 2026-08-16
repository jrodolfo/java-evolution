# Unix-Domain Socket Channels

Java 16 introduced Unix-domain socket channel support in JEP 380.

This feature is about communication between processes running on the same machine. It is not a new Java language syntax feature. It is a Java networking API improvement.

## What Problem Does This Feature Solve?

Sometimes two local processes need to talk to each other:

```text
desktop app -> local helper process
build tool   -> local daemon
database     -> local administration tool
server       -> local sidecar process
```

One common answer is to open a TCP port on `localhost`, such as `127.0.0.1:9000`.

That works, but it still uses the network socket model:

```text
host + port
```

For communication that never leaves the machine, many operating systems also support Unix-domain sockets. A Unix-domain socket uses a local file-system path as the address:

```text
/tmp/example.sock
```

That means the communication endpoint is identified by a path on the local machine rather than by a network host and port.

## How Was This Commonly Done Before?

Before Java 16, Java's standard non-blocking socket channel APIs focused on Internet sockets such as TCP.

If a Java application wanted Unix-domain socket behavior, it usually needed one of these approaches:

- use a platform-specific library
- use native code through the Java Native Interface
- communicate through TCP on `localhost`
- avoid the feature entirely

Those choices made local inter-process communication less portable and less visible in the standard Java API.

## What Did Java Introduce?

Java 16 added Unix-domain socket channel support to the Java NIO networking APIs.

The important protocol family is:

```java
StandardProtocolFamily.UNIX
```

The address is represented with:

```java
UnixDomainSocketAddress
```

Conceptually, code can create a socket channel for local path-based communication instead of host-and-port communication:

```java
SocketChannel.open(StandardProtocolFamily.UNIX);
```

The exact setup depends on the operating system and the path used for the socket.

## Terminology

Inter-process communication means communication between separate programs or processes.

TCP socket means a network socket addressed with a host and port, such as `127.0.0.1:8080`.

Unix-domain socket means a local socket addressed with a file-system path, such as `/tmp/app.sock`.

Protocol family means the address family used by the socket API. `StandardProtocolFamily.INET` is used for Internet Protocol sockets. `StandardProtocolFamily.UNIX` is used for Unix-domain sockets.

## Why This Repository Keeps It As Notes

A faithful executable example would need to create local socket paths and rely on operating-system support.

That is possible, but it would make the ordinary test suite more dependent on local platform behavior. This repository already has enough platform-sensitive examples in later Java versions. For Java 16, the most useful lesson is the model:

```text
TCP socket:
    host + port

Unix-domain socket:
    local path
```

The notes class preserves the concepts without making every learner debug local socket behavior.

## Realistic Use Case

A local database, daemon, command-line tool, or desktop application can expose a Unix-domain socket for local clients. The process avoids opening a network port and uses an address that exists only on the local machine.

## When Not To Use It

Do not use Unix-domain sockets when the processes are on different machines. They are local to one machine. For remote communication, TCP sockets or higher-level protocols are the normal choice.

Also do not use this API just because it is new. If a simple HTTP endpoint on `localhost` is clearer for your application and security model, that may still be the better design.

## Remember This

Unix-domain socket channels let Java use local path-based sockets for communication between processes on the same machine. Java 16 made that capability part of the standard Java NIO API through `StandardProtocolFamily.UNIX`.
