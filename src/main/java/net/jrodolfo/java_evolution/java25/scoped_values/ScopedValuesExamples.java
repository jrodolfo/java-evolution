package net.jrodolfo.java_evolution.java25.scoped_values;

/**
 * Demonstrates scoped values, finalized in Java 25 by JEP 506.
 *
 * <p>
 * This example keeps the code intentionally small so the lifecycle is visible:
 * create a scoped-value key, bind a value to it for one operation, read the value
 * while the operation runs, and observe that the binding is gone afterward.
 * </p>
 */
public class ScopedValuesExamples {

	/**
	 * Key used to look up the current user while a scoped operation is running.
	 *
	 * <p>
	 * This object is not the user name. It is a key that can temporarily have a
	 * value associated with it.
	 * </p>
	 */
	private static final ScopedValue<String> USER = ScopedValue.newInstance();

	/**
	 * Binds a user name for the duration of one scoped operation.
	 *
	 * @param user user name to bind while the operation runs
	 * @return message created while the user binding is active
	 * @throws Exception when the scoped operation fails
	 */
	public String userInsideScope(String user) throws Exception {
		var binding = ScopedValue.where(USER, user);
		return binding.call(this::currentUserMessage);
	}

	/**
	 * Reports whether the user key has a value in the current execution scope.
	 *
	 * @return {@code true} when the current code is running inside a user binding
	 */
	public boolean isUserBound() {
		return USER.isBound();
	}

	private String currentUserMessage() {
		String currentUser = USER.get();
		return "current user=" + currentUser;
	}
}
