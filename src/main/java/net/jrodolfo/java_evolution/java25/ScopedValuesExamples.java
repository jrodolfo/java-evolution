package net.jrodolfo.java_evolution.java25;

/**
 * Demonstrates scoped values, finalized in Java 25 by JEP 506.
 *
 * <p>
 * Scoped values solve a common context-passing problem. Data such as a user,
 * request ID, or tenant often needs to be available through a call chain without
 * adding parameters everywhere. Compared with mutable thread-local state,
 * scoped values bind immutable data to a bounded dynamic scope, making cleanup
 * and ownership easier to reason about.
 * </p>
 */
public class ScopedValuesExamples {

	/**
	 * Scoped value used by the example. It is intentionally private so all access
	 * goes through methods that demonstrate binding and lookup behavior.
	 */
	private static final ScopedValue<String> USER = ScopedValue.newInstance();

	/**
	 * Binds a scoped value for the duration of one call.
	 *
	 * @param user user name to bind
	 * @return value observed inside the scope
	 * @throws Exception when the scoped call fails
	 */
	public String userInsideScope(String user) throws Exception {
		return ScopedValue.where(USER, user)
				.call(() -> "current user=" + USER.get());
	}

	/**
	 * Checks whether the scoped value is bound outside a scope.
	 *
	 * @return whether the scoped value is currently bound
	 */
	public boolean userIsBoundOutsideScope() {
		return USER.isBound();
	}
}
