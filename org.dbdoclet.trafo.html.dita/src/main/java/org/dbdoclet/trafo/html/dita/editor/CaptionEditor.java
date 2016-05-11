/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor;

import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.Title;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class CaptionEditor extends DitaEditor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DitaTagFactory tagFactory = (DitaTagFactory) getTagFactory();

	Title title = tagFactory.createTitle();

	getParent().getTrafoParentNode().insertChild(0, title);
	setCurrent(title);

	traverse(true);

	return finalizeValues();
    }
}
