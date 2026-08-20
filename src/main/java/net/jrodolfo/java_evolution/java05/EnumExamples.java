package net.jrodolfo.java_evolution.java05;

/**
 * Demonstrates Java 5 typesafe enums.
 */
public class EnumExamples {

	/**
	 * Calculates the numeric score for a ticket priority.
	 *
	 * @param priority priority to inspect
	 * @return stable priority score
	 */
	public int score(Priority priority) {
		return priority.score();
	}

	/**
	 * Returns a display label from enum behavior instead of a parallel switch or
	 * map.
	 *
	 * @param priority priority to inspect
	 * @return display label
	 */
	public String label(Priority priority) {
		return priority.label();
	}

	/**
	 * Real enum values can carry fields and methods while remaining a closed set
	 * of constants.
	 */
	public enum Priority {
		LOW(1, "low priority"),
		NORMAL(5, "normal priority"),
		URGENT(10, "urgent priority");

		private final int score;
		private final String label;

		Priority(int score, String label) {
			this.score = score;
			this.label = label;
		}

		int score() {
			return score;
		}

		String label() {
			return label;
		}
	}
}
