/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor.javadoc;

import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.dita.editor.DitaEditor;

/**
 * The class <code>CodeEditor</code> is reponsible for transforming @literal
 * tags into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class LiteralEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);

	org.dbdoclet.tag.javadoc.Literal literal = (org.dbdoclet.tag.javadoc.Literal) getHtmlElement();
	getCurrent().appendChild(literal.getTextContent());

	traverse(false);

	return finalizeValues();
    }
}
