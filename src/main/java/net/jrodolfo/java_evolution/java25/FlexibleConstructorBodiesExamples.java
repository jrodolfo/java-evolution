package net.jrodolfo.java_evolution.java25;

/**
 * Demonstrates flexible constructor bodies, finalized in Java 25.
 *
 * <p>
 * Constructors can now execute safe validation or preparation before invoking
 * another constructor. This example uses a compact validation helper before
 * delegating to another constructor in the same class.
 * </p>
 */
public class FlexibleConstructorBodiesExamples {

	/**
	 * Creates a validated account.
	 *
	 * @param owner account owner
	 * @return account object
	 */
	public Account account(String owner) {
		return new Account(owner);
	}

	public static class Account {
		private final String owner;
		private final boolean active;

		public Account(String owner) {
			String validatedOwner = validate(owner);
			this(validatedOwner, true);
		}

		private Account(String owner, boolean active) {
			this.owner = owner;
			this.active = active;
		}

		private static String validate(String owner) {
			if (owner == null || owner.isBlank()) {
				throw new IllegalArgumentException("owner is required");
			}
			return owner.strip();
		}

		public String owner() {
			return owner;
		}

		public boolean active() {
			return active;
		}
	}
}
