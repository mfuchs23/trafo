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

public class EditorFactory {

    static public Editor getChildEditor(HtmlElement child) throws EditorFactoryException {

	if (child == null) {
	    throw new EditorFactoryException("Can't create editor for child null!");
	}

	String str = child.getClass().getName();

	if (child instanceof JavaDocElement) {

	    str = "org.dbdoclet.trafo.html.docbook.editor.javadoc"
		    + str.substring(str.lastIndexOf('.'), str.length()) + "Editor";

	} else {

	    str = "org.dbdoclet.trafo.html.docbook.editor"
		    + str.substring(str.lastIndexOf('.'), str.length()) + "Editor";
	}

	Class<?> c;
	Object o;

	try {

	    c = Class.forName(str);
	    o = c.newInstance();

	} catch (ClassNotFoundException oops) {

	    return new DefaultEditor();
	    
	} catch (InstantiationException oops) {

	    throw new EditorFactoryException("Can't create editor for " + child.getNodeName());

	} catch (IllegalAccessException oops) {

	    throw new EditorFactoryException("No permission to create editor for "
		    + child.getNodeName());
	}

	return (Editor) o;
    }

    public static Editor getCommentEditor() {

	return new CommentEditor();
    }

    public static Editor getTextEditor() {

	return new TextEditor();
    }
}
