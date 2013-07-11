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
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.script.Script;

public class TheadEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		boolean decomposeTables = script.isParameterOn(
				TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_DECOMPOSE_TABLES,
				TrafoConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables) {
			traverse(true);
			return finalizeValues();
		}

		setCurrent(dbfactory.createThead());
		getCurrent().setParentNode(getParent());
		getParent().appendChild(getCurrent());
		traverse(true);

		return finalizeValues();
	}
}
