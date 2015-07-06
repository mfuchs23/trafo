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
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.Varlistentry;
import org.dbdoclet.tag.docbook.Variablelist;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class DtEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		DocBookElement entry = null;

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		if (getParent() instanceof Variablelist) {

			entry = dbfactory.createVarlistentry();
			entry.setParentNode(getParent());
			getParent().appendChild(entry);

		} else if (getParent() instanceof Varlistentry) {

			setParent((DocBookElement) getParent().getParentNode());

			if ((getParent() != null) && getParent() instanceof Variablelist) {

				getCurrent().appendChild(
						dbfactory.createListitem().appendChild(
								dbfactory.createPara().setFormatType(
										ElementImpl.FORMAT_INLINE)));

				entry = dbfactory.createVarlistentry();
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
		traverse(true);

		return finalizeValues();
	}
}
