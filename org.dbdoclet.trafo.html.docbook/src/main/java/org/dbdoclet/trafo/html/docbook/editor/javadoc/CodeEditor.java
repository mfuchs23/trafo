/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor.javadoc;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.editor.DocBookEditor;

public class CodeEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);
	DocBookTagFactory dbfactory = getTagFactory();

	org.dbdoclet.tag.javadoc.Code code = (org.dbdoclet.tag.javadoc.Code) getHtmlElement();
	getCurrent().appendChild(dbfactory.createComputeroutput(code.getTextContent()));

	traverse(false);

	return finalizeValues();
    }
}
