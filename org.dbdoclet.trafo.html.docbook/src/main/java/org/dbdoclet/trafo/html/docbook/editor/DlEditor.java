/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class DlEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = getTagFactory();

	org.dbdoclet.tag.html.Dl dl = (org.dbdoclet.tag.html.Dl) getHtmlElement();
	String title = dl.getTitle();

	NodeListImpl children = dl.getTrafoChildNodes();

	if (children.size() == 0) {

	    return finalizeValues();
	}

	if (isList(getParent())) {

	    setCurrent(dbfactory.createListitem());
	    getCurrent().setParentNode(getParent());
	    getParent().appendChild(getCurrent());
	    setParent(getCurrent());
	}

	setCurrent(dbfactory.createVariablelist());

	if ((title != null) && (title.length() > 0)) {
	    getCurrent().appendChild(dbfactory.createTitle(title));
	}

	getParent().appendChild(getCurrent());
	traverse(true);

	return finalizeValues();
    }
}
