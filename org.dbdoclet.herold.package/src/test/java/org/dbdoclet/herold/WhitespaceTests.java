package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.script.Script;
import org.junit.Test;
import org.xml.sax.SAXException;

public class WhitespaceTests extends AbstractTests {

	@Test
	public void verlorenesLeerzeichen() throws IOException, SAXException,
			ParserConfigurationException {

		Script script = new Script();
		script.selectSection(TrafoConstants.SECTION_SECTION_DETECTION);
		script.setTextParameter(TrafoConstants.PARAM_SECTION_NUMBERING_PATTERN, "((\\d+\\.)+)?\\d*\\.?\\p{Z}");
		String xmlCode = transform("<h1><a name='dd'><span>1.2.3</span><span>&nbsp;</span><span>Donald </span></a><span>Duck</span></h1>", script);
		assertEquals("Lorem ipsum dolor sit amet.", xmlCode);
	}
}
