package org.dbdoclet.herold;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XmlServices;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class HeroldFileTests extends AbstractTests {

	@Override
	@Before
	public void startUp() {
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
	public void testPoe_1() {
		herold("Edgar Allan Poe - Die Maske des roten Todes");
	}

	@Test
	public void testJohannSebastianBach_1() {
		herold("JSB");
	}

	@Test
	public void testJohannSebastianBach_2() {
		herold("JSB.html", DocBookTransformer.DocumentElementType.BOOK);
	}

	@Test
	public void testJSB_1() {
		try {

			File htmlFile = new File("src/test/resources/JSB.html");
			File xmlFile = new File("build/test/JSB.xml");

			Herold herold = new Herold();
			herold.convert(htmlFile, xmlFile);
			String buffer = FileServices.readToString(xmlFile, "UTF-8");

			URL schemaUrl = ResourceServices.getResourceAsUrl("docbook5.xsd");
			XmlServices.validate(buffer, "UTF-8", schemaUrl);

		} catch (Exception oops) {
			oops.printStackTrace();
			fail();
		}
	}

	@Test
	public void testLink1() {
		herold("html/a/Link1.html", DocBookTransformer.DocumentElementType.BOOK);
	}

	private void herold(String resource,
			DocBookTransformer.DocumentElementType rootType) {

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

			script.selectSection(DbtConstants.SECTION_DOCBOOK);
			script.addTextParam(DbtConstants.PARAM_DOCUMENT_ELEMENT,
					rootType.toString());

			herold.convert(new FileInputStream(htmlFile), new FileOutputStream(
					xmlFile), script);

		} catch (Exception oops) {

			oops.printStackTrace();
			fail(oops.getMessage());
		}
	}
}
