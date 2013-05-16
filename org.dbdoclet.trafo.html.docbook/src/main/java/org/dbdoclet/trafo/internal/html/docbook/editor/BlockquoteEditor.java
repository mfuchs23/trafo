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
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class BlockquoteEditor extends Editor {

    @Override
	public EditorInstruction edit(EditorInstruction values) throws EditorException {

	setValues(super.edit(values));
	DocBookTagFactory dbfactory = values.getTagFactory();

	BlockQuote blockquote = dbfactory.createBlockQuote();

	DocBookElement parent = getParent();

	parent.appendChild(blockquote);
	blockquote.setParentNode(parent);

	Para para = dbfactory.createPara();

	blockquote.appendChild(para);
	para.setParentNode(blockquote);

	setCurrent(para);
	traverse(true);

	return finalizeValues();
    }
}
