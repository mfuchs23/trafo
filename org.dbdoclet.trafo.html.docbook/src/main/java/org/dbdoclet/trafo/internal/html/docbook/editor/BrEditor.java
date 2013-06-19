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
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.html.Br;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.DbtConstants;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.TextImpl;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class BrEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();
		traverse(false);

		Br brElement = (Br) values.getHtmlElement();

		Script script = getScript();
		boolean detectTrappedBrEnabled = script.isParameterOn(
				DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_DETECT_TRAPPED_BR, true);

		if (detectTrappedBrEnabled && isTrapped(brElement, values.getCurrent())) {
			return finalizeValues();
		}

		if (getCurrent() instanceof Para) {

			DocBookElement parent = (DocBookElement) getCurrent()
					.getParentNode();

			if (parent != null) {

				Para para = dbfactory.createPara();
				parent.appendChild(para);

				setParent(para);
				setCurrent(para);
			}

		} else {

			getCurrent().appendChild(new TextImpl("\n"));
		}

		return finalizeValues();
	}

	private boolean isTrapped(Br brElement, ElementImpl elementImpl) {

		Node prevSibling = brElement.getPreviousSibling();
		Node nextSibling = brElement.getNextSibling();

		if (prevSibling == null || nextSibling == null) {
			return false;
		}

		if (prevSibling instanceof Text == false
				|| nextSibling instanceof Text == false) {
			return false;
		}

		String prevText = prevSibling.getTextContent();
		String nextText = nextSibling.getTextContent();

		if (prevText == null || nextText == null) {
			return false;
		}

		if (nextText.matches("(?s)^[\\w\\u00A0].*$") == false) {
			return false;
		}

		if (prevText.matches("(?s)^.*[\\w\\u00A0-]$") == false) {
			return false;
		}

		if (prevText.endsWith("-")) {

			Node lastChild = elementImpl.getLastChild();

			if (lastChild instanceof Text) {

				Text lastChildText = (Text) lastChild;
				String buffer = lastChildText.getTextContent();

				if (buffer != null && buffer.endsWith("-")) {
					buffer = StringServices.cutSuffix(buffer, "-");
					lastChildText.setTextContent(buffer);
				}
			}
		}

		return true;
	}
}
