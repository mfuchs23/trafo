package org.dbdoclet.trafo.html;

import java.io.IOException;
import java.io.InputStream;

import org.dbdoclet.html.parser.ParserException;
import org.dbdoclet.html.tokenizer.TokenizerException;
import org.dbdoclet.tag.html.HtmlDocument;

public interface IHtmlProvider {

	void traverse(IHtmlVisitor visitor) throws Exception;

	HtmlDocument parse(InputStream in, String encoding) throws IOException,
			ParserException, TokenizerException;

}
