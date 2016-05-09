/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Indexterm;
import org.dbdoclet.tag.docbook.Primary;
import org.dbdoclet.tag.docbook.Secondary;
import org.dbdoclet.tag.html.Span;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class SpanEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		Span span = (Span) getHtmlElement();
		NodeImpl parent = getParent();

		String title = span.getTitle();

		String border1 = ":primary=";
		String border2 = ":secondary=";

		if ((title != null) && title.startsWith("indexterm:")) {

			String buffer = StringServices.cutPrefix(title, "indexterm");

			if (buffer.startsWith(border1)) {

				String primaryText = StringServices.cutPrefix(buffer, border1);
				String secondaryText = null;

				int indexSecondary = primaryText.indexOf(border2);

				if (indexSecondary != -1) {

					int index = indexSecondary + border2.length();

					if (index < primaryText.length()) {

						secondaryText = primaryText.substring(indexSecondary
								+ border2.length());
					}

					primaryText = primaryText.substring(0, indexSecondary);
				}

				Indexterm indexTerm = dbfactory.createIndexterm();
				indexTerm.setParentNode(getParent());

				Primary primary = dbfactory.createPrimary(primaryText);
				indexTerm.appendChild(primary);

				if ((secondaryText != null) && (secondaryText.length() > 0)) {

					Secondary secondary = dbfactory
							.createSecondary(secondaryText);
					indexTerm.appendChild(secondary);
				}

				parent.appendChild(indexTerm);
			}
		}

		String id = span.getId();

		if (id != null) {

			Anchor anchor = dbfactory.createAnchor();
			anchor.setId(getLinkManager().createUniqueId(id));
			parent.appendChild(anchor);
		}

		traverse(true);

		return finalizeValues();
	}
}
