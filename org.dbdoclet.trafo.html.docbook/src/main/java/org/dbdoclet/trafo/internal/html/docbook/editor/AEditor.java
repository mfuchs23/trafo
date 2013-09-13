/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Link;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.SimPara;
import org.dbdoclet.tag.docbook.ULink;
import org.dbdoclet.tag.docbook.XRef;
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

		// The referncen of attribute parent must not be changed. Because of
		// this we
		// need a temporary variable to store a new parent for
		// different situations.
		ancestor = parent;

		if (isContentModel(parent)) {

			Para para = dbfactory.createPara();
			parent.appendChild(para);
			ancestor = para;
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

				XRef xref = dbfactory.createXRef(getLinkManager().getUniqueId(
						href));//	public Script getScript() {
//				return script;
//				}

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
				ulink.setUrl(href);//	public Script getScript() {
//				return script;
//				}

				linkElement = ulink;
			}

			if (linkElement.validate()) {

				setCurrent(linkElement);
				ancestor.appendChild(getCurrent());
				getLinkManager().addLink(linkElement);

			} else {

				SimPara candidate = dbfactory.createSimPara();
				candidate.setParentNode(ancestor);

				if (candidate.validate()) {

					ancestor.appendChild(candidate);
					candidate.appendChild(ancestor);
				}
			}
		}

		return finalizeValues();
	}
}
