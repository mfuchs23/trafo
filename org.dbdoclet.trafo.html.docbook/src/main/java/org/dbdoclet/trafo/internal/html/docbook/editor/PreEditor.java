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
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.Example;
import org.dbdoclet.tag.docbook.InformalExample;
import org.dbdoclet.tag.docbook.SectionElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class PreEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();
		HtmlElement pre = values.getHtmlElement();

		DocBookElement candidate;
		DocBookElement parent = getDocBookElementParent();

		if (getParent() instanceof Entry) {

			candidate = dbfactory.createScreen();

		} else if (getParent() instanceof Example
				|| getParent() instanceof InformalExample) {

			candidate = dbfactory.createProgramListing();

		} else {

			candidate = dbfactory.createScreen();
		}

		String attrLang = pre.getAttribute("lang");
		if (attrLang != null) {
			candidate.setAttribute("language", attrLang);
		}

		candidate.setParentNode(parent);

		if (candidate.validate()) {

			setCurrent(candidate);
			parent.appendChild(candidate);

		} else {

			if (parent.isSection()) {

				SectionElement sect = (SectionElement) parent;
				SectionElement firstSectionChild = sect.getFirstSectionChild();

				if (firstSectionChild != null) {

					setCurrent(candidate);
					parent.insertBefore(candidate, firstSectionChild);
				}
			}
		}

		traverse(true);

		return finalizeValues();
	}
}
