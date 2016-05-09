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
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Link;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Simpara;
import org.dbdoclet.tag.docbook.ULink;
import org.dbdoclet.tag.docbook.Xref;
import org.dbdoclet.tag.html.A;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class AEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		NodeImpl ancestor;
		NodeImpl parent = getParent();

		traverse(true);

		ancestor = parent;

		if (parent instanceof DocBookElement) {
			
			DocBookElement dbParent = (DocBookElement) parent;
			
			if (dbParent.isSection()) {
				Para para = dbfactory.createPara();
				parent.appendChild(para);
				ancestor = para;
			}
		}
		
		A htmlA = (A) getHtmlElement();

		String name = htmlA.getName();
		String href = htmlA.getHref();

		if (name != null && name.length() > 0) {

			Anchor anchor = dbfactory.createAnchor();
			anchor.setId(getLinkManager().createUniqueId(name));

			boolean createXrefLabel = script.isParameterOn(
					TrafoConstants.SECTION_DOCBOOK,
					TrafoConstants.PARAM_CREATE_XREF_LABEL, true);

			if (createXrefLabel == true) {
				anchor.setXrefLabel(StringServices.replace(
						htmlA.getTextContent(), "\"", "&quot;"));
			}

			ancestor.appendChild(anchor);

		} else if (href != null && href.equals("#") == false
				&& href.startsWith("#")) {

			String label = htmlA.getTextContent();

			if ((label != null) && (label.length() > 0)) {

				Link link = dbfactory.createLink(getLinkManager().getUniqueId(
						href));
				link.setParentNode(ancestor);
				ancestor.appendChild(link);
				setCurrent(link);

			} else {

				Xref xref = dbfactory.createXref(getLinkManager().getUniqueId(
						href));

				xref.setParentNode(ancestor);
				ancestor.appendChild(xref);
				setCurrent(ancestor);
			}

		} else {

			DocBookElement linkElement;

			if (isDocBook5()) {

				Link link = dbfactory.createLink();
				link.setParentNode(ancestor);

				if (href != null) {
					link.setHref(href);
				} else {
					link.setHref("");
				}

				linkElement = link;

			} else {

				ULink ulink = dbfactory.createULink();
				ulink.setParentNode(ancestor);
				ulink.setUrl(href);// public Script getScript() {
				// return script;
				// }

				linkElement = ulink;
			}

			if (linkElement.isValidParent(script.getTransformPosition(), ancestor)) {

				setCurrent(linkElement);
				ancestor.appendChild(getCurrent());
				getLinkManager().addLink(linkElement);

			} else {

				Simpara candidate = dbfactory.createSimpara();
				candidate.setParentNode(ancestor);

				if (candidate.isValidParent(script.getTransformPosition(), ancestor)) {

					ancestor.appendChild(candidate);
					candidate.appendChild(ancestor);
				}
			}
		}

		return finalizeValues();
	}
}
