package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates helpful {@link NullPointerException} messages, introduced in
 * Java 14.
 *
 * <p>
 * The JVM can describe which part of a chained access was {@code null}, making
 * failures easier to diagnose than the older generic null pointer message.
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

	public static class Customer {
		private final Address address;

		public Customer(Address address) {
			this.address = address;
		}

		public Address address() {
			return address;
		}
	}

	public static class Address {
		private final String city;

		public Address(String city) {
			this.city = city;
		}

		public String city() {
			return city;
		}
	}
}
