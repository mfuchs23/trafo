package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.xiphias.XPathServices;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class InfoTests extends AbstractTests {

	@Test
	public void simpleAbstract() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File("src/test/resources/html/header/TitleH1.html");
		File xmlFile = new File("build/test/SimpleAbstract.xml");
		String[] cmd = { "-p", "src/test/resources/profile/abstractSimple.her",
				"-i", htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:abstract/d:para");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());

	}

	@Test
	public void simpleAbstractWithInlineXml() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File("src/test/resources/html/header/TitleH1.html");
		File xmlFile = new File("build/test/AbstractWithInlineXml.xml");
		String[] cmd = { "-p",
				"src/test/resources/profile/abstractWithInlineXml.her", "-i",
				htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:abstract/d:para");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());

	}

	@Test
	public void simpleAbstractComplex() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File("src/test/resources/html/header/TitleH1.html");
		File xmlFile = new File("build/test/AbstractComplex.xml");
		String[] cmd = { "-p",
				"src/test/resources/profile/abstractComplex.her", "-i",
				htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:abstract/d:para");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());

	}

	@Test
	@Ignore
	public void stackoverflow() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File(
				"/home/michael/Dokumente/DPMA/GSS-20121106/ElSAMarke-Gesamtsystemspezifikation.htm");
		File xmlFile = new File("build/test/stackoverflow.xml");
		String[] cmd = { "-p", "src/test/resources/profile/book.her", "-i",
				htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParse(xmlFile);
	}
}
