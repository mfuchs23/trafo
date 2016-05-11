/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import java.util.Iterator;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;

public class TdEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		BaseTagFactory dbfactory = getTagFactory();

		boolean decomposeTables = script.isParameterOn(
				TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_DECOMPOSE_TABLES,
				TrafoConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables || getHtmlElement().isMute()) {
			traverse(true);
			return finalizeValues();
		}

		org.dbdoclet.tag.html.Td td = (org.dbdoclet.tag.html.Td) getHtmlElement();
		org.dbdoclet.tag.docbook.Entry entry = dbfactory.createEntry();
		copyCommonAttributes(getHtmlElement(), entry);

		NodeListImpl children = td.getTrafoChildNodes();
		Iterator<NodeImpl> iterator = children.iterator();
		NodeImpl node;

		while (iterator.hasNext()) {

			node = iterator.next();

			if (node instanceof org.dbdoclet.tag.html.Table) {
				traverse(true);
				return finalizeValues();
			}
		}

		entry.setAlign(td.getAlign());
		entry.setChar(td.getChar());
		entry.setCharOff(td.getCharOff());
		entry.setVAlign(td.getVAlign());

		int colspan = td.getColspan();

		if (colspan > 1) {

			entry.setNameSt("c" + td.getIndex());
			entry.setNameEnd("c" + ((td.getIndex() + colspan) - 1));
		}

		int rowspan = td.getRowspan();

		if (rowspan > 0) {
			entry.setMorerows(rowspan);
		}

		org.dbdoclet.tag.docbook.Para para = dbfactory.createPara();
		para.setFormatType(ElementImpl.FORMAT_INLINE);

		entry.appendChild(para);
		para.setParentNode(entry);

		getParent().appendChild(entry);
		entry.setParentNode(getParent());

		setCurrent(para);
		traverse(true);

		return finalizeValues();
	}
}
