/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor.javadoc;

import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.dita.editor.DitaEditor;

public class CodeEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) {

	setValues(values);
	getTagFactory();
	return finalizeValues();
    }
}
