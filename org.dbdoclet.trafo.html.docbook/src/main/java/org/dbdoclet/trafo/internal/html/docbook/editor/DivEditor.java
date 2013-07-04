/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

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
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.SectionDetector;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class DivEditor extends DocBookEditor {

	private static final int TIP = 1;
	private static final int NOTE = 2;
	private static final int WARNING = 3;
	private static final int CAUTION = 4;
	private static final int IMPORTANT = 5;

	private void addAdmonition(DocBookTagFactory dbfactory, int type,
			String title) {

		DocBookElement admon;
		NodeImpl parent = getParent();

		switch (type) {
		case CAUTION:
			admon = dbfactory.createCaution();
			break;
		case IMPORTANT:
			admon = dbfactory.createImportant();
			break;
		case NOTE:
			admon = dbfactory.createNote();
			break;
		case TIP:
			admon = dbfactory.createTip();
			break;
		case WARNING:
			admon = dbfactory.createWarning();
			break;
		default:
			admon = null;
			break;
		}

		if (admon == null) {
			return;
		}

		if (title != null && title.length() > 0) {
			admon.appendChild(dbfactory.createTitle(title));
		}

		Para para = dbfactory.createPara();
		admon.appendChild(para);
		parent.appendChild(admon);
		setCurrent(para);
	}

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		Script script = values.getScript();
		DocBookTagFactory dbfactory = getTagFactory();
		
		Div div = (Div) getHtmlElement();

		SectionDetector sectionDetector = new SectionDetector();
		sectionDetector.setScript(script);
		sectionDetector.setTagFactory(dbfactory);

		if (sectionDetector.isSection(div)) {
			sectionDetector.edit(values, dbfactory);
			setValues(values);
			return finalizeValues();
		}


		String type = null;

		String id = div.getId();

		String clazz = div.getCssClass();
		String title = div.getTitle();

		NodeImpl parent = getParent();

		traverse(true);
		ignore(false);

		if ((id != null) && (id.length() > 0)) {
			type = id.toLowerCase();
		}

		if ((type == null) && (clazz != null) && (clazz.length() > 0)) {
			type = clazz.toLowerCase();
		}

		if ((type != null) && (type.length() > 0)) {

			if (type.startsWith("example")) {

				if ((title != null) && (title.length() > 0)) {
					setCurrent(dbfactory.createExample(title));
				} else {
					setCurrent(dbfactory.createInformalExample());
				}

				parent.appendChild(getCurrent());
				getCurrent().setParentNode(parent);
				return finalizeValues();
			}

			if (type.startsWith("formalpara")) {

				if ((title != null) && (title.length() > 0)) {
					setCurrent(dbfactory.createFormalPara(title));
				} else {
					setCurrent(dbfactory.createFormalPara());
				}

				parent.appendChild(getCurrent());
				return finalizeValues();
			}

			if (type.startsWith("caution")) {

				addAdmonition(dbfactory, CAUTION, title);
				return finalizeValues();
			}

			if (type.startsWith("important")) {

				addAdmonition(dbfactory, IMPORTANT, title);
				return finalizeValues();
			}

			if (type.startsWith("note")) {
				
				addAdmonition(dbfactory, NOTE, title);
				return finalizeValues();
			}

			if (type.startsWith("tip") || type.startsWith("hint")) {

				addAdmonition(dbfactory, TIP, title);
				return finalizeValues();
			}

			if (type.startsWith("warning")) {

				addAdmonition(dbfactory, WARNING, title);
				return finalizeValues();
			}

		}

		if (parent instanceof Book || parent instanceof Part) {

			Chapter chapter = dbfactory.createChapter(AUTOMATICALLY_INSERTED);
			parent.appendChild(chapter);

			Para para = dbfactory.createPara();
			chapter.appendChild(para);

			parent = para;
			setCurrent(para);
		}

		if (parent instanceof Reference) {

			RefEntry refEntry = dbfactory.createRefEntry();
			parent.appendChild(refEntry);

			RefNameDiv refNameDiv = dbfactory.createRefNameDiv();
			refNameDiv.appendChild(dbfactory
					.createRefName(AUTOMATICALLY_INSERTED));
			refNameDiv.appendChild(dbfactory.createRefPurpose());
			refEntry.appendChild(refNameDiv);

			RefSection refSection = dbfactory
					.createRefSection(AUTOMATICALLY_INSERTED);
			refEntry.appendChild(refSection);

			Para para = dbfactory.createPara();
			refSection.appendChild(para);

			parent = para;
			setCurrent(para);
		}

		if (id != null) {

			Anchor anchor = dbfactory.createAnchor();
			anchor.validate();
			copyCommonAttributes(getHtmlElement(), anchor);
			parent.appendChild(anchor);
		}

		traverse(true);
		ignore(true);

		return finalizeValues();
	}
}
