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
	void streamPipelineFiltersActiveUsersMapsToNamesAndSorts() {
		// Given
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		// When
		List<String> activeUserNames = examples.activeUserNames(users);

		// Then
		assertThat(activeUserNames)
				.as("The stream should keep active users, map them to names, and sort the names")
				.containsExactly("Bruna", "Rodolfo");
	}

	@Test
	void collectorGroupsUserNamesByDepartment() {
		// Given
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));
		Map<String, List<String>> expectedNamesByDepartment = new HashMap<>();
		expectedNamesByDepartment.put("Engineering", Arrays.asList("Rodolfo", "Bruna"));
		expectedNamesByDepartment.put("Design", Arrays.asList("Ana"));

		// When
		Map<String, List<String>> namesByDepartment = examples.namesByDepartment(users);

		// Then
		assertThat(namesByDepartment)
				.as("Collectors.groupingBy should create one map entry per department")
				.containsExactlyInAnyOrderEntriesOf(expectedNamesByDepartment);
	}

	@Test
	void primitiveStreamSumsTheAgesOfActiveUsers() {
		// Given
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		// When
		int totalAge = examples.totalAgeOfActiveUsers(users);

		// Then
		assertThat(totalAge)
				.as("mapToInt should create an IntStream that can sum values directly")
				.isEqualTo(70);
	}

	@Test
	void maxWithComparatorFindsTheLongestName() {
		// Given
		List<StreamExamples.User> users = Arrays.asList(
				new StreamExamples.User("Rodolfo", 41, "Engineering", true),
				new StreamExamples.User("Ana", 35, "Design", false),
				new StreamExamples.User("Bruna", 29, "Engineering", true));

		// When
		String longestName = examples.longestName(users);

		// Then
		assertThat(longestName)
				.as("max should return the name with the greatest length")
				.isEqualTo("Rodolfo");
	}
}
