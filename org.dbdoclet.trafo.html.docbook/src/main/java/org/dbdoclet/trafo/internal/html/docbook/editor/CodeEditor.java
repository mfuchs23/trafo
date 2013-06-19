/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Literal;
import org.dbdoclet.tag.html.Code;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class CodeEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = getTagFactory();

	Code code = (Code) getHtmlElement();
	DocBookElement parent = getParent();

	if (parent.isContentModel() == true) {

	    setCurrent(dbfactory.createProgramListing());
	    parent.appendChild(getCurrent());
	    traverse(true);

	} else {

	    Literal candidate = dbfactory.createLiteral(code.getTextContent());
	    candidate.setParentNode(parent);

	    if (candidate.validate()) {
		parent.appendChild(candidate);
	    }

	    traverse(false);
	}

	return finalizeValues();
    }
}
