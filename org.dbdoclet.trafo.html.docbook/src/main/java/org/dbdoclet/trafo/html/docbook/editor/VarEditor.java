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
import org.dbdoclet.tag.docbook.Blockquote;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class VarEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		NodeImpl parent = getParent();

		if (parent instanceof Blockquote) {

			setCurrent(dbfactory.createProgramlisting());
			traverse(true);

		} else {

			NodeImpl ancestor = parent;

			DocBookElement candidate = dbfactory.createLiteral();
			candidate.setParentNode(ancestor);

			if (candidate.isValidParent(script.getTransformPosition(), ancestor)) {

				setCurrent(candidate);
				ancestor.appendChild(candidate);
			}

			traverse(true);
		}

		return finalizeValues();
	}
}
