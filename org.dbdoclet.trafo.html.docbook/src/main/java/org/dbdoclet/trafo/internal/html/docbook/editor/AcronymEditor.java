/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.Acronym;
import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class AcronymEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		Acronym candidate = dbfactory.createAcronym();

		if (candidate.isValidParent(script.getTransformPosition(), getParent())) {
			setCurrent(candidate);
			getParent().appendChild(getCurrent());
		}

		traverse(true);
		return finalizeValues();
	}
}
