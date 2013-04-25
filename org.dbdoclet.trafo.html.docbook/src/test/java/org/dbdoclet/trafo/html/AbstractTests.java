package org.dbdoclet.trafo.html;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.html.parser.HtmlParser;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XPathServices;
import org.w3c.dom.Document;

public class AbstractTests {

	/**
	 * @param htmlCode
	 */
	protected HtmlFragment parse(String htmlCode) {

		HtmlParser parser = new HtmlParser();

		try {

			HtmlFragment fragment = parser.parseFragment(htmlCode);
			return fragment;

		} catch (Exception oops) {
			oops.printStackTrace();
			fail();
		}

		return null;
	}

	protected String transform(String htmlCode) throws TrafoException {

		DocBookTransformer trafo = new DocBookTransformer();
		return trafo.transformFragment(htmlCode);
	}

	protected ArrayList<String> getValues(String xmlCode, String query) {

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					xmlCode.getBytes());
			Document doc = builder.parse(stream);
			return XPathServices.getValues(doc, query);
		} catch (Exception oops) {
			oops.printStackTrace();
			fail();
		}

		return null;
	}

	protected String transformFragment(String htmlCode) throws TrafoException {
		DocBookTransformer transformer = new DocBookTransformer();

		Script script = new Script();
		transformer.setScript(script);

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformFragment(htmlCode, result);
		return xmlCode;
	}

}
