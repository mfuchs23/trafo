/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.SimPara;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class StrikeEditor extends DocBookEditor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = getTagFactory();

	traverse(true);
	Emphasis emphasis = dbfactory.createEmphasis();
	emphasis.setRole("strikethrough");

	setCurrent(emphasis);

	if (emphasis.isValidParent(getDocBookElementParent()) == false) {

	    SimPara candidate = dbfactory.createSimPara();
	    candidate.setParentNode(getParent());

	    if (candidate.validate()) {

		getParent().appendChild(candidate);
		candidate.appendChild(getCurrent());
	    }

	} else {

	    getCurrent().setParentNode(getParent());
	    getParent().appendChild(getCurrent());
	}

	return finalizeValues();
    }
}
