package net.jrodolfo.java_evolution.java15.hidden_classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HiddenClassesExamplesTest {

	private final HiddenClassesExamples examples = new HiddenClassesExamples();

	@Test
	void hiddenClassCanBeDefinedFromClassFileBytes() throws Exception {
		Class<?> hiddenClass = examples.defineHiddenTemplateClass();

		assertThat(examples.isHidden(hiddenClass))
				.as("defineHiddenClass should produce a real Class object marked as hidden")
				.isTrue();
	}

	@Test
	void hiddenClassCanStillBeUsedByTheDefiningRuntimeCode() throws Exception {
		Class<?> hiddenClass = examples.defineHiddenTemplateClass();

		assertThat(examples.invokeHiddenClassMethod(hiddenClass))
				.as("Hidden classes are usable by runtime infrastructure even though normal code should not depend on them")
				.isEqualTo("hidden implementation");
	}

	@Test
	void hiddenClassIsNotDiscoverableByNormalNameLookup() throws Exception {
		Class<?> hiddenClass = examples.defineHiddenTemplateClass();

		assertThat(hiddenClass.getName())
				.as("Hidden class names include a VM-generated suffix")
				.contains("/");
		assertThat(examples.canFindByName(hiddenClass))
				.as("Class.forName should not discover a hidden class by its VM-specific name")
				.isFalse();
	}
}
