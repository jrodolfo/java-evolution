package net.jrodolfo.java_evolution.java02.strict_floating_point;

/**
 * Explains {@code strictfp}, introduced in Java 2.
 */
public class StrictFloatingPointNotes {

	public String problemSolved() {
		return "floating-point calculations could differ when processors used wider intermediate precision";
	}

	public String featureShape() {
		return "strictfp could be applied to classes, interfaces, or methods to request strict floating-point semantics";
	}

	public String modernContext() {
		return "Java 17 restored always-strict floating-point semantics, so JDK 25 does not show the old distinction clearly";
	}
}
