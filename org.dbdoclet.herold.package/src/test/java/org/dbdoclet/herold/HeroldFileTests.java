package org.dbdoclet.herold;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.docbook.DocumentElementType;
import org.dbdoclet.trafo.script.Script;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class HeroldFileTests extends AbstractTests {

	@Override
	@Before
	public void startUp() {
		super.startUp();
		new File("bin/DocBook").mkdirs();
	}

	@Test
	public void testWordProfile() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File(
				"/home/michael/Dokumentation/DPMA/GSS/ElSAMarke-Gesamtsystemspezifikation.htm");
		File xmlFile = new File("/tmp/GSS.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath(),
				"-p", "root/profile/word.her" };
		executeHeroldCommandLine(cmd);
	}

	@Test
	public void testPoe2DocBook() {
		html2docbook("Edgar Allan Poe - Die Maske des roten Todes");
	}

	@Test
	public void testPoe2Dita() {
		html2dita("Edgar Allan Poe - Die Maske des roten Todes");
	}

	@Test
	public void testJSB2DocBook() {
		html2docbook("JSB");
	}

	@Test
	public void testJSB2Dita() {
		html2dita("JSB");
	}

	@Test
	public void testJSB() {
		herold("JSB.html", DocumentElementType.BOOK);
	}

	@Test
	public void testJSB_1() {
		try {

			File htmlFile = new File("src/test/resources/JSB.html");
			File xmlFile = new File("build/test/JSB.xml");

			html2docbook(htmlFile, xmlFile);
			html2dita(htmlFile, xmlFile);
			
		} catch (Exception oops) {
			oops.printStackTrace();
			fail();
		}
	}

	@Test
	public void testLink1() {
		herold("html/a/Link1.html", DocumentElementType.BOOK);
	}

	private void herold(String resource, DocumentElementType rootType) {

		try {

			String htmlCode = ResourceServices.getResourceAsString(resource);
			assertNotNull("Can't find resource " + resource, htmlCode);

			File htmlFile = new File("bin/DocBook/"
					+ FileServices.getFileBase(resource) + ".html");
			FileServices.writeFromString(htmlFile, htmlCode);

			File xmlFile = new File("bin/DocBook/"
					+ FileServices.getFileBase(resource) + ".xml");

			Script script = new Script();

			Herold herold = new Herold();

			script.selectSection(TrafoConstants.SECTION_DOCBOOK);
			script.addTextParam(TrafoConstants.PARAM_DOCUMENT_ELEMENT,
					rootType.toString());

			herold.convert(new FileInputStream(htmlFile), new FileOutputStream(
					xmlFile), script);

		} catch (Exception oops) {

			oops.printStackTrace();
			fail(oops.getMessage());
		}
	}
}
