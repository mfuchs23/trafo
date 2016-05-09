/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class DirEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		NodeListImpl children = getHtmlElement().getTrafoChildNodes();

		if (children.size() == 0) {

			return finalizeValues();
		}

		NodeImpl parentNode = getParent();
		if (isList(parentNode)) {

			setCurrent(dbfactory.createListitem());
			getCurrent().setParentNode(getParent());
			parentNode.appendChild(getCurrent());
			setParent(getCurrent());
		}

		setCurrent(dbfactory.createItemizedlist());
		getCurrent().setParentNode(getParent());
		getParent().appendChild(getCurrent());
		traverse(true);

		return finalizeValues();
	}
}
