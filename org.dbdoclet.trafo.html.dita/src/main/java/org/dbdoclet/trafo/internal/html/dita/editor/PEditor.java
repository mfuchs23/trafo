/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.html.P;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class PEditor extends AbstractBlockEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DitaTagFactory tagFactory = getTagFactory();

		org.dbdoclet.tag.html.P p = (P) getHtmlElement();

		NodeImpl parent = getParent();

		org.dbdoclet.tag.dita.P candidate = tagFactory.createP();
		copyCommonAttributes(p, candidate);
		candidate.setParentNode(parent);
		setCurrent(candidate);
		parent.appendChild(getCurrent());

		traverse(true);

		return finalizeValues();
	}
}
