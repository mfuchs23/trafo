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
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.Example;
import org.dbdoclet.tag.docbook.Informalexample;
import org.dbdoclet.tag.docbook.SectionElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class PreEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();
		HtmlElement pre = values.getHtmlElement();

		DocBookElement candidate;
		NodeImpl parent = getParent();

		if (getParent() instanceof Entry) {

			candidate = dbfactory.createScreen();

		} else if (getParent() instanceof Example
				|| getParent() instanceof Informalexample) {

			candidate = dbfactory.createProgramlisting();

		} else {

			candidate = dbfactory.createScreen();
		}

		String attrLang = pre.getAttribute("lang");
		if (attrLang != null) {
			candidate.setAttribute("language", attrLang);
		}

		candidate.setParentNode(parent);

		if (candidate.isValidParent(script.getTransformPosition(), parent)) {

			setCurrent(candidate);
			parent.appendChild(candidate);

		} else {

			if (isSection(parent)) {

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
