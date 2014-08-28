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
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.VarListEntry;
import org.dbdoclet.tag.docbook.VariableList;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class DdEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		DocBookElement list = null;

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		// Try to detect two sequenced dd tags.
		if (getParent() instanceof VariableList) {

			VarListEntry entry = dbfactory.createVarListEntry();
			getParent().appendChild(entry);

			Term term = dbfactory.createTerm();
			entry.appendChild(term);

			list = dbfactory.createListItem();
			entry.appendChild(list);

		} else if (getParent() instanceof VarListEntry) {

			list = dbfactory.createListItem();
			getParent().appendChild(list);
			setParent((DocBookElement) getParent().getParentNode());
		}

		if (list == null) {

			return finalizeValues();
		} // end of else

		Para para = dbfactory.createPara();
		copyCommonAttributes(getHtmlElement(), para);
		setCurrent(para);
		list.appendChild(getCurrent());
		traverse(true);

		return finalizeValues();
	}
}
