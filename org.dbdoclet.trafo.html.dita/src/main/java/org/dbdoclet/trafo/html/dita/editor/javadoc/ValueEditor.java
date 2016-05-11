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
 * The class <code>ValueEditor</code> is reponsible for transforming @value tags
 * into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class ValueEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);

	org.dbdoclet.tag.javadoc.Value value = (org.dbdoclet.tag.javadoc.Value) getHtmlElement();
	getCurrent().appendChild(value.getTextContent());

	traverse(false);

	return finalizeValues();
    }
}
