package org.dbdoclet.trafo.html;

import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.DocumentFragmentImpl;
import org.dbdoclet.xiphias.dom.DocumentImpl;

public interface IHtmlVisitor {

	public DocumentImpl createDocument(HtmlDocument htmlDoc);
	public DocumentFragmentImpl createDocumentFragment(HtmlFragment htmlFragment);
	public IEditorFactory getEditorFactory();
	public boolean beforeEdit(EditorInstruction values);
	public Script getScript();

}
