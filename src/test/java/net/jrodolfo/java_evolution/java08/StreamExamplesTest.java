package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class StreamExamplesTest {

	private final StreamExamples examples = new StreamExamples();

	@Test
	void filtersMapsAndSortsValues() {
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		assertThat(examples.activeUserNames(users))
				.containsExactly("Bruna", "Rodolfo");
	}

	@Test
	void groupsValuesByClassifier() {
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));
		Map<String, List<String>> expectedNamesByDepartment = new HashMap<>();
		expectedNamesByDepartment.put("Engineering", Arrays.asList("Rodolfo", "Bruna"));
		expectedNamesByDepartment.put("Design", Arrays.asList("Ana"));

		assertThat(examples.namesByDepartment(users))
				.containsExactlyInAnyOrderEntriesOf(expectedNamesByDepartment);
	}

	@Test
	void usesPrimitiveStreamForNumericAggregation() {
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		assertThat(examples.totalAgeOfActiveUsers(users))
				.isEqualTo(70);
	}

	@Test
	void findsMaximumValueWithComparator() {
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		assertThat(examples.longestName(users))
				.isEqualTo("Rodolfo");
	}
}
