package org.dbdoclet.trafo.html.docbook;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class AlignTests extends AbstractTests {

	@Test
	public void invalidImgAlignAttribute() throws TrafoException, UnsupportedEncodingException {

		String htmlCode = "<img align='bottom'/>";
		String xmlCode = transform(htmlCode);
		String query = "//@align[.='bottom']";
		ArrayList<String> values = getValues(xmlCode, query);
		assertEquals(
				"Attribute 'align' mit ungültigem Wert 'bottom' gefunden.", 0,
				values.size());
	}
}
