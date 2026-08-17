# Vector API Fifth Incubator

Java 20 continued the Vector API as a fifth incubator feature in Java Enhancement Proposal (JEP) 438.

This module is explanatory because the Java 20 API required the `jdk.incubator.vector` module. The later Java 25 module contains the fuller incubator learning guide.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Some programs perform the same operation over many values:

- add two arrays of numbers
- compare many bytes
- transform pixels
- process audio samples
- run numeric or machine-learning calculations
- work through compression or cryptographic blocks

Normal Java loops are usually scalar:

```text
result[0] = left[0] + right[0]
result[1] = left[1] + right[1]
result[2] = left[2] + right[2]
result[3] = left[3] + right[3]
```

Scalar code works on one logical value at a time.

Modern central processing units (CPUs) can often process several values with one vector instruction. That style is often called SIMD: Single Instruction, Multiple Data.

## How Was This Commonly Done Before?

Java developers commonly relied on one of these approaches:

- write ordinary loops and hope the just-in-time compiler can auto-vectorize them
- use native libraries for performance-sensitive numeric code
- write platform-specific code outside Java

Auto-vectorization is useful, but it is indirect. The source code describes scalar operations, and the Java Virtual Machine (JVM) may or may not discover a vector form.

Native libraries can be fast, but they add deployment, debugging, testing, and portability cost.

## What Did Java 20 Continue?

The Vector API gives Java code a direct way to express vector computations.

Instead of saying only:

```text
add one number to one number
```

the code can express:

```text
add this group of lanes to that group of lanes
```

Conceptually:

```text
left vector:   [10, 20, 30, 40]
right vector:  [ 1,  2,  3,  4]
operation:     add each lane
result vector: [11, 22, 33, 44]
```

Java 20 was not the final API. It was the fifth incubator round, meaning the platform was still collecting feedback and refining the API.

## Important Terminology

**Scalar**

A single value, such as one `int`, one `long`, or one `double`.

**Vector**

A group of values handled as one unit by the API.

**Lane**

One position inside a vector. If a vector contains four `int` values, it has four lanes.

**Lane-wise operation**

An operation applied independently to corresponding lanes. Adding two vectors means lane 0 is added to lane 0, lane 1 to lane 1, and so on.

**SIMD**

Single Instruction, Multiple Data. One instruction performs the same operation across multiple pieces of data.

**Incubator API**

An API shipped in the JDK for experimentation and feedback before it becomes a permanent Java SE API. Incubator APIs can change and require explicit modules.

## Why This Module Has Notes Instead Of Java 20 Code

The Java 20 Vector API lived in the `jdk.incubator.vector` module.

Using it directly would require special compile and runtime options such as:

```bash
javac --add-modules jdk.incubator.vector ...
java --add-modules jdk.incubator.vector ...
```

That is not a good fit for this repository's default Maven build. It would make the project harder to run for a historical incubator API shape.

The later learning module is here:

```text
src/main/java/net/jrodolfo/java_evolution/java25/vector_api/README.md
```

This Java 20 module explains the fifth incubator step and the problem the feature was trying to solve.

## What The Test Proves

`VectorApiFifthIncubatorNotesTest` protects the Java 20 explanation.

It checks that the notes preserve:

- the scalar-loop problem
- older reliance on JIT auto-vectorization or native libraries
- SIMD-style lane-wise computation
- realistic performance-sensitive use cases
- Java 20 fifth-incubator status
- the `jdk.incubator.vector` module reason this stays explanatory
- the Java 25 continuation module

The test does not execute the Java 20 incubator API because this project avoids old incubator-module setup.

## Realistic Use Case

Imagine code that adds two large arrays:

```text
for each index:
    result[index] = left[index] + right[index]
```

Scalar code handles one index at a time. Vector code can handle a group of indexes together when the hardware and JVM can support it.

That matters most in code where the same numeric operation repeats over large amounts of data.

## When Not To Use It

Do not use the Vector API for ordinary business logic.

Most application code is clearer as normal Java. The extra API complexity is only worth considering when the code is performance-sensitive and naturally fits lane-wise computation.

Also, do not treat the Java 20 API as final. It was still incubating.

## Remember This

Java 20 continued the Vector API so Java could explore explicit SIMD-style computation: group scalar values into lanes, apply one operation across those lanes, and let the JVM map that work to hardware vector instructions when possible.
