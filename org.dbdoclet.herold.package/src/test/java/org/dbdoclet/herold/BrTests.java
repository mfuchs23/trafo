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

public class BrTests extends AbstractTests {

	private final File htmlFile = new File("src/test/resources/html/br/Br.html");
	private final File xmlFile = new File("build/test/Br.xml");

	@Test
	public void detectTrappedBr() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/pdftohtml.her" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:para");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

	@Test
	public void dontDetectTrappedBr() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/article.her" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:para");
		assertNotNull(nodeList);
		assertEquals(4, nodeList.size());
	}
}
