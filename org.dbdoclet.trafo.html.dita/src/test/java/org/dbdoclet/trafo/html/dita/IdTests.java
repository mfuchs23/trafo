package org.dbdoclet.trafo.html.dita;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class IdTests extends AbstractTests {

	@Test
	public void testId_1() throws IOException, TrafoException {

		String htmlCode = "<dl><dt id='refsXML'>[XML]</dt><dd>Lorem ipsum</dd></dl>";
		String xmlCode = transform(htmlCode);
		assertTrue("Attribute id nicht gefunden.",
				xmlCode.contains("xml:id=\"refsXML\""));

	}

	@Test
	public void testId_2() throws IOException, TrafoException {

		String htmlCode = "<html><head><link href='XXX' title='XYZ'></head><body><p>Lorem</p></body></html>";
		String xmlCode = transform(htmlCode);
		assertFalse("Ungültiges Element link gefunden.",
				xmlCode.contains("<link"));
	}

}
