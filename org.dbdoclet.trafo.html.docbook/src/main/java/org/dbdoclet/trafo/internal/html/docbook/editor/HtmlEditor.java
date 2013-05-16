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
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class HtmlEditor extends Editor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = values.getTagFactory();

		Article article = dbfactory.createArticle();

		HtmlElement child = getHtmlElement();

		String lang = child.getAttribute("lang");

		if (lang == null) {
			lang = child.getAttribute("xml:lang");
		}

		if (lang != null) {
			article.setAttribute("lang", lang.toLowerCase());
		}

		setCurrent(article);
		traverse(true);

		article.setAttribute("xmlns", "http://docbook.org/ns/docbook");
		article.setAttribute("version", "5.0");

		if (lang != null) {
			article.setAttribute("xml:lang", lang.toLowerCase());
		}

		getParent().appendChild(getCurrent());

		return finalizeValues();
	}
}
