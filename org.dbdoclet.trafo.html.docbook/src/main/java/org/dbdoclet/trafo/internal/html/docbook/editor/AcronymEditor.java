/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.Acronym;
import org.dbdoclet.tag.docbook.DocBookTagFactory;

public class AcronymEditor extends Editor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	Acronym candidate = dbfactory.createAcronym();
	candidate.setParentNode(getParent());

	if (candidate.validate()) {

	    setCurrent(candidate);
	    getParent().appendChild(getCurrent());
	} // end of if ()

	traverse(true);

	return finalizeValues();
    }
}
