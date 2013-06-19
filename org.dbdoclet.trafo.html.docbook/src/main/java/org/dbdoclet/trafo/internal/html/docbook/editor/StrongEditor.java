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
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.DbtConstants;

public class StrongEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		HtmlElement child = getHtmlElement();
		DocBookElement parent = getParent();

		DocBookElement candidate;

		candidate = dbfactory.createEmphasis(child.getTextContent());
		candidate.setParentNode(parent);

		candidate.setRole(DbtConstants.DEFAULT_EMPHASIS_ROLE_BOLD);

		if (candidate.validate()) {

			parent.appendChild(candidate);
			setCurrent(candidate);
		}

		traverse(false);

		return finalizeValues();
	}
}
