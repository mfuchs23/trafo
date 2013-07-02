package org.dbdoclet.trafo.html;

import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.trafo.script.Script;
import org.w3c.dom.Document;

public interface IHtmlVisitor {

	public Document createDocument(HtmlDocument htmlDoc);
	public IEditorFactory getEditorFactory();
	public boolean beforeEdit(EditorInstruction values);
	public Script getScript();

}
