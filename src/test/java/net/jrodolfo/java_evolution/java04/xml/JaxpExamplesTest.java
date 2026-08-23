package net.jrodolfo.java_evolution.java04.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

class JaxpExamplesTest {

	private static final String XML = "<release version=\"4\"><feature>JAXP</feature></release>";

	private final JaxpExamples examples = new JaxpExamples();

	@Test
	void domParserReadsRootElement() throws Exception {
		assertThat(examples.rootElementName(XML))
				.as("JAXP DOM parsing should expose the document root element")
				.isEqualTo("release");
	}

	@Test
	void domParserReadsAttributesAndText() throws Exception {
		assertThat(examples.attributeValue(XML, "release", "version"))
				.as("DOM elements expose XML attributes")
				.isEqualTo("4");
		assertThat(examples.textValue(XML, "feature"))
				.as("DOM nodes expose child element text")
				.isEqualTo("JAXP");
	}

	@Test
	void transformerWritesDomBackToXmlText() throws Exception {
		Document document = examples.parseDocument(XML);

		assertThat(examples.transformToText(document))
				.as("JAXP transformers can write a DOM document as XML text")
				.contains("<release version=\"4\">")
				.contains("<feature>JAXP</feature>");
	}
}
