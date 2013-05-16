package org.dbdoclet.trafo.html;

import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.INodeVisitor;

public interface IHtmlVisitor extends INodeVisitor {

	public ElementImpl getDocumentElement();
	public IEditorFactory getEditorFactory();
	public boolean beforeEdit(EditorInstruction values);

}
