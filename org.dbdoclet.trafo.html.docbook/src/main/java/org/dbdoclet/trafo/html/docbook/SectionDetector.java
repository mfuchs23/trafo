package org.dbdoclet.trafo.html.docbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dbdoclet.option.OptionException;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.Article;
import org.dbdoclet.tag.docbook.Book;
import org.dbdoclet.tag.docbook.Bridgehead;
import org.dbdoclet.tag.docbook.Chapter;
import org.dbdoclet.tag.docbook.DocBookDocument;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookFragment;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Part;
import org.dbdoclet.tag.docbook.Partintro;
import org.dbdoclet.tag.docbook.Refentry;
import org.dbdoclet.tag.docbook.Refname;
import org.dbdoclet.tag.docbook.Refnamediv;
import org.dbdoclet.tag.docbook.Refpurpose;
import org.dbdoclet.tag.docbook.Refsect1;
import org.dbdoclet.tag.docbook.Refsect2;
import org.dbdoclet.tag.docbook.Refsect3;
import org.dbdoclet.tag.docbook.Refsect4;
import org.dbdoclet.tag.docbook.Refsect5;
import org.dbdoclet.tag.docbook.Reference;
import org.dbdoclet.tag.docbook.Sect1;
import org.dbdoclet.tag.docbook.Sect2;
import org.dbdoclet.tag.docbook.Sect3;
import org.dbdoclet.tag.docbook.Sect4;
import org.dbdoclet.tag.docbook.Sect5;
import org.dbdoclet.tag.docbook.Section;
import org.dbdoclet.tag.docbook.Title;
import org.dbdoclet.tag.html.HeadingElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.editor.DefaultEditor;
import org.dbdoclet.trafo.html.docbook.editor.HeadingEditor;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SectionDetector {

	private static Class<?>[] bookMap = { Book.class, Chapter.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] articleMap = { Article.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] partIntroMap = { Partintro.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] chapterMap = { Chapter.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] sect1Map = { Sect1.class, Sect2.class,
			Sect3.class, Sect4.class, Sect5.class, Sect5.class };

	private static Class<?>[] sect2Map = { Sect2.class, Sect3.class,
			Sect4.class, Sect5.class, Sect5.class, Sect5.class };

	private static Class<?>[] sect3Map = { Sect3.class, Sect4.class,
			Sect5.class, Sect5.class, Sect5.class, Sect5.class };

	private static Class<?>[] referenceMap = { Reference.class, Refentry.class,
			Refsect1.class, Refsect2.class, Refsect3.class, Refsect4.class };

	private static Class<?>[] refSect1Map = { Refsect1.class, Refsect2.class,
			Refsect3.class, Refsect4.class, Refsect5.class, Refsect5.class };

	private static Class<?>[] refSect2Map = { Refsect2.class, Refsect3.class,
			Refsect4.class, Refsect5.class, Refsect5.class, Refsect5.class };

	private static Class<?>[] refSect3Map = { Refsect3.class, Refsect4.class,
			Refsect5.class, Refsect5.class, Refsect5.class, Refsect5.class };

	private Class<?>[] map;
	private EditorInstruction values;
	private Script script;
	private DocBookTagFactory dbfactory;
	private LinkManager linkManager;

	public boolean isSection(HtmlElement element) {

		/*
		 * H1-6 Elemente, die als erste Kindelemente von Article oder Section
		 * auftreten, werden als einfache Titelelemente verarbeitet. Der gleiche
		 * Fall gilt für Kindelemente von Header.
		 */
		Node parentNode = element.getParentNode();
		
		if (element instanceof HeadingElement && parentNode != null && parentNode instanceof DocumentFragment == false) {

			String tagName = ((Element) parentNode)
					.getTagName();

			if (tagName != null && tagName.equalsIgnoreCase("header")) {
				return false;
			}

			if (tagName != null
					&& (tagName.equalsIgnoreCase("section") || tagName
							.equalsIgnoreCase("article"))) {

				if (element.isFirstChildElement()) {
					return false;
				}
			}
		}

		return getSectionLevel(element) > 0 ? true : false;
	}

	public int getSectionLevel(HtmlElement element) {

		if (element == null) {
			return 0;
		}

		if (element instanceof HeadingElement) {
			return ((HeadingElement) element).getLevel();
		}

		String tagName = element.getTagName();
		tagName = tagName.trim().toLowerCase();

		if (tagName.startsWith("h")) {

			tagName = StringServices.cutPrefix(tagName, "h");

			int level = -1;

			try {
				level = Integer.parseInt(tagName);
			} catch (NumberFormatException oops) {
				return -1;
			}

			return level;
		}

		TextParam paramClasses = (TextParam) script.getParameter(
				TrafoConstants.SECTION_SECTION_DETECTION,
				TrafoConstants.PARAM_ATTRIBUTE_CLASS);

		String cssClass = element.getCssClass();

		if (cssClass == null || paramClasses == null) {
			return 0;
		}

		for (String regex : paramClasses.getValues()) {

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(cssClass);

			if (matcher.matches()) {

				int level = 7;

				int groupCount = matcher.groupCount();

				if (groupCount > 0) {
					String group = matcher.group(groupCount);
					try {
						level = Integer.parseInt(group);
					} catch (NumberFormatException oops) {
						level = 7;
					}
				}

				return level;
			}
		}

		return 0;
	}

	public void edit(EditorInstruction values, DocBookTagFactory dbfactory) {

		this.values = values;

		DocBookElement sect;

		HtmlElement child = values.getHtmlElement();
		NodeImpl parent = values.getParent();

		if (parent != null) {
			values.setCurrent(parent);
		}

		values.doTraverse(true);

		NodeImpl levelParent = null;
		int level = 7;

		if (child instanceof HeadingElement) {
			level = ((HeadingElement) child).getLevel();
		} else {
			level = getSectionLevel(child);
		}

		Element root = initMap();
		levelParent = findParentForLevel(child, level);

		if (levelParent instanceof DocumentFragment) {
			NodeImpl documentElement = (NodeImpl) levelParent
					.getUserData("documentElement");
			sect = createSectionChild(child, documentElement);
		} else {
			sect = createSectionChild(child, levelParent);
		}

		if (sect != null) {

			if (sect instanceof Para) {

				values.doTraverse(false);

				// Try to force a new paragraph after the header.
				if (parent instanceof Para && (parent.getParentNode() != null)) {
					values.setParent((DocBookElement) parent.getParentNode());
				}

				values.setCurrent(values.getParent());

			} else {

				if (levelParent != null) {

					levelParent.appendChild(sect);

					if (sect instanceof Refentry) {

						Refnamediv refNameDiv = dbfactory.createRefnamediv();
						Refname refName = dbfactory.createRefname("");
						Refpurpose refPurpose = dbfactory.createRefpurpose();

						refNameDiv.appendChild(refName);
						refNameDiv.appendChild(refPurpose);

						Refsect1 refSect1 = dbfactory.createRefsect1();

						sect.appendChild(refNameDiv);
						sect.appendChild(refSect1);
						sect = refSect1;
					}

					if (sect instanceof Bridgehead) {

						// setParent(sect);
						values.setCurrent(sect);

					} else {

						Title title = dbfactory.createTitle();
						sect.appendChild(title);
						values.setParent(sect);
						values.setCurrent(title);
					}
				}
			}

		} else {

			NodeImpl current = values.getCurrent();

			if (current instanceof DocBookElement) {
				sect = (DocBookElement) current;
			}
		}

		String id = child.getId();

		if (sect != null && id != null && id.length() > 0) {
			sect.setId(linkManager.createUniqueId(id));
		}

		if (root != null) {
			root.getClass().getName();
		}
	}

	private Element initMap() {

		Element root = getDocumentElement();

		if (map != null) {
			return root;
		}

		if (isRootBook() || isRoot(Part.getTag())) {
			map = bookMap;
		}

		if (isRootArticle()) {
			map = articleMap;
		}

		if (isRoot(Chapter.getTag())) {
			map = chapterMap;
		}

		if (isRoot(Partintro.getTag())) {
			map = partIntroMap;
		}

		if (isRoot(Sect1.tagName)) {
			map = sect1Map;
		}

		if (isRoot(Sect2.getTag())) {
			map = sect2Map;
		}

		if (isRoot(Sect3.getTag())) {
			map = sect3Map;
		}

		if (isRoot(Reference.getTag())) {
			map = referenceMap;
		}

		if (isRoot(Refsect1.tagName)) {
			map = refSect1Map;
		}

		if (isRoot(Refsect2.tagName)) {
			map = refSect2Map;
		}

		if (isRoot(Refsect3.tagName)) {
			map = refSect3Map;
		}

		if (map == null) {
			map = articleMap;
		}

		return root;
	}

	public boolean isRoot(String tagName) {

		Node root = getDocumentElement();

		if (root instanceof DocBookFragment) {

			Node firstChild = ((DocBookFragment) root).getFirstChildElement();

			if (firstChild != null) {
				root = firstChild;
			}
		}

		if (root == null) {
			throw new IllegalStateException("The field root must not be null!");
		}

		if (tagName.equalsIgnoreCase(root.getNodeName())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isRootArticle() {

		Node root = getDocumentElement();

		if (root instanceof DocBookFragment) {			
			root = (Element) root.getUserData("documentElement");
		}
		
		if (root instanceof Article) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isRootBook() {

		Element root = getDocumentElement();

		if (root instanceof DocBookFragment) {
			root = (Element) root.getUserData("documentElement");
		}

		if (root instanceof Book) {
			return true;
		} else {
			return false;
		}
	}

	private Element getDocumentElement() {

		NodeImpl rootNode = values.getCurrent().getRoot();

		Element root = null;

		if (rootNode instanceof DocBookDocument) {

			DocBookDocument doc = (DocBookDocument) rootNode;
			root = doc.getDocumentElement();

		} else if (rootNode instanceof Element) {

			root = (Element) rootNode;

		} else {
			throw new IllegalStateException(
					"Root node must be of type DocBookDocument or Element."
							+ "Found root node of type "
							+ rootNode.getClass().getName() + "!");
		}

		return root;
	}

	public NodeImpl findParentForLevel(HtmlElement header, int level) {

		NodeImpl parent = null;
		Class<?> parentClass = null;

		if (level < 0) {
			level = 0;
		}

		if (level > map.length) {
			level = map.length - 1;
		}

		for (int i = (level - 1); i >= 0; i--) {

			parentClass = map[i];

			parent = values.getCurrent();

			if (parent != null) {

				int tagLevel = getSectionLevel(header);
				int parentLevel = getSectionLevel((HtmlElement) parent
						.getUserData("html"));

				while (tagLevel != -1 && parentLevel != -1
						&& parentLevel >= tagLevel) {

					NodeImpl ancestor = NodeImpl
							.findParent(parent, parentClass);

					if (ancestor == null) {	
						ancestor = NodeImpl
							.findParent(parent, DocumentFragment.class);
					}
					
					if (ancestor == null) {
						break;
					}

					parent = ancestor;

					parentLevel = getSectionLevel((HtmlElement) parent
							.getUserData("html"));
				}

				break;
			}
		}

		return parent;
	}

	public DocBookElement createSectionChild(HtmlElement header,
			NodeImpl levelParent) throws OptionException {

		DocBookElement section = null;

		if (levelParent == null || levelParent instanceof Para
				|| isValidHeader(header) == false) {
			section = dbfactory.createBridgehead();
		}

		if (levelParent instanceof Book || levelParent instanceof Part) {
			section = dbfactory.createChapter();
		}

		if (levelParent instanceof Chapter || levelParent instanceof Partintro) {
			section = dbfactory.createSection();
		}

		if (levelParent instanceof Article) {
			section = dbfactory.createSection();
		}

		if (levelParent instanceof Section) {
			section = dbfactory.createSection();
		}

		if (levelParent instanceof Sect1) {
			section = dbfactory.createSect2();
		}

		if (levelParent instanceof Sect2) {
			section = dbfactory.createSect3();
		}

		if (levelParent instanceof Sect3) {
			section = dbfactory.createSect4();
		}

		if (levelParent instanceof Sect4) {
			section = dbfactory.createSect5();
		}

		if (levelParent instanceof Sect5) {
			section = dbfactory.createSimplesect();
		}

		if (levelParent instanceof Reference) {
			section = dbfactory.createRefentry();
		}

		if (levelParent instanceof Refentry) {
			section = dbfactory.createRefsect1();
		}

		if (levelParent instanceof Refsect1) {
			section = dbfactory.createRefsect2();
		}

		if (levelParent instanceof Refsect2) {
			section = dbfactory.createRefsect3();
		}

		if (levelParent instanceof Refsect3) {
			section = dbfactory.createRefsect4();
		}

		if (levelParent instanceof Refsect4) {
			section = dbfactory.createRefsect5();
		}

		if (levelParent instanceof Refsect5) {
			section = dbfactory.createSimplesect();
		}

		if (section != null) {
			DefaultEditor editor = new DefaultEditor();
			editor.setLinkManager(linkManager);
			editor.setTagFactory(dbfactory);
			editor.setValues(values);
			editor.copyCommonAttributes(header, section);
		}

		return section;
	}

	public DocBookElement createSectionPara(Element parent)
			throws OptionException {

		DocBookElement para;

		if ((parent != null) && parent instanceof Para) {

			para = (DocBookElement) parent;

		} else {

			para = dbfactory.createPara();

			if (((Para) para)
					.isValidParent(script.getTransformPosition(), (DocBookElement) values.getParent())) {
				values.getParent().appendChild(para);
			} else {
				para = (DocBookElement) values.getParent();
			}
		}

		Bridgehead Bridgehead = dbfactory.createBridgehead();
		Bridgehead.appendChild(values.getHtmlElement().getTextContent());
		para.appendChild(Bridgehead);

		// Emphasis emph = dbfactory.createEmphasis();
		// emph.setRole(getOptions().getEmphasisBoldRole());
		// emph.setParentNode(para);
		// emph.setRemap(header.getNodeName());
		// emph.appendChild(getChild().getText());
		// para.appendChild(emph);

		return para;
	}

	private boolean isValidHeader(HtmlElement header) {

		boolean rc = header
				.validateParentPath(HeadingEditor.validHtmlParentPathMap);
		return rc;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {
		this.dbfactory = dbfactory;
	}

	public void setLinkManager(LinkManager linkManager) {
		this.linkManager = linkManager;
	}
}
