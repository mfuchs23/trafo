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
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.Varlistentry;
import org.dbdoclet.tag.docbook.Variablelist;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class DdEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		DocBookElement list = null;

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		// Try to detect two sequenced dd tags.
		if (getParent() instanceof Variablelist) {

			Varlistentry entry = dbfactory.createVarlistentry();
			getParent().appendChild(entry);

			Term term = dbfactory.createTerm();
			entry.appendChild(term);

			list = dbfactory.createListitem();
			entry.appendChild(list);

		} else if (getParent() instanceof Varlistentry) {

			list = dbfactory.createListitem();
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
