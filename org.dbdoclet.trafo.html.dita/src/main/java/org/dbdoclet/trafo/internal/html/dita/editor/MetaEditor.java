/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import java.util.List;

import org.dbdoclet.tag.dita.Abstract;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.Prolog;
import org.dbdoclet.tag.docbook.Author;
import org.dbdoclet.tag.docbook.Date;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Keyword;
import org.dbdoclet.tag.docbook.Keywordset;
import org.dbdoclet.tag.docbook.Personname;
import org.dbdoclet.tag.docbook.Subject;
import org.dbdoclet.tag.docbook.Subjectset;
import org.dbdoclet.tag.docbook.Subjectterm;
import org.dbdoclet.tag.html.Meta;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class MetaEditor extends DitaEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DitaTagFactory tagFactory = getTagFactory();
		return finalizeValues();
	}
}
