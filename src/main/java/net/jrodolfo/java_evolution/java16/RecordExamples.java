package net.jrodolfo.java_evolution.java16;

/**
 * Demonstrates records, finalized in Java 16.
 *
 * <p>
 * Before records, simple immutable data carriers needed a lot of mechanical
 * code: fields, constructors, accessors, {@code equals}, {@code hashCode}, and
 * {@code toString}. That boilerplate obscured the actual data being modeled.
 * </p>
 *
 * <p>
 * Records solve this by making the state declaration central. The compiler
 * generates a canonical constructor, accessors, {@code equals},
 * {@code hashCode}, and {@code toString} from the record components.
 * </p>
 */
public class RecordExamples {

	/**
	 * Creates a feature record.
	 *
	 * @param name the feature name
	 * @param version the Java version where it became final
	 * @return a feature record
	 */
	public Feature feature(String name, int version) {
		return new Feature(name, version);
	}

	/**
	 * Uses a compact constructor to validate record data.
	 *
	 * @param name the language name
	 * @param version the Java version
	 * @return a validated release record
	 */
	public Release release(String name, int version) {
		return new Release(name, version);
	}

	/**
	 * Simple record with generated accessors and value equality.
	 *
	 * @param name the feature name
	 * @param version the Java version
	 */
	public record Feature(String name, int version) {
	}

	/**
	 * Record with a compact constructor for validation.
	 *
	 * @param name the release name
	 * @param version the release version
	 */
	public record Release(String name, int version) {
		public Release {
			if (name == null || name.isBlank()) {
				throw new IllegalArgumentException("name is required");
			}
			if (version < 1) {
				throw new IllegalArgumentException("version must be positive");
			}
		}
	}
}
