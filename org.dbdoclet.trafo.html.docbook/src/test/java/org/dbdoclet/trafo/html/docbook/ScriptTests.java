package org.dbdoclet.trafo.html.docbook;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.tag.docbook.Article;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.html.AbstractTests;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.internal.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.script.Script;
import org.junit.Test;

public class ScriptTests extends AbstractTests {

	@Test
	public void testAddIndex() throws IOException, TrafoException {

		String htmlCode = ResourceServices
				.getResourceAsString("html/lorem.html");

		DocBookTransformer transformer = new DocBookTransformer();

		Script script = new Script();
		script.selectSection(DbtConstants.SECTION_DOCBOOK);
		script.addBoolParam(DbtConstants.PARAM_ADD_INDEX, true);

		transformer.setScript(script);
		TrafoResult result = new TrafoResult();
		DocBookTagFactory tf = new DocBookTagFactory();
		Article article = tf.createArticle();

		String xmlCode = transformer.transform(htmlCode, article, result);
		assertTrue("Element index nicht gefunden.",
				xmlCode.contains("<index/>"));

	}
}
