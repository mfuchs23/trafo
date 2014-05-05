package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class ArticleTests extends AbstractTests {

	@Test
	public void sectionWithNestedArticles() throws IOException, SAXException,
			ParserConfigurationException {

		File htmlFile = new File("src/test/resources/html/article/SectionWithNestedArticles.html");
		File xmlFile = new File("build/test/SectionWithNestedArticles.xml");
		String[] cmd = { "-i", htmlFile.getPath(), "-o", xmlFile.getPath() };
		assertEquals("ExitCode != 0", 0, executeHeroldCommandLine(cmd));
		validateAndParseDocBook(xmlFile);
	}
}
