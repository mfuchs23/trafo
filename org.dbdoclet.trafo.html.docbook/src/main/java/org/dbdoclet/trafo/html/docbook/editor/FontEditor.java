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
import org.dbdoclet.tag.html.Font;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class FontEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		traverse(true);
		Font font = (Font) values.getHtmlElement();

		String color = font.getAttribute("color");

		if (color == null || color.trim().length() == 0) {
			return finalizeValues();
		}

		Emphasis emphasis = dbfactory.createEmphasis();
		emphasis.setRole("color");
		emphasis.setCondition(color);

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
