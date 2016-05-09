/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.Abbrev;
import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class AbbrEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		Abbrev candidate = dbfactory.createAbbrev();

		if (candidate.isValidParent(script.getTransformPosition(), getParent())) {

			setCurrent(candidate);
			getParent().appendChild(getCurrent());
		}

		traverse(true);

		return finalizeValues();
	}
}
