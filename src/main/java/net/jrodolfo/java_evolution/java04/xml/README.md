# XML Processing

J2SE 1.4 added standard XML processing support through JAXP.

## 1. What Problem Does This Feature Solve?

XML was a common integration format, and Java applications needed standard parsing and transformation APIs.

## 2. What Did Java Introduce?

Java 4 included standard APIs for DOM, SAX, and XSLT-style processing through JAXP.

## 3. What Does The Example Show?

`JaxpExamples` uses trusted in-memory XML to show the core platform APIs:

- parsing XML text into a DOM `Document`
- reading root elements, attributes, and child text
- transforming a DOM document back to XML text

The example stays intentionally small. It does not use remote schemas, network access, external files, or complex XPath expressions, because the release-level lesson is that XML parsing and transformation became standard Java platform capabilities.

## 4. Remember This

JAXP made XML processing a standard Java platform capability in an era when XML was central to enterprise integration.
