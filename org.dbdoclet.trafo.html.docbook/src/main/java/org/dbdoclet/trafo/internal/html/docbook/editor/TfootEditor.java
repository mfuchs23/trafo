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
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.script.Script;

public class TfootEditor extends Editor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = values.getTagFactory();

		Script script = getTransformer().getScript();
		boolean decomposeTables = script.isParameterOn(
				DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_DECOMPOSE_TABLES,
				DbtConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables) {
			traverse(true);
			return finalizeValues();
		}

		setCurrent(dbfactory.createTfoot());
		getCurrent().setParentNode(getParent());
		getParent().appendChild(getCurrent());
		traverse(true);

		return finalizeValues();
	}
}
