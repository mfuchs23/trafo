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

public class UlOlTests extends AbstractTests {

	@Test
	public void testAufzhlung_1() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/Aufzhlung_1.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/Aufzhlung_1.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:listitem");
		assertNotNull(nodeList);
		assertEquals(5, nodeList.size());
		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:itemizedlist");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());
	}

	@Test
	public void testMsoListBullet_1() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoListBullet_1.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoListBullet_1.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:listitem");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

	@Test
	public void testMsoListBullet_2() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoListBullet_2.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoListBullet_2.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:listitem");
		assertNotNull(nodeList);
		assertEquals(5, nodeList.size());
	}

	@Test
	public void testMsoListBullet_3() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoListBullet_3.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoListBullet_3.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:listitem");
		assertNotNull(nodeList);
		assertEquals(5, nodeList.size());
	}

	@Test
	public void testMsoListNumbered() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoListNumbered.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoListNumberedTest.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:listitem");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:orderedlist");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.size());
	}

	@Test
	public void testMsoNestedList_1() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoNestedList_1.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoNestedList_1.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:itemizedlist");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

	@Test
	public void testMsoNestedList_2() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoNestedList_2.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoNestedList_2.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:itemizedlist");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK, "//d:title");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

	@Test
	public void testMsoNestedList_3() throws ParserConfigurationException,
			SAXException, IOException {

		String htmlFileName = "src/test/resources/html/ul/MsoNestedList_3.html";
		File htmlFile = new File(htmlFileName);

		String xmlFileName = "build/test/MsoNestedList_3.xml";
		File xmlFile = new File(xmlFileName);

		html2docbook(htmlFile, xmlFile, "listDetection");
		Document doc = validateAndParseDocBook(xmlFile);
		ArrayList<Node> nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK,
				"//d:itemizedlist");
		assertNotNull(nodeList);
		assertEquals(4, nodeList.size());
		nodeList = XPathServices.getNodes(doc, "d", NS_DOCBOOK, "//d:title");
		assertNotNull(nodeList);
		assertEquals(2, nodeList.size());
	}

}