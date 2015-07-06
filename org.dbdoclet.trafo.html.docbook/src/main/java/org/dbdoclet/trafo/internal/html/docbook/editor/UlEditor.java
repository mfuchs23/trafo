/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class UlEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	BaseTagFactory dbfactory = getTagFactory();

	NodeListImpl children = getHtmlElement().getTrafoChildNodes();

	if (children.size() == 0) {

	    return finalizeValues();
	}

	if (isList(getParent())) {

	    setCurrent(dbfactory.createListitem());
	    getCurrent().setParentNode(getParent());
	    getParent().appendChild(getCurrent());
	    setParent(getCurrent());
	}

	setCurrent(dbfactory.createItemizedlist());
	getCurrent().setParentNode(getParent());
	getParent().appendChild(getCurrent());
	traverse(true);

	return finalizeValues();
    }
}
