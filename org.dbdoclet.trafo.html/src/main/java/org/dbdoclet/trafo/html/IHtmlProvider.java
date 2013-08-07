package org.dbdoclet.trafo.html;

import java.io.IOException;
import java.util.ArrayList;

import org.dbdoclet.html.parser.ParserException;
import org.dbdoclet.html.tokenizer.TokenizerException;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlFragment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

public interface IHtmlProvider {

	public Document traverse(HtmlDocument htmlDocument, IHtmlVisitor visitor) throws Exception;
	public DocumentFragment traverse(HtmlFragment htmlFragment, IHtmlVisitor visitor);

	public HtmlDocument parseDocument(String htmlCode) throws IOException,
			ParserException, TokenizerException;

	public HtmlFragment parseFragment(String htmlCode) throws IOException,
			ParserException, TokenizerException;
	public void setProgressListeners(ArrayList<ProgressListener> listeners);
}
