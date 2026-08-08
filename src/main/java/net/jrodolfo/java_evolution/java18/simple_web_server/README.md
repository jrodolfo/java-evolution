# Simple Web Server

Java 18 introduced the Simple Web Server in JEP 408.

Most Java developers do not need a full application server when they only want to preview generated HTML, browse documentation, or share a folder during a local demo. Before Java 18, that small task usually meant installing another tool, using an IDE feature, writing a tiny server, or starting a larger framework than the job required.

Java 18 added two related pieces:

- `jwebserver`, a command-line tool for serving static files
- `SimpleFileServer`, a Java API for creating the same kind of server from code

## The Problem

Static files are files that can be served as they are:

- generated JavaDoc
- generated reports
- HTML pages
- CSS and JavaScript files
- images
- downloaded examples

The problem is not complex web application development. The problem is much smaller:

```text
I have a directory of files.
I want to open them through HTTP.
I do not want to install or configure another server.
```

That is exactly where `jwebserver` fits:

```bash
jwebserver --port 8000 --directory public
```

## What This Example Does

The executable example uses the Java API added with the same feature:

```java
SimpleFileServer.createFileServer(address, rootDirectory, OutputLevel.NONE)
```

The test creates a temporary directory, writes a small HTML file, starts the server on a local ephemeral port, and requests the file with `HttpClient`.

The flow is:

```text
temporary directory
  -> SimpleFileServer
  -> local HTTP request
  -> static file response
```

## What This Is Not

The Simple Web Server is not a replacement for Spring Boot, Tomcat, nginx, authentication, authorization, routing, or production web infrastructure.

It intentionally solves a local tooling problem. It is useful when the smallest correct tool is "serve this folder over HTTP."

## Why This Matters For Learning

This repository generates JavaDoc and contains many Markdown files. A learner can use the Simple Web Server idea to browse generated output locally without adding another dependency.

The feature also shows that Java evolves as a platform, not only as a language. Java 18 did not add a big syntax feature here; it added a practical tool for a common developer workflow.
