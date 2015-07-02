package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Namespace;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.Section;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

public class WhitespaceTests extends AbstractTests {

	/** TODO */
	@Test
	@Ignore
	public void verlorenesLeerzeichen() throws IOException, SAXException,
			ParserConfigurationException {

		Script script = new Script();
		Namespace namespace = script.getNamespace();
		Section section = namespace.findOrCreateSection(TrafoConstants.SECTION_SECTION_DETECTION);
		section.setParam(new TextParam(TrafoConstants.PARAM_SECTION_NUMBERING_PATTERN, "((\\d+\\.)+)?\\d*\\.?\\p{Z}"));
		String xmlCode = transform("<h1><a name='dd'><span>1.2.3</span><span>&nbsp;</span><span>Donald </span></a><span>Duck</span></h1>", script);
		assertEquals("Donald Duck", xmlCode);
	}
}
