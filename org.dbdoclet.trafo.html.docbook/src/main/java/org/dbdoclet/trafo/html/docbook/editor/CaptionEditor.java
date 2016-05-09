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
import org.dbdoclet.tag.docbook.Title;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class CaptionEditor extends DocBookEditor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	BaseTagFactory dbfactory = getTagFactory();

	Title title = dbfactory.createTitle();

	getParent().getTrafoParentNode().insertChild(0, title);
	setCurrent(title);

	traverse(true);

	return finalizeValues();
    }
}
