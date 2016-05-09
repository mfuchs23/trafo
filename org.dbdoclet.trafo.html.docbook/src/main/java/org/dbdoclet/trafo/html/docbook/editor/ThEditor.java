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
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class ThEditor extends DocBookEditor {

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

		org.dbdoclet.tag.html.Th th = (org.dbdoclet.tag.html.Th) getHtmlElement();
		org.dbdoclet.tag.docbook.Entry entry = dbfactory.createEntry();

		entry.setAlign(th.getAlign());
		entry.setChar(th.getChar());
		entry.setCharOff(th.getCharOff());
		entry.setVAlign(th.getVAlign());

		int colspan = th.getColspan();

		if (colspan > 1) {

			entry.setNameSt("c" + th.getIndex());
			entry.setNameEnd("c" + ((th.getIndex() + colspan) - 1));
		}

		int rowspan = th.getRowspan();

		if (rowspan > 0) {
			entry.setMorerows(rowspan);
		}

		org.dbdoclet.tag.docbook.Para para = dbfactory.createPara();
		para.setFormatType(ElementImpl.FORMAT_INLINE);

		org.dbdoclet.tag.docbook.Emphasis emph = dbfactory
				.createEmphasis();

		emph.setRole(TrafoConstants.DEFAULT_EMPHASIS_ROLE_BOLD);

		entry.appendChild(para);
		para.appendChild(emph);

		getParent().appendChild(entry);
		setCurrent(emph);

		traverse(true);

		return finalizeValues();
	}
}
