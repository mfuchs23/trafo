package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class AttributeTests extends AbstractTests {

	@Test
	public void xmlLangDe() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File("src/test/resources/Edgar Allan Poe - Die Maske des roten Todes.html");
		File xmlFile = new File("build/test/xmlLangDe.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		validateAndParseDocBook(xmlFile);
	}

}
