package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CovariantReturnExamplesTest {

	private final CovariantReturnExamples examples = new CovariantReturnExamples();

	@Test
	void overridingMethodCanReturnMoreSpecificSubtype() {
		CovariantReturnExamples.InvoiceDocument document = examples.createInvoiceDocument();

		assertThat(document.type())
				.as("The overriding factory method should return the specialized document type")
				.isEqualTo("invoice");
		assertThat(document.paymentStatus())
				.as("The caller can use subtype-specific behavior without a cast")
				.isEqualTo("ready for payment");
	}
}
