/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor.javadoc;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.docbook.editor.Editor;

public class CodeEditor extends Editor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);
	DocBookTagFactory dbfactory = values.getTagFactory();

	org.dbdoclet.tag.javadoc.Code code = (org.dbdoclet.tag.javadoc.Code) getHtmlElement();
	getCurrent().appendChild(dbfactory.createComputerOutput(code.getTextContent()));

	traverse(false);

	return finalizeValues();
    }
}
