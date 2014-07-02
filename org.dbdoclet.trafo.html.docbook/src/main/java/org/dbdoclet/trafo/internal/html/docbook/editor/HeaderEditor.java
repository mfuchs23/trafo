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
import org.dbdoclet.tag.docbook.Info;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;


public class HeaderEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		DocBookTagFactory dbfactory = getTagFactory();
		DocBookElement parent = (DocBookElement) values.getParent();
		
		if (parent.isSection()) {
			
			Info info = dbfactory.createInfo();
			parent.appendChild(info);
			setCurrent(info);
		}

		return finalizeValues();
	}
}
