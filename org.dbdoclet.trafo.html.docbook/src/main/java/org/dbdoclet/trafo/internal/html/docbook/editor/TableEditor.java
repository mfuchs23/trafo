/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import java.util.ArrayList;
import java.util.Iterator;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.Entrytbl;
import org.dbdoclet.tag.html.Td;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Element;

public class TableEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = getTagFactory();

		boolean decomposeTables = script.isParameterOn(
				TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_DECOMPOSE_TABLES,
				TrafoConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables || getHtmlElement().isMute()) {
			traverse(true);
			return finalizeValues();
		}

		org.dbdoclet.tag.html.Table htmlTable = (org.dbdoclet.tag.html.Table) getHtmlElement();

		NodeImpl htmlTableParent = htmlTable.getTrafoParentNode();
		ArrayList<Element> htmlParentChildList = htmlTableParent
				.getChildElementList();

		/*
		 * Falls sich die HTML-Tabelle innerhalb einer sie umgebenden
		 * Tabellenzelle liegt und diese mehrere Kindelemente ent
		 */
		NodeImpl tdParent = htmlTable.findParent(Td.class);

		if (tdParent != null) {
			htmlParentChildList = tdParent.getChildElementList();
		}

		NodeImpl parent = getParent();
		NodeImpl grandParent = parent.getTrafoParentNode();

		if (parent instanceof org.dbdoclet.tag.docbook.Row) {

			if (htmlParentChildList.size() > 1) {

				Entry entry = dbfactory.createEntry();
				parent.appendChild(entry);
				parent = entry;
				setParent(entry);
				setCurrent(entry);
				addTable(dbfactory, htmlTable, parent);

			} else {

				addSubtable(dbfactory, htmlTable, parent);
			}

		} else if ((grandParent != null)
				&& parent instanceof org.dbdoclet.tag.docbook.Entry) {

			if (htmlParentChildList.size() > 1) {
				addTable(dbfactory, htmlTable, parent);
			} else {
				addSubtable(dbfactory, htmlTable, grandParent);
			}

		} else {

			addTable(dbfactory, htmlTable, parent);
		}

		return finalizeValues();
	}

	private void addSubtable(DocBookTagFactory dbfactory,
			org.dbdoclet.tag.html.Table htmlTable, NodeImpl parent) {

		ArrayList<String> colWidthList = htmlTable.getColWidths();

		Entrytbl entrytbl = dbfactory.createEntrytbl();
		entrytbl.setCols(htmlTable.getNumOfCols());

		Iterator<String> iterator = colWidthList.iterator();
		int index = 1;
		String width;

		while (iterator.hasNext()) {

			width = iterator.next();
			entrytbl.appendChild(dbfactory.createColspec("c" + index, width));
			index++;
		}

		entrytbl.setParentNode(parent);
		parent.appendChild(entrytbl);
		setCurrent(entrytbl);

		traverse(true);
	}

	private void addTable(DocBookTagFactory dbfactory,
			org.dbdoclet.tag.html.Table htmlTable, NodeImpl parent) {

		ArrayList<String> colWidthList = htmlTable.getColWidths();

		org.dbdoclet.tag.docbook.Table table = null;

		String caption = htmlTable.getCaption();

		if ((caption != null) && (caption.length() > 0)) {

			table = dbfactory.createTable();

		} else {

			table = dbfactory.createInformaltable();
		}

		copyCommonAttributes(htmlTable, table);

		String tableStyle = script.getTextParameter(
				TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_TABLE_STYLE,
				TrafoConstants.DEFAULT_TABLE_STYLE);

		table.setFrame(tableStyle);

		org.dbdoclet.tag.docbook.Tgroup tgroup = dbfactory.createTgroup();
		colWidthList = editWidths(colWidthList);

		Iterator<String> iterator = colWidthList.iterator();
		int index = 1;
		String width;

		while (iterator.hasNext()) {

			width = iterator.next();
			tgroup.appendChild(dbfactory.createColspec("c" + index, width));
			index++;
		}

		if (htmlTable.hasBorder() == false) {
			table.setFrame("none");
		} else {
			table.setFrame("all");
		}

		transferId(htmlTable, table);

		tgroup.setCols(colWidthList.size());
		table.appendChild(tgroup);

		parent.appendChild(table);
		setCurrent(tgroup);
		traverse(true);
	}

	private ArrayList<String> editWidths(ArrayList<String> list) {

		int percent = 0;
		int i;
		int withoutWidth = 0;

		String width;
		Iterator<String> iterator;

		int index = 0;
		iterator = list.iterator();

		while (iterator.hasNext()) {

			width = iterator.next();

			if ((width == null) || (width.length() == 0)) {
				withoutWidth++;
				continue;
			}

			width = width.trim();

			if (width.endsWith("%") || width.matches("\\d+")) {

				width = width.substring(0, width.length() - 1);

				try {
					i = Integer.parseInt(width);
				} catch (NumberFormatException oops) {
					continue;
				}

				percent += i;

				list.set(index, String.valueOf(i) + "*");
			}

			index++;
		}

		index = 0;

		if ((percent < 100) && (withoutWidth > 0)) {
			percent = (100 - percent) / withoutWidth;
		} else {
			percent = 10;
		}

		iterator = list.iterator();

		while (iterator.hasNext()) {

			width = iterator.next();

			if ((width == null) || (width.length() == 0)) {

				list.set(index, String.valueOf(percent) + "*");
			}

			index++;
		}

		return list;
	}
}
