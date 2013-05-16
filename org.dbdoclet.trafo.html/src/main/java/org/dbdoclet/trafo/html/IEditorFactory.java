package org.dbdoclet.trafo.html;

import org.dbdoclet.tag.html.HtmlElement;

public interface IEditorFactory {

	public IEditor getChildEditor(HtmlElement child)
			throws EditorFactoryException;

	public IEditor getCommentEditor();

	public IEditor getTextEditor();

}