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
import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.docbook.Book;
import org.dbdoclet.tag.docbook.Chapter;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Part;
import org.dbdoclet.tag.docbook.RefEntry;
import org.dbdoclet.tag.docbook.RefNameDiv;
import org.dbdoclet.tag.docbook.RefSection;
import org.dbdoclet.tag.docbook.Reference;
import org.dbdoclet.tag.html.Div;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.dita.SectionDetector;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class DivEditor extends DitaEditor {

	private static final int TIP = 1;
	private static final int NOTE = 2;
	private static final int WARNING = 3;
	private static final int CAUTION = 4;
	private static final int IMPORTANT = 5;

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DitaTagFactory tagFactory = getTagFactory();
		return finalizeValues();
	}
}
