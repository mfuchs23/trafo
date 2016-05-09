/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.Subscript;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class SubEditor extends DocBookEditor {
    
    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	BaseTagFactory dbfactory = getTagFactory();

	Subscript candidate = dbfactory.createSubscript();
	candidate.setParentNode(getParent());

	if (candidate.isValidParent(script.getTransformPosition(), getParent())) {

	    setCurrent(candidate);
	    getParent().appendChild(getCurrent());
	}

	traverse(true);

	return finalizeValues();
    }
}
