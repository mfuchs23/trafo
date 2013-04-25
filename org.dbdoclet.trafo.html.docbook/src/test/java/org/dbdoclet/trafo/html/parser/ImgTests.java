package org.dbdoclet.trafo.html.parser;

import java.io.IOException;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.dbdoclet.trafo.html.tokenizer.TokenizerException;
import org.junit.Test;

public class ImgTests extends AbstractTests {

	@Test
	public void testImg1() throws TrafoException, IOException, ParserException, TokenizerException {

		String buffer = ResourceServices
				.getResourceAsString("html/ch04s02.html");
		
		HtmlParser parser = new HtmlParser();
		HtmlDocument doc = parser.parseDocument(buffer);
		doc.treeView();
	}
}
