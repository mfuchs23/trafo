package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.Sfv;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.xiphias.XPathServices;
import org.dbdoclet.xiphias.XmlServices;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ParameterTests extends AbstractTests {

	private final File htmlFile = new File(
			"src/test/resources/ParameterTests.html");
	private final File xmlFile = new File("build/test/ParameterTests.xml");

	@Test
	public void testInvalidProfile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/invalid.her" };
		assertEquals("ExitCode != 2", 2, executeHeroldCommandLine(cmd));
	}

	@Test
	public void testAddIndex_Off_CmdLine() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"--docbook-add-index=false" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:index");
		assertNull("//d:index", node);
	}

	@Test
	public void testAddIndex_On_CmdLine() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"--docbook-add-index" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:index");
		assertNotNull("//d:index", node);
	}

	@Test
	public void testAddIndex_On_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/addIndexOn.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:index");
		assertNotNull("//d:index", node);
	}

	@Test
	public void attributeRemapOn() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/createRemapAttributeOn.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//@remap");
		assertNotNull("//@remap", node);
	}

	@Test
	public void attributeRemapOff() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/createRemapAttributeOff.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//@remap");
		assertNull("//@remap", node);
	}

	@Test
	public void testAddIndex_Off_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/addIndexOff.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:index");
		assertNotNull("//d:index", node);
	}

	@Test
	public void testCreateXrefLabel_On_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/createXrefLabelOn.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:anchor/@xreflabel");
		assertNotNull("//d:anchor/@xreflabel", node);
	}

	@Test
	public void testCreateXrefLabel_Off_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/createXrefLabelOff.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:anchor/@xreflabel");
		assertNull("//d:anchor", node);
	}

	@Test
	public void testDecomposeTables_Off_CmdLine() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"--docbook-decompose-tables=false" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:informaltable");
		assertNotNull("//d:informaltable", node);
	}

	@Test
	public void testDecomposeTables_On_CmdLine() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-T" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:informaltable");
		assertNull("//d:informaltable", node);
	}

	@Test
	public void testDecomposeTables_Off_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/decomposeTablesOff.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:informaltable");
		assertNotNull("//d:informaltable", node);
	}

	@Test
	public void testDecomposeTables_On_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/decomposeTablesOn.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:informaltable");
		assertNull("//d:informaltable", node);
	}

	@Test
	public void testDestinationEncoding_Windows1252_CmdLine()
			throws IOException, SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-d", "windows-1252" };
		executeHeroldCommandLine(cmd);
		assertEquals("windows-1252", XmlServices.getEncoding(xmlFile));
	}

	@Test
	public void testDestinationEncoding_Windows1252_Profile()
			throws IOException, SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "src/test/resources/profile/destinationEncoding.her" };
		executeHeroldCommandLine(cmd);
		assertEquals("windows-1252", XmlServices.getEncoding(xmlFile));
	}

	@Test
	public void testDocumentElement_Article_CmdLine() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-r", "article" };
		executeHeroldCommandLine(cmd);
		Document doc = XmlServices.parse(xmlFile);
		assertEquals("article", doc.getDocumentElement().getTagName());
	}

	@Test
	public void testDocumentElement_Article_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/article.her" };
		executeHeroldCommandLine(cmd);
		Document doc = XmlServices.parse(xmlFile);
		assertEquals("article", doc.getDocumentElement().getTagName());
	}

	@Test
	public void testDocumentElement_Book_CmdLine() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-r", "book" };
		executeHeroldCommandLine(cmd);
		Document doc = XmlServices.parse(xmlFile);
		assertEquals("book", doc.getDocumentElement().getTagName());
	}

	@Test
	public void testDocumentElement_Book_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/book.her" };
		executeHeroldCommandLine(cmd);
		Document doc = XmlServices.parse(xmlFile);
		assertEquals("book", doc.getDocumentElement().getTagName());
	}

	@Test
	public void testImageDataFormats_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		File base64File = new File("./src/test/resources/images/logo.base64");
		base64File.delete();

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/imageDataFormats.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:imageobject[3]/d:imagedata/@format");
		assertNotNull("//d:imagedata/@format", node.getNodeValue());
		assertEquals("//d:imagedata/@format", "BASE64", node.getNodeValue());
		assertTrue(base64File.exists());
		base64File.delete();
	}

	@Test
	public void testImagePath_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/imagePath.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:imagedata/@fileref");
		assertEquals("//d:imagedata/@fileref",
				"../media/pics/src/test/resources/images/logo.png",
				node.getNodeValue());
	}

	@Test
	public void testLanguage_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/language.her" };
		executeHeroldCommandLine(cmd);
		String buffer = FileServices.readToString(xmlFile);
		assertTrue("xml:lang", buffer.contains("xml:lang=\"es\""));
	}

	@Test
	public void testTitle_CmdLine() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-t", "Dies ist ein Titel" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:title");
		assertEquals("//d:title", "Dies ist ein Titel", node.getTextContent());
	}

	@Test
	public void testTitle_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/title.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:title");
		assertEquals("//d:title", "Dies ist ein Titel", node.getTextContent());
	}

	@Test
	public void testUseAbsoluteImagePath_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/useAbsoluteImagePath.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:imagedata/@fileref");
		assertTrue("//d:imagedata/@fileref",
				node.getNodeValue().startsWith("file:///"));
	}

	@Test
	public void testExclude_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/exclude.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:imagedata");
		assertNull("//img", node);
		node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:para[id='exclude']");
		assertNull("//p", node);
	}

	@Test
	public void testSectionNumberingPattern_Profile() throws IOException,
			SAXException, ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p",
				"./src/test/resources/profile/sectionNumberingPattern.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:section/d:title");
		assertEquals("Einführung", node.getTextContent());
	}

	@Test
	public void testSourceEncoding_Profile() throws IOException, SAXException,
			ParserConfigurationException {

		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "./src/test/resources/profile/sourceEncoding.her" };
		executeHeroldCommandLine(cmd);
		Document doc = validateAndParseDocBook(xmlFile);
		Node node = XPathServices.getNode(doc.getDocumentElement(), "d",
				Sfv.NS_DOCBOOK, "//d:section/d:title");
		assertEquals("1. EinfÃ¼hrung", node.getTextContent());
	}

}
