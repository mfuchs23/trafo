/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.VarListEntry;
import org.dbdoclet.tag.docbook.VariableList;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class DtEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		DocBookElement entry = null;

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		if (getParent() instanceof VariableList) {

			entry = dbfactory.createVarListEntry();
			entry.setParentNode(getParent());
			getParent().appendChild(entry);

		} else if (getParent() instanceof VarListEntry) {

			setParent((DocBookElement) getParent().getParentNode());

			if ((getParent() != null) && getParent() instanceof VariableList) {

				getCurrent().appendChild(
						dbfactory.createListItem().appendChild(
								dbfactory.createPara().setFormatType(
										ElementImpl.FORMAT_INLINE)));

				entry = dbfactory.createVarListEntry();
				entry.setParentNode(getParent());
				getParent().appendChild(entry);
				
			} else {

				return finalizeValues();
			}
			
		} else {

			return finalizeValues();
		}

		Term term = dbfactory.createTerm();
		copyCommonAttributes(getHtmlElement(), term);
		
		setCurrent(term);
		getCurrent().setParentNode(entry);

		entry.appendChild(getCurrent());

		setParent(entry);
		setAnything(getCurrent());

		traverse(true);

		return finalizeValues();
	}
}
