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

public class PdfToHtmlTests extends AbstractTests {

	private final File htmlFile = new File(
			"src/test/resources/html/pdftohtml/meta.html");
	private final File xmlFile = new File("build/test/pdftohtml/meta.xml");

	@Test
	public void extractMetaInfo() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/pdftohtml.her" };

		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));

		Document doc = validateAndParseDocBook(xmlFile);

		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:author");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());

		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK, "//d:abstract");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());

		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:info/d:date");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());

		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"/d:article/d:info/d:keywordset/d:keyword");
		assertNotNull(nodeList);
		assertEquals(4, nodeList.size());

		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"/d:article/d:info/d:subjectset/d:subject");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());
	}
}
