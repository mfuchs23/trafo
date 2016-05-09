/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor;

import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.dita.DitaElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;
import org.dbdoclet.xiphias.dom.CommentImpl;

public class CommentEditor extends DitaEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

		try {

			setValues(super.edit(values));

			DitaElement parent = getDitaElementParent();
			CharacterDataImpl node = getCharacterDataNode();

			if (node instanceof CommentImpl == false) {
				return finalizeValues();
			}

			CommentImpl comment = (CommentImpl) node;
			String text = comment.getData();

			if (text == null) {
				return finalizeValues();
			}

			text = text.trim();
			text = StringServices.cutPrefix(text, "<!--");
			text = StringServices.cutSuffix(text, "-->");
			text = text.trim();
			text = StringServices.cutPrefix(text, "[:dbdoclet:]");
			text = text.trim();

			if (text.startsWith("insert ")) {

				text = StringServices.cutPrefix(text, "insert");
				text = text.trim();

				text = XmlServices.textToXml(text);
				text = StringServices.replace(text, "(", "<");
				text = StringServices.replace(text, ")", ">");
				text = StringServices.replace(text, "<<", "(");
				text = StringServices.replace(text, ">>", ")");

				parent.appendChild(text, false);
			}

			return finalizeValues();

		} catch (Exception oops) {
			throw new EditorException(oops);
		}
	}
}
