/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.SimPara;
import org.dbdoclet.tag.html.Font;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class FontEditor extends Editor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	traverse(true);
	Font font = (Font) values.getHtmlElement();

	String color = font.getAttribute("color");

	if (color == null || color.trim().length() == 0) {
	    return finalizeValues();
	}

	Emphasis emphasis = dbfactory.createEmphasis();
	emphasis.setRole("color");
	emphasis.setCondition(color);

	setCurrent(emphasis);

	if (emphasis.isValidParent(getParent()) == false) {

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
