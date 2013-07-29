package org.dbdoclet.herold;

import static org.junit.Assert.fail;

import java.io.File;

import org.dbdoclet.xiphias.XmlServices;
import org.junit.Test;
import org.w3c.dom.Document;

public class CommentTests extends AbstractTests {

	@Test
	public void testComment1() {

		String htmlFileName = "src/test/resources/html/comment/Comment1.html";
		String xmlFileName = "build/test/Comment1.xml";
		testComment(htmlFileName, xmlFileName);
	}

	@Test
	public void testComment2() {

		String htmlFileName = "src/test/resources/html/comment/Comment2.html";
		String xmlFileName = "build/test/Comment2.xml";
		testComment(htmlFileName, xmlFileName);
	}

	@Test
	public void testComment3() {

		String htmlFileName = "src/test/resources/html/comment/Comment3.html";
		String xmlFileName = "build/test/Comment3.xml";
		testComment(htmlFileName, xmlFileName);
	}

	private Document testComment(String htmlFileName, String xmlFileName) {

		try {

			Herold herold = new Herold();
			herold.setVerbose(true);
			File htmlFile = new File(htmlFileName);
			File xmlFile = new File(xmlFileName);
			herold.convert(htmlFile, xmlFile);

			validateAndParseDocBook(xmlFile);
			Document doc = XmlServices.parse(xmlFile);
			return doc;

		} catch (Exception oops) {

			oops.printStackTrace();
			fail();
		}

		return null;
	}
}