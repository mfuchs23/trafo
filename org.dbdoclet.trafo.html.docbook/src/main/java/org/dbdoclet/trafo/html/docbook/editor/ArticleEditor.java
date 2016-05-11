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
import org.dbdoclet.tag.docbook.Section;
import org.dbdoclet.tag.html.Header;
import org.dbdoclet.tag.html.HeadingElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Element;

public class ArticleEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		BaseTagFactory dbfactory = getTagFactory();

		NodeImpl parent = getParent();
		HtmlElement article = values.getHtmlElement();
		Element firstElement = article.getFirstChildElement();

		if (isSection(parent)
				&& (firstElement instanceof HeadingElement || firstElement instanceof Header)) {

			Section sect = dbfactory.createSection();
			parent.appendChild(sect);
			values.setCurrent(sect);
		}

		setValues(values);
		return finalizeValues();
	}
}
