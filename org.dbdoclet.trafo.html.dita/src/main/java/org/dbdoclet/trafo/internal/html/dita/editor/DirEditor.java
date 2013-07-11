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
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class DirEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		NodeListImpl children = getHtmlElement().getTrafoChildNodes();

		if (children.size() == 0) {

			return finalizeValues();
		}

		NodeImpl parentNode = getParent();
		if (isList(parentNode)) {

			setCurrent(dbfactory.createListItem());
			getCurrent().setParentNode(getParent());
			parentNode.appendChild(getCurrent());
			setParent(getCurrent());
		}

		setCurrent(dbfactory.createItemizedList());
		getCurrent().setParentNode(getParent());
		getParent().appendChild(getCurrent());
		traverse(true);

		return finalizeValues();
	}
}
