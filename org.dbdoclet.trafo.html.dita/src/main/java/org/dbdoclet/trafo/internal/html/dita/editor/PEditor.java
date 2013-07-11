/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.Book;
import org.dbdoclet.tag.docbook.Chapter;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Part;
import org.dbdoclet.tag.docbook.RefEntry;
import org.dbdoclet.tag.docbook.RefNameDiv;
import org.dbdoclet.tag.docbook.RefSection;
import org.dbdoclet.tag.docbook.Reference;
import org.dbdoclet.tag.docbook.SectionElement;
import org.dbdoclet.tag.html.P;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.TextImpl;

public class PEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		P p = (P) getHtmlElement();

		NodeImpl parent = getParent();
		Para candidate = dbfactory.createPara();
		copyCommonAttributes(p, candidate);
		candidate.setParentNode(parent);

		if (parent instanceof Entry) {
			candidate.setFormatType(ElementImpl.FORMAT_INLINE);
		}

		if (candidate.validate()) {

			setCurrent(candidate);
			parent.appendChild(getCurrent());

		} else {

			setCurrent(parent);

			if (parent instanceof Para &&  parent.getParentNode() != null) {
				parent.appendChild(new TextImpl(" "));
				setParent((NodeImpl) parent.getParentNode());
			}

			if (isSection(parent)) {

				if (parent instanceof Book || parent instanceof Part) {

					Chapter chapter = dbfactory
							.createChapter("Automatically inserted chapter");
					parent.appendChild(chapter);
					setCurrent(candidate);
					chapter.appendChild(getCurrent());

				} else if (parent instanceof Reference) {

					setCurrent(candidate);

					RefEntry refEntry = dbfactory.createRefEntry();
					parent.appendChild(refEntry);

					RefNameDiv refNameDiv = dbfactory.createRefNameDiv();
					refNameDiv.appendChild(dbfactory
							.createRefName(AUTOMATICALLY_INSERTED));
					refNameDiv.appendChild(dbfactory.createRefPurpose());
					refEntry.appendChild(refNameDiv);

					RefSection refSection = dbfactory.createRefSection();
					refEntry.appendChild(refSection);

					refSection.appendChild(getCurrent());
					// TODO Auto-generated method stub

				} else {

					SectionElement sect = (SectionElement) parent;
					SectionElement firstSectionChild = sect
							.getFirstSectionChild();

					if (firstSectionChild != null) {
						setCurrent(candidate);
						parent.insertBefore(getCurrent(), firstSectionChild);
					}
				}
			}
		}

		traverse(true);

		return finalizeValues();
	}
}
