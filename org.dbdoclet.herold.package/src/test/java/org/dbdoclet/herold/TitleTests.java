package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.herold.Herold.OutputFormat;
import org.dbdoclet.xiphias.XPathServices;
import org.dbdoclet.xiphias.XmlServices;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class TitleTests extends AbstractTests {

	@Test
	public void testTitleHead() {
		String htmlFileName = "src/test/resources/html/header/TitleHead.html";
		String xmlFileName = "build/test/TitleHead.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH1() {
		String htmlFileName = "src/test/resources/html/header/TitleH1.html";
		String xmlFileName = "build/test/TitleH1.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH2() {
		String htmlFileName = "src/test/resources/html/header/TitleH2.html";
		String xmlFileName = "build/test/TitleH2.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH3() {
		String htmlFileName = "src/test/resources/html/header/TitleH3.html";
		String xmlFileName = "build/test/TitleH3.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH4() {
		String htmlFileName = "src/test/resources/html/header/TitleH4.html";
		String xmlFileName = "build/test/TitleH4.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH5() {
		String htmlFileName = "src/test/resources/html/header/TitleH5.html";
		String xmlFileName = "build/test/TitleH5.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleH6() {
		String htmlFileName = "src/test/resources/html/header/TitleH6.html";
		String xmlFileName = "build/test/TitleH6.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	@Test
	public void testTitleMsoHeading() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/header/TitleMsoHeading7.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/TitleMsoHeading7.xml";
		File xmlFile = new File(xmlFileName);

		herold(htmlFile, xmlFile, OutputFormat.DocBook, "sectionDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:para");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

	@Test
	public void testTitleText() {
		String htmlFileName = "src/test/resources/html/header/TitleText.html";
		String xmlFileName = "build/test/TitleText.xml";
		testTitle(htmlFileName, xmlFileName);
	}

	private void testTitle(String htmlFileName, String xmlFileName) {
		try {

			Herold herold = new Herold();
			File htmlFile = new File(htmlFileName);
			File xmlFile = new File(xmlFileName);
			herold.convert(htmlFile, xmlFile);

			Document doc = XmlServices.parse(xmlFile);
			String title = (String) XPathServices.getValue(doc, "d",
					NS_DOCBOOK, "/d:article/d:info/d:title");
			assertEquals("Goethes Faust", title);

		} catch (Exception oops) {

			oops.printStackTrace();
			fail();
		}
	}
}