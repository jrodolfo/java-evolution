package net.jrodolfo.java_evolution.java08;

/**
 * Demonstrates default methods, introduced in Java 8 so interfaces could evolve
 * without forcing every existing implementation to add the new methods
 * immediately.
 */
public class DefaultMethodExamples {

	/**
	 * Creates a user that relies on the default methods from {@link Named}.
	 *
	 * @param name the user's name
	 * @return a user implementation with no custom default-method overrides
	 */
	public Named regularUser(String name) {
		return new RegularUser(name);
	}

	/**
	 * Creates a user that overrides one default method.
	 *
	 * @param name the user's name
	 * @return a user implementation with a custom display name
	 */
	public Named adminUser(String name) {
		return new AdminUser(name);
	}

	/**
	 * Interface with one abstract method and two default methods.
	 *
	 * <p>
	 * Implementations only need to provide {@link #name()}, while
	 * {@link #displayName()} and {@link #initials()} are inherited automatically.
	 * </p>
	 */
	public interface Named {
		/**
		 * @return the raw name supplied by the implementation
		 */
		String name();

		/**
		 * @return a user-friendly name for display
		 */
		default String displayName() {
			return name();
		}

		/**
		 * @return the first letter of each name part in uppercase
		 */
		default String initials() {
			String[] parts = name().trim().split("\\s+");
			StringBuilder initials = new StringBuilder();
			for (String part : parts) {
				if (!part.isEmpty()) {
					initials.append(Character.toUpperCase(part.charAt(0)));
				}
			}
			return initials.toString();
		}
	}

	/**
	 * Implementation that inherits all default behavior from {@link Named}.
	 */
	public static class RegularUser implements Named {
		private final String name;

		public RegularUser(String name) {
			this.name = name;
		}

		@Override
		public String name() {
			return name;
		}
	}

	/**
	 * Implementation that overrides a default method while keeping the others.
	 */
	public static class AdminUser implements Named {
		private final String name;

		public AdminUser(String name) {
			this.name = name;
		}

		@Override
		public String name() {
			return name;
		}

		@Override
		public String displayName() {
			return "Admin: " + name();
		}
	}
}
