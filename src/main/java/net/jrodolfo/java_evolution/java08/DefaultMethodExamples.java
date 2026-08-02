package net.jrodolfo.java_evolution.java08;

/**
 * Demonstrates default methods, introduced in Java 8 so interfaces could evolve
 * without forcing every existing implementation to add new methods
 * immediately.
 *
 * <p>
 * Before Java 8, adding a method to an interface was a breaking change for
 * every implementation. That was a serious problem for old, widely used JDK
 * interfaces because the platform needed to add new behavior while preserving
 * compatibility with existing code.
 * </p>
 *
 * <p>
 * Default methods solve this by allowing an interface to provide an inherited
 * method body. Implementations can accept the default behavior or override it
 * when they need something more specific.
 * </p>
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
		 * Supplies the raw name owned by the implementation.
		 *
		 * @return the raw name supplied by the implementation
		 */
		String name();

		/**
		 * Returns the display form of the name.
		 *
		 * @return a user-friendly name for display
		 */
		default String displayName() {
			return name();
		}

		/**
		 * Builds initials from the display name without requiring each implementation
		 * to duplicate the same string-processing logic.
		 *
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

		/**
		 * Creates a regular user that inherits all default methods.
		 *
		 * @param name the user's name
		 */
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

		/**
		 * Creates an admin user that overrides the display-name behavior.
		 *
		 * @param name the user's name
		 */
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
