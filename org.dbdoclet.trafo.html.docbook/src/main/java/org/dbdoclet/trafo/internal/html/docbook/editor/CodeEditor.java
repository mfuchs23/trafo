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
import org.dbdoclet.tag.html.Code;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class CodeEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		Code code = (Code) getHtmlElement();
		NodeImpl parent = getParent();

		DocBookElement candidate = dbfactory.createLiteral(code.getTextContent());

		if (candidate.isValidParent(script.getTransformPosition(), parent)) {
		
			parent.appendChild(candidate);
			traverse(false);
			return finalizeValues();
		}
		
		candidate = dbfactory.createProgramListing();
		setCurrent(candidate);
		parent.appendChild(getCurrent());
		traverse(true);
		return finalizeValues();
	}
}
