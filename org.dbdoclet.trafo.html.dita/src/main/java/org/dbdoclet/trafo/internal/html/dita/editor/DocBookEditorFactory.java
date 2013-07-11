/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.javadoc.JavaDocElement;
import org.dbdoclet.trafo.html.EditorFactoryException;
import org.dbdoclet.trafo.html.IEditor;
import org.dbdoclet.trafo.html.IEditorFactory;
import org.dbdoclet.trafo.internal.html.dita.LinkManager;
import org.dbdoclet.trafo.script.Script;

public class DocBookEditorFactory implements IEditorFactory {

	private LinkManager linkManager;
	private Script script;
	private DocBookTagFactory tagFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#getChildEditor
	 * (org.dbdoclet.tag.html.HtmlElement)
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

			str = "org.dbdoclet.trafo.internal.html.docbook.editor.javadoc"
					+ str.substring(str.lastIndexOf('.'), str.length())
					+ "Editor";

		} else {

			str = "org.dbdoclet.trafo.internal.html.docbook.editor"
					+ str.substring(str.lastIndexOf('.'), str.length())
					+ "Editor";
		}

		Class<?> c;
		Object o;

		try {

			c = Class.forName(str);
			o = c.newInstance();

			DocBookEditor editor = (DocBookEditor) o;
			editor.setLinkManager(linkManager);
			editor.setTagFactory(tagFactory);

		} catch (ClassNotFoundException oops) {

			DefaultEditor editor = new DefaultEditor();
			editor.setLinkManager(linkManager);
			editor.setTagFactory(tagFactory);
			return editor;

		} catch (InstantiationException oops) {

			throw new EditorFactoryException("Can't create editor for "
					+ child.getNodeName());

		} catch (IllegalAccessException oops) {

			throw new EditorFactoryException(
					"No permission to create editor for " + child.getNodeName());
		}

		return (IEditor) o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#
	 * getCommentEditor()
	 */
	@Override
	public IEditor getCommentEditor() {

		CommentEditor editor = new CommentEditor();
		editor.setLinkManager(linkManager);
		editor.setTagFactory(tagFactory);
		return editor;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dbdoclet.trafo.internal.html.docbook.editor.IEditorFactory#getTextEditor
	 * ()
	 */
	@Override
	public IEditor getTextEditor() {

		TextEditor editor = new TextEditor();
		editor.setLinkManager(linkManager);
		editor.setTagFactory(tagFactory);
		return editor;
	}

	public void setLinkManager(LinkManager linkManager) {
		this.linkManager = linkManager;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setTagFactory(DocBookTagFactory tagFactory) {
		this.tagFactory = tagFactory;
	}
}
