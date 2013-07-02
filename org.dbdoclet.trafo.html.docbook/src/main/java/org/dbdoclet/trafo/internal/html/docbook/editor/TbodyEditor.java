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
import org.dbdoclet.tag.docbook.Tbody;
import org.dbdoclet.tag.docbook.Tgroup;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.script.Script;

public class TbodyEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		boolean decomposeTables = script.isParameterOn(TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_DECOMPOSE_TABLES, TrafoConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables) {
			traverse(true);
			return finalizeValues();
		}

		DocBookElement parent = getParent();
		
		if (parent != null && parent instanceof Tgroup) { 
		
			Tbody tbody = (Tbody) parent.getLastChild(Tbody.class);

			if (tbody == null) {

				setCurrent(dbfactory.createTbody());
				getCurrent().setParentNode(getParent());
				getParent().appendChild(getCurrent());

			} else {
				
				setCurrent(tbody);
			}
		}
		
		traverse(true);

		return finalizeValues();
	}
}
