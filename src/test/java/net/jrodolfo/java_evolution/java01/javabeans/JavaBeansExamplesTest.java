package net.jrodolfo.java_evolution.java01.javabeans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JavaBeansExamplesTest {

	private final JavaBeansExamples examples = new JavaBeansExamples();

	@Test
	void introspectionDiscoversBeanProperties() throws Exception {
		assertThat(examples.propertyNames())
				.as("JavaBeans introspection should discover properties from getter and setter names")
				.containsExactlyInAnyOrder("active", "name");
	}

	@Test
	void getterAndSetterNamesDefineAProperty() throws Exception {
		assertThat(examples.accessorPair("name"))
				.as("A JavaBeans property links getX and setX methods by convention")
				.isEqualTo("getName/setName");
	}

	@Test
	void booleanGetterUsesIsPrefix() throws Exception {
		assertThat(examples.accessorPair("active"))
				.as("Boolean JavaBeans properties commonly use isX as the read method")
				.isEqualTo("isActive/setActive");
		assertThat(examples.propertyType("active"))
				.as("The active property should be a primitive boolean")
				.isEqualTo(boolean.class);
	}

	@Test
	void listenerMethodsDefineAnEventSet() throws Exception {
		assertThat(examples.eventSetName())
				.as("addXListener and removeXListener methods define a JavaBeans event set")
				.isEqualTo("project");
	}
}
