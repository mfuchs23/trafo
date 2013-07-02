package org.dbdoclet.trafo.html.parser;

import java.io.UnsupportedEncodingException;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class EntityTests extends AbstractTests {

	@Test
	public void testLe() throws TrafoException, UnsupportedEncodingException {
		transform("<p>Der Bereich ist &le; 10<p>");
	}
}
