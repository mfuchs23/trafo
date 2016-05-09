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
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class BlockquoteEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		Blockquote blockquote = dbfactory.createBlockquote();

		NodeImpl parent = getParent();

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
