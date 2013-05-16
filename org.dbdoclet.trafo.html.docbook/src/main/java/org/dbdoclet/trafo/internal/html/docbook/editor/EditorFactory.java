/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.javadoc.JavaDocElement;
import org.dbdoclet.trafo.html.EditorFactoryException;
import org.dbdoclet.trafo.html.IEditorFactory;

public class EditorFactory implements IEditorFactory {

	/* (non-Javadoc)
	 * @see org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#getChildEditor(org.dbdoclet.tag.html.HtmlElement)
	 */
	@Override
	public IEditor getChildEditor(HtmlElement child)
			throws EditorFactoryException {

		if (child == null) {
			throw new EditorFactoryException(
					"Can't create editor for child null!");
		}

		String str = child.getClass().getName();

		if (child instanceof JavaDocElement) {

			str = "org.dbdoclet.trafo.html.docbook.editor.javadoc"
					+ str.substring(str.lastIndexOf('.'), str.length())
					+ "Editor";

		} else {

			str = "org.dbdoclet.trafo.html.docbook.editor"
					+ str.substring(str.lastIndexOf('.'), str.length())
					+ "Editor";
		}

		Class<?> c;
		Object o;

		try {

			c = Class.forName(str);
			o = c.newInstance();

		} catch (ClassNotFoundException oops) {

			return new DefaultEditor();

		} catch (InstantiationException oops) {

			throw new EditorFactoryException("Can't create editor for "
					+ child.getNodeName());

		} catch (IllegalAccessException oops) {

			throw new EditorFactoryException(
					"No permission to create editor for " + child.getNodeName());
		}

		return (IEditor) o;
	}

	/* (non-Javadoc)
	 * @see org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#getCommentEditor()
	 */
	@Override
	public IEditor getCommentEditor() {

		return new CommentEditor();
	}

	/* (non-Javadoc)
	 * @see org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#getTextEditor()
	 */
	@Override
	public IEditor getTextEditor() {

		return new TextEditor();
	}
}
