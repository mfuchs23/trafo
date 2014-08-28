/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.BlockQuote;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class VarEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		NodeImpl parent = getParent();

		if (parent instanceof BlockQuote) {

			setCurrent(dbfactory.createProgramListing());
			traverse(true);

		} else {

			NodeImpl ancestor = parent;

			DocBookElement candidate = dbfactory.createLiteral();
			candidate.setParentNode(ancestor);

			if (candidate.isValidParent("VarEditor", ancestor)) {

				setCurrent(candidate);
				ancestor.appendChild(candidate);
			}

			traverse(true);
		}

		return finalizeValues();
	}
}
