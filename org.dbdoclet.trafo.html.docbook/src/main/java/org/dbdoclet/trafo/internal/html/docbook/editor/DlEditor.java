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
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class DlEditor extends Editor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	org.dbdoclet.tag.html.Dl dl = (org.dbdoclet.tag.html.Dl) getHtmlElement();
	String title = dl.getTitle();

	NodeListImpl children = dl.getTrafoChildNodes();

	if (children.size() == 0) {

	    return finalizeValues();
	}

	if (getParent().isList()) {

	    setCurrent(dbfactory.createListItem());
	    getCurrent().setParentNode(getParent());
	    getParent().appendChild(getCurrent());
	    setParent(getCurrent());
	}

	setCurrent(dbfactory.createVariableList());

	if ((title != null) && (title.length() > 0)) {
	    getCurrent().appendChild(dbfactory.createTitle(title));
	}

	getParent().appendChild(getCurrent());
	traverse(true);

	return finalizeValues();
    }
}
