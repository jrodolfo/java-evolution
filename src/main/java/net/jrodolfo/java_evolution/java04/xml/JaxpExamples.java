package net.jrodolfo.java_evolution.java04.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Demonstrates JAXP XML processing, standardized in J2SE 1.4.
 */
public class JaxpExamples {

	/**
	 * Parses trusted XML text into a DOM document.
	 *
	 * @param xml trusted XML input
	 * @return parsed document
	 * @throws ParserConfigurationException when the parser cannot be created
	 * @throws IOException when the input cannot be read
	 * @throws SAXException when the XML is invalid
	 */
	public Document parseDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(xml)));
	}

	/**
	 * Reads the root element name from XML.
	 *
	 * @param xml trusted XML input
	 * @return root element name
	 * @throws ParserConfigurationException when the parser cannot be created
	 * @throws IOException when the input cannot be read
	 * @throws SAXException when the XML is invalid
	 */
	public String rootElementName(String xml) throws ParserConfigurationException, IOException, SAXException {
		return parseDocument(xml).getDocumentElement().getTagName();
	}

	/**
	 * Reads an attribute from the first element with the requested name.
	 *
	 * @param xml trusted XML input
	 * @param elementName element name
	 * @param attributeName attribute name
	 * @return attribute value
	 * @throws ParserConfigurationException when the parser cannot be created
	 * @throws IOException when the input cannot be read
	 * @throws SAXException when the XML is invalid
	 */
	public String attributeValue(String xml, String elementName, String attributeName)
			throws ParserConfigurationException, IOException, SAXException {
		Element element = (Element) parseDocument(xml).getElementsByTagName(elementName).item(0);
		return element.getAttribute(attributeName);
	}

	/**
	 * Reads the text from the first element with the requested name.
	 *
	 * @param xml trusted XML input
	 * @param elementName element name
	 * @return element text
	 * @throws ParserConfigurationException when the parser cannot be created
	 * @throws IOException when the input cannot be read
	 * @throws SAXException when the XML is invalid
	 */
	public String textValue(String xml, String elementName) throws ParserConfigurationException, IOException, SAXException {
		Node element = parseDocument(xml).getElementsByTagName(elementName).item(0);
		return element.getFirstChild().getNodeValue();
	}

	/**
	 * Transforms a DOM document back to XML text.
	 *
	 * @param document parsed document
	 * @return XML text
	 * @throws TransformerException when the document cannot be transformed
	 */
	public String transformToText(Document document) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter output = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(output));
		return output.toString();
	}
}
