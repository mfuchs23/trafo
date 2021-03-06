package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SectionTests extends AbstractTests {

	@Test
	public void documentElementBook() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Book.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "book" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("book", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementPart() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Part.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "part" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("part", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementParagraph() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Paragraph.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "paragraph" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("para", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementArticle() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Article.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "article" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("article", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementReference() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Reference.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "reference" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("reference", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementChapter() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Chapter.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "chapter" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("chapter", doc.getDocumentElement().getTagName());
	}

	@Test
	public void documentElementSection() throws IOException, SAXException, ParserConfigurationException {

		File htmlFile = new File("src/test/resources/JSB.html");
		File xmlFile = new File("build/test/Section.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(), "-p", "section" };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		Document doc = validateAndParseDocBook(xmlFile);
		assertEquals("section", doc.getDocumentElement().getTagName());
	}

	@Test
	public void nestingH1H2() {
		String buffer = transform("<h1>H1</h1><p>P1</p><h2>H2</h2><p>P2</p>");
		String expected = "<section><title>H1</title><para>P1</para>"
				+ "<section><title>H2</title><para>P2</para></section></section>";
		assertEquals(expected, buffer.replaceAll("\\s+", ""));
	}

	@Test
	public void multipleH2() {
		String buffer = transform("<h1>title1</h1><p>para1</p>" 
				+ "<h2>title2.1</h2><p>para2.1</p>"
				+ "<h2>title2.2</h2><p>para2.2</p>");
		String expected = "<section><title>title1</title><para>para1</para>"
				+ "<section><title>title2.1</title><para>para2.1</para></section>"
				+ "<section><title>title2.2</title><para>para2.2</para></section></section>";
		assertEquals(expected, buffer.replaceAll("\\s+", ""));
	}

	@Test
	public void fragment2H2() {
		String buffer = transform("<p>para1</p><h2>title2.1</h2><p>para2.1</p><h2>title2.2</h2><p>para2.2</p>");
		String expected = "<para>para1</para>"
				+ "<section><title>title2.1</title><para>para2.1</para></section>"
				+ "<section><title>title2.2</title><para>para2.2</para></section>";
		assertEquals(expected, buffer.replaceAll("\\s+", ""));
	}

	@Test
	public void multipleH2() {
		String buffer = transform("<h1>title1</h1><p>para 1</p>"
				+ "<h2>title 2.1</h2><p>para 2.1</p>"
				+ "<h2>title 2.2</h2><p>para 2.2</p>");
		System.out.println(buffer);
	}

}
