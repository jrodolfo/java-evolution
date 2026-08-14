package net.jrodolfo.java_evolution.java25.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesExamplesTest {

	private final ScopedValuesExamples examples = new ScopedValuesExamples();

	@Test
	void userIsVisibleWhileScopedBindingIsActive() throws Exception {
		boolean userBoundBeforeScope = examples.isUserBound();

		assertThat(userBoundBeforeScope)
				.as("creating the scoped-value key should not bind a user by itself")
				.isFalse();

		String valueInsideScope = examples.userInsideScope("Rodolfo");

		assertThat(valueInsideScope)
				.as("code running inside the scoped operation should read the bound user")
				.isEqualTo("current user=Rodolfo");
	}

	@Test
	void userBindingDisappearsAfterScopedOperationEnds() throws Exception {
		String valueInsideScope = examples.userInsideScope("Rodolfo");

		assertThat(valueInsideScope)
				.as("the scoped operation should observe the user while the binding is active")
				.isEqualTo("current user=Rodolfo");

		boolean userBoundAfterScope = examples.isUserBound();

		assertThat(userBoundAfterScope)
				.as("the user binding should be gone after the scoped operation finishes")
				.isFalse();
	}
}
