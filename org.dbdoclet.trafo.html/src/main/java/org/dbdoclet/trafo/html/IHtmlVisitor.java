package org.dbdoclet.trafo.html;

import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.INodeVisitor;

public interface IHtmlVisitor {

	public ElementImpl getDocumentElement();
	public IEditorFactory getEditorFactory();
	public boolean beforeEdit(EditorInstruction values);
	public Script getScript();

}
