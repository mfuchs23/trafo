package org.dbdoclet.trafo.html;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dbdoclet.html.parser.HtmlParser;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.TrafoService;
import org.dbdoclet.trafo.html.dita.HtmlDitaTrafo;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XPathServices;
import org.w3c.dom.Document;

public class AbstractTests {

	protected ArrayList<String> getValues(String xmlCode, String query) {

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					xmlCode.getBytes());
			Document doc = builder.parse(stream);
			return XPathServices.getValues(doc, query);
		
		} catch (Exception oops) {
			
			System.err.println(xmlCode);
			oops.printStackTrace();
			fail();
		}

		return null;
	}

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

	protected String transform(String htmlCode) {

		if (htmlCode == null) {
			throw new IllegalArgumentException("Der Parameter htmlCode darf nicht null sein!");
		}
		
		try {
			
			TrafoService trafo = new HtmlDitaTrafo();
			Script script = new Script();
			trafo.setInputStream(new ByteArrayInputStream(htmlCode.getBytes()));
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			trafo.setOutputStream(buffer);
			TrafoResult result = trafo.transform(script);
			
			if (result.isFailed()) {
				fail(result.toString());
			}
			
			return buffer.toString("UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
			return null;
		}
	}

}
