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
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Simpara;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class StrikeEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		traverse(true);
		Emphasis emphasis = dbfactory.createEmphasis();
		emphasis.setRole("strikethrough");

		setCurrent(emphasis);

		if (emphasis.isValidParent(script.getTransformPosition(), getParent()) == false) {

			Simpara candidate = dbfactory.createSimpara();
			candidate.setParentNode(getParent());

			if (candidate.isValidParent(script.getTransformPosition(), getParent())) {

				getParent().appendChild(candidate);
				candidate.appendChild(getCurrent());
			}

		} else {

			getCurrent().setParentNode(getParent());
			getParent().appendChild(getCurrent());
		}

		return finalizeValues();
	}
}
