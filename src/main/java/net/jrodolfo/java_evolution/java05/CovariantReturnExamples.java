package net.jrodolfo.java_evolution.java05;

/**
 * Demonstrates covariant return types, introduced in Java 5.
 */
public class CovariantReturnExamples {

	/**
	 * Creates a specialized document through an overriding method that returns a
	 * subtype.
	 *
	 * @return a specialized document
	 */
	public InvoiceDocument createInvoiceDocument() {
		InvoiceDocumentFactory factory = new InvoiceDocumentFactory();
		return factory.create();
	}

	static class Document {
		private final String type;

		Document(String type) {
			this.type = type;
		}

		String type() {
			return type;
		}
	}

	static class InvoiceDocument extends Document {
		InvoiceDocument() {
			super("invoice");
		}

		String paymentStatus() {
			return "ready for payment";
		}
	}

	static class DocumentFactory {
		Document create() {
			return new Document("generic");
		}
	}

	static class InvoiceDocumentFactory extends DocumentFactory {
		@Override
		InvoiceDocument create() {
			return new InvoiceDocument();
		}
	}
}
