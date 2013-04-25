/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.BlockQuote;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;

public class VarEditor extends Editor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	DocBookElement parent = getParent();

	if (parent instanceof BlockQuote) {

	    setCurrent(dbfactory.createProgramListing());
	    traverse(true);
	} else {

	    DocBookElement ancestor = parent;

	    if (parent.isContentModel()) {

		ancestor = dbfactory.createPara();
		parent.appendChild(ancestor);
	    } // end of if ()

	    DocBookElement candidate = dbfactory.createLiteral();
	    candidate.setParentNode(ancestor);

	    if (candidate.validate()) {

		setCurrent(candidate);
		ancestor.appendChild(candidate);
	    }

	    traverse(true);
	}

	return finalizeValues();
    }
}
