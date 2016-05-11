/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor;

import java.util.HashMap;

import org.dbdoclet.option.OptionException;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.dita.SectionDetector;

public class HeadingEditor extends DitaEditor {

	public static HashMap<String, HashMap<String, String>> validHtmlParentPathMap;

	static {

		validHtmlParentPathMap = new HashMap<String, HashMap<String, String>>();
		validHtmlParentPathMap.put(new HtmlDocument().getNodeName(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.Html.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(HtmlElement.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(HtmlElement.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(HtmlElement.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(HtmlElement.getTag(),
				HtmlElement.getAttributeMap());
	}

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		try {

			DitaTagFactory tagFactory = getTagFactory();
			SectionDetector sectionDetector = new SectionDetector();
			sectionDetector.setScript(script);
			sectionDetector.setTagFactory(tagFactory);
			sectionDetector.edit(values);

			setValues(super.edit(values));
			return finalizeValues();

		} catch (OptionException oops) {

			throw new EditorException(oops);
		}
	}

}
