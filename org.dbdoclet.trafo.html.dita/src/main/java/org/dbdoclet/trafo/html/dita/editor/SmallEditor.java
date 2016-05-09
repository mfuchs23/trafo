/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor;

import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;


public class SmallEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	traverse(true);

	return finalizeValues();
    }
}
