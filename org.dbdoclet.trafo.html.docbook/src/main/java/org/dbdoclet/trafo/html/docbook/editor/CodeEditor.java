/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Literal;
import org.dbdoclet.tag.html.Code;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

/**
 * Verarbeitung des HTML-Elements <tt>code</tt>.
 * 
 * <p>
 * <ul>
 * <li>Falls das DocBook-Vaterelement vom Typ <tt>literal</tt> ist, wird nur der
 * Textinhalt eingefügt.</li>
 * <li>Andernfalls wird überprüft, ob sich an dieser Stelle ein Element des Typs
 * <tt>literal</tt> einfügen läßt.</li>
 * </ul>
 * </p>
 * 
 * @author michael
 *
 */
public class CodeEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		Code code = (Code) getHtmlElement();
		NodeImpl parent = getParent();

		if (parent instanceof Literal) {
			parent.appendChild(code.getTextContent());
			return finalizeValues();
		}

		DocBookElement candidate = dbfactory.createLiteral(code
				.getTextContent());

		if (candidate.isValidParent(script.getTransformPosition(), parent)) {

			parent.appendChild(candidate);
			traverse(false);
			return finalizeValues();
		}

		candidate = dbfactory.createProgramlisting();

		if (candidate.isValidParent(script.getTransformPosition(), parent)) {

			setCurrent(candidate);
			parent.appendChild(getCurrent());
			traverse(true);
			return finalizeValues();
		}

		parent.appendChild(code.getTextContent());
		return finalizeValues();
	}
}
