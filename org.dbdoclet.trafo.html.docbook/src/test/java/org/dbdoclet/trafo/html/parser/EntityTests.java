package org.dbdoclet.trafo.html.parser;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.internal.html.docbook.DocBookTransformer;
import org.junit.Test;

public class EntityTests {

	@Test
	public void testLe() throws TrafoException {
		transform("<p>Der Bereich ist &le; 10<p>");
	}

	private void transform(String htmlCode) throws TrafoException {

		DocBookTransformer trafo = new DocBookTransformer();
		System.out.println(trafo.transformFragment(htmlCode));
	}
}
