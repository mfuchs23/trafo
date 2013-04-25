/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import java.util.HashMap;

import org.dbdoclet.option.OptionException;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.internal.html.docbook.SectionDetector;

public class HeadingEditor extends Editor {

	public static HashMap<String, HashMap<String, String>> validHtmlParentPathMap;

	static {

		validHtmlParentPathMap = new HashMap<String, HashMap<String, String>>();
		validHtmlParentPathMap.put(new HtmlDocument().getNodeName(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.Html.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.Body.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.Center.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.Div.getTag(),
				HtmlElement.getAttributeMap());
		validHtmlParentPathMap.put(org.dbdoclet.tag.html.A.getTag(),
				HtmlElement.getAttributeMap());
	}

	// private HeaderElement header;

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		try {

			SectionDetector sectionManager = new SectionDetector();
			sectionManager.edit(values);

			setValues(super.edit(values));
			return finalizeValues();

		} catch (OptionException oops) {

			throw new EditorException(oops);
		} // end of try-catch
	}

}
