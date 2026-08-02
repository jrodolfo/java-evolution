package net.jrodolfo.java_evolution.java25;

/**
 * Demonstrates scoped values, finalized in Java 25.
 *
 * <p>
 * Scoped values share immutable data for a bounded dynamic scope, making the
 * dataflow easier to reason about than broad thread-local state.
 * </p>
 */
public class ScopedValuesExamples {

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
