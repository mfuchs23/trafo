package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.dbdoclet.xiphias.XPathServices;
import org.junit.Test;
import org.w3c.dom.Document;

public class PreTests extends AbstractTests {

	@Test
	public void keepFormatting() {

		Document doc = herold("html/pre/PreKeepFormatting");
		String text = (String) XPathServices.getValue(doc, "d", NS_DOCBOOK,
				"//d:screen/text()");
		assertNotNull(text);
		assertTrue(text.contains("\n"));
	}

	@Test
	public void keepAttributeLanguage() {

		Document doc = herold("html/pre/PreKeepFormatting");
		String text = (String) XPathServices.getValue(doc, "d", NS_DOCBOOK,
				"//d:screen/@language");
		assertNotNull(text);
		assertEquals(text, "ActiveScript");
	}
}
