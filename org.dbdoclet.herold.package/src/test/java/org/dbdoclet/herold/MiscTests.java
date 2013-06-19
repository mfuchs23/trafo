package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class MiscTests extends AbstractTests {

	@Test
	public void concurrentModificationException() throws IOException,
			SAXException, ParserConfigurationException {

		File xmlFile = new File("build/test/EuroPLoP98.xml");
		String[] cmd = { "-i", "src/test/resources/html/misc/EuroPLoP98.html",
				"-o", xmlFile.getPath() };

		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		validateAndParse(xmlFile);
	}
}
