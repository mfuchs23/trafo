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
import org.dbdoclet.tag.docbook.Superscript;

public class SupEditor extends Editor {
    
    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	Superscript candidate = dbfactory.createSuperscript();
	candidate.setParentNode(getParent());
	copyCommonAttributes(getHtmlElement(), candidate);
	
	if (candidate.validate()) {

	    setCurrent(candidate);
	    getParent().appendChild(getCurrent());
	}
	
	traverse(true);

	return finalizeValues();
    }
}
