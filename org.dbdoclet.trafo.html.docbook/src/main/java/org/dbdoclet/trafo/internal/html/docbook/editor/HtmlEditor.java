/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.Article;
import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.XmlConstants;

public class HtmlEditor extends DocBookEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		BaseTagFactory dbfactory = getTagFactory();

		Article article = dbfactory.createArticle();

		HtmlElement child = getHtmlElement();

		String lang = child.getAttribute("lang");

		if (lang == null) {
			lang = child.getAttributeNS(XmlConstants.NAMESPACE_XML, "lang");
		}

		setCurrent(article);
		traverse(true);

		article.setAttribute("xmlns", "http://docbook.org/ns/docbook");
		article.setAttribute("version", "5.0");

		if (lang != null) {
			article.setAttributeNS(XmlConstants.NAMESPACE_XML, "xml:lang", lang.toLowerCase());
		}

		getParent().appendChild(getCurrent());

		return finalizeValues();
	}
}
