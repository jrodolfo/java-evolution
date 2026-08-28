/**
 * Explanatory notes for features introduced in Java 26.
 *
 * <p>
 * Java 26 adds HTTP/3 support for the HTTP Client API, starts warning about
 * deep-reflective final-field mutation, removes the Applet API, continues
 * preview and incubator APIs, and advances runtime work around ahead-of-time
 * startup data, G1, and vector computation. This package is mostly notes-only
 * while selected Java 26 features await focused executable-example evaluation.
 * HTTP/3 is executable because its final API can be demonstrated without live
 * networking, final-field restrictions are executable because the warning can be
 * captured in a child JVM, and Applet API removal is executable because the
 * removed package can be verified with a deterministic compiler probe.
 * </p>
 */
package net.jrodolfo.java_evolution.java26;
