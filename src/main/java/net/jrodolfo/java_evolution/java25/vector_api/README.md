# Vector API

Java 25 continued the Vector API as a tenth incubator in JEP 508.

This module is an executable incubator-module example. The main Maven build does not compile `jdk.incubator.vector` directly. Instead, `VectorApiTenthIncubatorExamples` writes a small child source file, compiles it with `javac --add-modules jdk.incubator.vector --release 25`, and runs it with `java --add-modules jdk.incubator.vector`.

That keeps the repository build stable while still demonstrating the real Java 25 incubator API.

## 1. What Problem Does This Feature Solve?

Some programs perform the same operation over many values:

- add two arrays of numbers
- compare many bytes
- transform pixels
- process audio samples
- run numeric or machine-learning calculations
- check blocks of data in cryptographic or compression code

The ordinary Java version is usually scalar code. Scalar code works on one value at a time:

```text
result[0] = left[0] + right[0]
result[1] = left[1] + right[1]
result[2] = left[2] + right[2]
result[3] = left[3] + right[3]
```

That is simple and portable, but modern processors can often do more. Many central processing units (CPUs) have vector instructions that apply one operation to several values at once.

## 2. How Was This Commonly Done Before?

Before the Vector API, Java developers commonly relied on one of these approaches:

- write normal loops and hope the just-in-time compiler recognizes a vectorization opportunity
- use native libraries for performance-critical numeric code
- use platform-specific code outside Java

The first approach is convenient but indirect. The developer writes scalar code, and the Java Virtual Machine (JVM) may or may not turn it into vector instructions.

The native-library approach can be fast, but it adds a boundary between Java and non-Java code. That can make deployment, debugging, portability, and testing harder.

## 3. What Did Java Introduce?

The Vector API gives Java code a way to express vector computations directly.

Instead of describing only one operation on one value, code can describe one operation over a group of lanes:

```text
left vector:   [10, 20, 30, 40]
right vector:  [ 1,  2,  3,  4]
operation:     add each lane
result vector: [11, 22, 33, 44]
```

The same source code can still be Java code, while the JVM has a clearer model for compiling it to efficient hardware instructions on supported CPUs.

## 4. Terminology In Plain English

Scalar:

A single value, such as one `int`, one `long`, or one `double`.

Vector:

A group of values handled as one unit by the API. For example, a vector may contain several `int` lanes.

Lane:

One position inside a vector. If a vector contains four `int` values, it has four lanes.

Lane-wise operation:

An operation applied independently to corresponding lanes. Adding two vectors means lane 0 is added to lane 0, lane 1 to lane 1, and so on.

SIMD:

Single Instruction, Multiple Data. This means one instruction performs the same operation across multiple pieces of data.

Incubator API:

An API shipped in the JDK for experimentation and feedback before it becomes a permanent Java SE API. Incubator APIs can change, require explicit modules, and should not be treated like final APIs.

## 5. Syntax Shape

A real Vector API example uses types from the incubator module:

```java
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;
```

The code then loads values from arrays into vectors, performs lane-wise operations, and stores the result back:

```java
IntVector leftVector = IntVector.fromArray(SPECIES, left, index);
IntVector rightVector = IntVector.fromArray(SPECIES, right, index);
IntVector sum = leftVector.add(rightVector);

sum.intoArray(result, index);
```

The important idea is not the exact class names. The important idea is that the Java source explicitly describes vector operations that the JVM can map to CPU vector instructions when the platform supports them.

## 6. How This Repository Runs The Incubator API

The Vector API is still incubating in Java 25.

Using it in a normal Maven build would require adding the incubator module, for example:

```bash
javac --add-modules jdk.incubator.vector ...
java --add-modules jdk.incubator.vector ...
```

That would make this lightweight learning project more complicated for one feature that is not final yet.

That is why this module uses a child process. The repository class itself remains ordinary Java 25-compatible code, while the generated child program imports and executes `jdk.incubator.vector`.

## 7. What The Test Proves

`VectorApiTenthIncubatorExamplesTest` tests the incubator API through a child compiler and child JVM.

The test verifies that:

- the child source compiles only when the `jdk.incubator.vector` module is added
- vector species metadata is visible
- array values are loaded into `IntVector` lanes
- lane-wise addition produces the expected numeric result
- leftover elements are handled by a scalar tail

The test does not prove that a particular CPU instruction was used, and it does not benchmark performance. It proves the API shape and numeric correctness that learners can observe directly.

## 8. Realistic Use Case

Imagine code that adds two large arrays of numbers:

```text
for each index:
    result[index] = left[index] + right[index]
```

Scalar code handles one index at a time. Vector code can handle a group of indexes together when the hardware and JVM can support it.

That does not mean every loop should use the Vector API. It is mainly useful when the same numeric operation is repeated across many values and performance matters.

## 9. When Not To Use The Vector API

Do not use the Vector API for ordinary business logic. Most application code is easier to read as normal scalar Java.

Do not use it just because a loop exists. The extra API complexity is only worth considering when the code is performance-sensitive and naturally fits lane-wise computation.

Do not treat the Java 25 API as final. It is still an incubator API, so it may change in later releases.

## 10. Remember This

The Vector API is Java's explicit model for SIMD-style computation: group several scalar values into lanes, apply one operation across those lanes, and let the JVM map that work to hardware vector instructions when it can.
