/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import java.util.List;

import org.dbdoclet.tag.docbook.Abstract;
import org.dbdoclet.tag.docbook.Author;
import org.dbdoclet.tag.docbook.Date;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Info;
import org.dbdoclet.tag.docbook.Keyword;
import org.dbdoclet.tag.docbook.Keywordset;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Personname;
import org.dbdoclet.tag.docbook.Subject;
import org.dbdoclet.tag.docbook.Subjectset;
import org.dbdoclet.tag.docbook.Subjectterm;
import org.dbdoclet.tag.html.Meta;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class MetaEditor extends Editor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		DocBookTagFactory dbfactory = values.getTagFactory();
		traverse(false);

		DocBookElement current = getCurrent();
		NodeImpl root = current.getRoot();
		ElementImpl info = (ElementImpl) root.getFirstElement();

		if (info == null) {
			info = dbfactory.createInfo();
			root.appendChild(info);
		}

		if (info instanceof Info == false) {
			info = dbfactory.createInfo();
			root.insertChild(0, info);
		}

		Meta meta = (Meta) getHtmlElement();
		String name = meta.getName();
		String content = meta.getContent();

		if (name != null && name.trim().equalsIgnoreCase("author")) {
			insertAuthor(dbfactory, info, content);
		}

		if (name != null && name.trim().equalsIgnoreCase("date")) {
			insertDate(dbfactory, info, content);
		}

		if (name != null && name.trim().equalsIgnoreCase("description")) {
			insertAbstract(dbfactory, info, content);
		}

		if (name != null && name.trim().equalsIgnoreCase("keywords")) {
			insertKeywordset(dbfactory, info, content);
		}

		if (name != null && name.trim().equalsIgnoreCase("subject")) {
			insertSubjectset(dbfactory, info, content);
		}

		return finalizeValues();
	}

	private void insertAuthor(DocBookTagFactory dbfactory, ElementImpl info,
			String content) {

		Author author = dbfactory.createAuthor();
		Personname personname = dbfactory.createPersonname();
		author.appendChild(personname);
		personname.setTextContent(content);

		List<Author> authorList = info.findChildren(Author.class);

		if (authorList.size() == 0) {
			info.appendChild(author);
		} else {
			info.insertAfter(author, authorList.get(authorList.size() - 1));
		}
	}

	private void insertDate(DocBookTagFactory dbfactory, ElementImpl info,
			String content) {

		Date date = dbfactory.createDate();
		date.setTextContent(content);

		List<Date> dateList = info.findChildren(Date.class);

		if (dateList.size() == 0) {
			info.appendChild(date);
		} else {
			info.insertAfter(date, dateList.get(dateList.size() - 1));
		}
	}

	private void insertAbstract(DocBookTagFactory dbfactory, ElementImpl info,
			String content) {

		Abstract description = dbfactory.createAbstract();
		Para para = dbfactory.createPara(content);
		description.appendChild(para);

		List<Abstract> abstractList = info.findChildren(Abstract.class);

		if (abstractList.size() == 0) {
			info.appendChild(description);
		} else {
			info.insertAfter(description,
					abstractList.get(abstractList.size() - 1));
		}
	}

	private void insertKeywordset(DocBookTagFactory dbfactory,
			ElementImpl info, String content) {

		if (content == null || content.trim().length() == 0) {
			return;
		}

		Keywordset keywordset = dbfactory.createKeywordset();

		for (String token : content.split(",")) {
			Keyword keyword = dbfactory.createKeyword();
			keyword.setTextContent(token.trim());
			keywordset.appendChild(keyword);
		}

		List<Keywordset> keywordsetList = info.findChildren(Keywordset.class);

		if (keywordsetList.size() == 0) {
			info.appendChild(keywordset);
		} else {
			info.insertAfter(keywordset,
					keywordsetList.get(keywordsetList.size() - 1));
		}
	}

	private void insertSubjectset(DocBookTagFactory dbfactory,
			ElementImpl info, String content) {

		if (content == null || content.trim().length() == 0) {
			return;
		}

		List<Subjectset> subjectsetList = info.findChildren(Subjectset.class);

		Subjectset subjectset = null;

		if (subjectsetList.size() == 0) {
			subjectset = dbfactory.createSubjectset();
		} else {
			subjectset = subjectsetList.get(0);
		}

		Subject subject = dbfactory.createSubject();
		subjectset.appendChild(subject);
		Subjectterm subjectterm = dbfactory.createSubjectterm();
		subjectterm.setTextContent(content);
		subject.appendChild(subjectterm);
		info.appendChild(subjectset);
	}
}
