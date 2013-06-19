/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor.javadoc;

import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.docbook.editor.DocBookEditor;

/**
 * The class <code>ValueEditor</code> is reponsible for transforming @value tags
 * into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class ValueEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);

	org.dbdoclet.tag.javadoc.Value value = (org.dbdoclet.tag.javadoc.Value) getHtmlElement();
	getCurrent().appendChild(value.getTextContent());

	traverse(false);

	return finalizeValues();
    }
}
