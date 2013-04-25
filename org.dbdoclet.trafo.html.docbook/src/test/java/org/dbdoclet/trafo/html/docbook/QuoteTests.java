package org.dbdoclet.trafo.html.docbook;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class QuoteTests extends AbstractTests {

	@Test
	public void testQuote_1() throws IOException, TrafoException {

		String htmlCode = "<p>Da \"<b>Fett</b>\"</p><p>WEG!!!</p>";
		String xmlCode = transformFragment(htmlCode);
		System.out.println(xmlCode);
		assertTrue("Anführungszeichen nicht gefunden.",
				xmlCode.contains("&quot;<emphasis"));
	}

}
