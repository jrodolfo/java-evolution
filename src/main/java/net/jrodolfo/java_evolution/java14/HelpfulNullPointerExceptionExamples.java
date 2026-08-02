package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates helpful {@link NullPointerException} messages, introduced in
 * Java 14.
 *
 * <p>
 * Before Java 14, a null pointer failure inside a chained expression often
 * produced a generic message. Developers had to debug the expression to figure
 * out which value was {@code null}.
 * </p>
 *
 * <p>
 * Java 14 improved JVM diagnostics so the exception message can describe which
 * part of a chained access was {@code null}. The feature does not prevent null
 * values, but it makes failures easier to diagnose.
 * </p>
 */
public class HelpfulNullPointerExceptionExamples {

	/**
	 * Intentionally dereferences a nested null value so tests can inspect the
	 * helpful exception message.
	 *
	 * @return never returns when the default sample data is used
	 */
	public String triggerHelpfulNullPointerException() {
		Customer customer = new Customer(null);
		return customer.address().city().toUpperCase();
	}

	/**
	 * Customer with an address reference used to trigger nested null access.
	 */
	public static class Customer {
		private final Address address;

		/**
		 * Creates a customer.
		 *
		 * @param address the customer's address, possibly {@code null} for the example
		 */
		public Customer(Address address) {
			this.address = address;
		}

		/**
		 * Returns the address reference.
		 *
		 * @return the customer's address
		 */
		public Address address() {
			return address;
		}
	}

	/**
	 * Address with a city value used by the null-pointer diagnostic example.
	 */
	public static class Address {
		private final String city;

		/**
		 * Creates an address.
		 *
		 * @param city the city name
		 */
		public Address(String city) {
			this.city = city;
		}

		/**
		 * Returns the city name.
		 *
		 * @return the city name
		 */
		public String city() {
			return city;
		}
	}
}
