package net.jrodolfo.java_evolution.java25;

/**
 * Demonstrates flexible constructor bodies, finalized in Java 25.
 *
 * <p>
 * Older Java syntax required an explicit constructor invocation to be the first
 * statement. That made validation before delegation awkward. Flexible
 * constructor bodies let constructors perform safe validation or preparation
 * before invoking another constructor, while still preventing unsafe use of the
 * object under construction.
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

	/**
	 * Simple account model used to demonstrate validation before constructor
	 * delegation.
	 */
	public static class Account {
		private final String owner;
		private final boolean active;

		/**
		 * Creates an active account after validating and normalizing the owner.
		 *
		 * @param owner account owner
		 */
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

		/**
		 * Returns the normalized account owner.
		 *
		 * @return owner name
		 */
		public String owner() {
			return owner;
		}

		/**
		 * Returns whether the account is active.
		 *
		 * @return active flag
		 */
		public boolean active() {
			return active;
		}
	}
}
