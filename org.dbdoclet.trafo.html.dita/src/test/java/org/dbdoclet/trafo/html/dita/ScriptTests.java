package org.dbdoclet.trafo.html.dita;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.html.AbstractTests;
import org.dbdoclet.trafo.param.BooleanParam;
import org.dbdoclet.trafo.script.Namespace;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.Section;
import org.junit.Test;

public class ScriptTests extends AbstractTests {

	@Test
	public void testAddIndex() throws IOException, TrafoException {

		String htmlCode = ResourceServices
				.getResourceAsString("html/lorem.html");

		HtmlDitaTrafo transformer = new HtmlDitaTrafo();

		Script script = new Script();
		Namespace namespace = script.getNamespace();
		Section section = namespace.addSection(TrafoConstants.SECTION_DOCBOOK);
		section.addParam(new BooleanParam(TrafoConstants.PARAM_ADD_INDEX, true));
		
		transformer.setInputStream(new ByteArrayInputStream(htmlCode.getBytes()));
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		transformer.setOutputStream(buffer);
		
		TrafoResult result = transformer.transform(script);
		
		if (result.isFailed()) {
			fail(result.toString());
		}
		
		assertTrue("Element index nicht gefunden.",
				buffer.toString("UTF-8").contains("<index/>"));

	}
}
