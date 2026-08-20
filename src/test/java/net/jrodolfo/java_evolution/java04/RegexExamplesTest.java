package net.jrodolfo.java_evolution.java04;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RegexExamplesTest {

	private final RegexExamples examples = new RegexExamples();

	@Test
	void matcherFindsRepeatedIssueIdentifiers() {
		assertThat(examples.issueIds("Fix API-12 and DOC-7 before release"))
				.as("Matcher.find should locate each regex occurrence")
				.containsExactly("API-12", "DOC-7");
	}

	@Test
	void patternMatchesValidatesEntireInput() {
		assertThat(examples.isReleasePackageName("java04")).isTrue();
		assertThat(examples.isReleasePackageName("java4")).isFalse();
	}
}
