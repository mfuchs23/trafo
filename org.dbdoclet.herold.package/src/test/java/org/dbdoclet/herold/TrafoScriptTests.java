package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.xiphias.XPathServices;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class TrafoScriptTests extends AbstractTests {

	@Test
	public void mapOlToProcedure() throws ParserConfigurationException, SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ol/OrderedList.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MapToProcedure.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "mapToProcedure");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:step");
		assertNotNull(nodeList);
		assertEquals(3, nodeList.size());
		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:procedure");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());
	}
}
