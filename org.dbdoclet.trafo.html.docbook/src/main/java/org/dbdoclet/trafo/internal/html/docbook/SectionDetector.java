package org.dbdoclet.trafo.internal.html.docbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dbdoclet.option.OptionException;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.Article;
import org.dbdoclet.tag.docbook.Book;
import org.dbdoclet.tag.docbook.BridgeHead;
import org.dbdoclet.tag.docbook.Chapter;
import org.dbdoclet.tag.docbook.DocBookDocument;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookFragment;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Part;
import org.dbdoclet.tag.docbook.PartIntro;
import org.dbdoclet.tag.docbook.RefEntry;
import org.dbdoclet.tag.docbook.RefName;
import org.dbdoclet.tag.docbook.RefNameDiv;
import org.dbdoclet.tag.docbook.RefPurpose;
import org.dbdoclet.tag.docbook.RefSect1;
import org.dbdoclet.tag.docbook.RefSect2;
import org.dbdoclet.tag.docbook.RefSect3;
import org.dbdoclet.tag.docbook.RefSect4;
import org.dbdoclet.tag.docbook.RefSect5;
import org.dbdoclet.tag.docbook.Reference;
import org.dbdoclet.tag.docbook.Sect1;
import org.dbdoclet.tag.docbook.Sect2;
import org.dbdoclet.tag.docbook.Sect3;
import org.dbdoclet.tag.docbook.Sect4;
import org.dbdoclet.tag.docbook.Sect5;
import org.dbdoclet.tag.docbook.Section;
import org.dbdoclet.tag.docbook.Title;
import org.dbdoclet.tag.html.HeaderElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.internal.html.docbook.editor.Editor;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorInstruction;
import org.dbdoclet.trafo.internal.html.docbook.editor.HeadingEditor;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SectionDetector {

	private static Class<?>[] bookMap = { Book.class, Chapter.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] articleMap = { Article.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] partIntroMap = { PartIntro.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] chapterMap = { Chapter.class, Section.class,
			Section.class, Section.class, Section.class, Section.class };

	private static Class<?>[] sect1Map = { Sect1.class, Sect2.class,
			Sect3.class, Sect4.class, Sect5.class, Sect5.class };

	private static Class<?>[] sect2Map = { Sect2.class, Sect3.class,
			Sect4.class, Sect5.class, Sect5.class, Sect5.class };

	private static Class<?>[] sect3Map = { Sect3.class, Sect4.class,
			Sect5.class, Sect5.class, Sect5.class, Sect5.class };

	private static Class<?>[] referenceMap = { Reference.class, RefEntry.class,
			RefSect1.class, RefSect2.class, RefSect3.class, RefSect4.class };

	private static Class<?>[] refSect1Map = { RefSect1.class, RefSect2.class,
			RefSect3.class, RefSect4.class, RefSect5.class, RefSect5.class };

	private static Class<?>[] refSect2Map = { RefSect2.class, RefSect3.class,
			RefSect4.class, RefSect5.class, RefSect5.class, RefSect5.class };

	private static Class<?>[] refSect3Map = { RefSect3.class, RefSect4.class,
			RefSect5.class, RefSect5.class, RefSect5.class, RefSect5.class };

	private static Class<?>[] saveMap = { Para.class, Para.class, Para.class,
			Para.class, Para.class, Para.class };

	private Class<?>[] map;
	private EditorInstruction values;

	public boolean isSection(HtmlElement element, Script script) {
		return getSectionLevel(element, script) > 0 ? true : false;
	}

	public int getSectionLevel(HtmlElement element, Script script) {

		if (element == null) {
			return 0;
		}

		if (element instanceof HeaderElement) {
			return ((HeaderElement) element).getLevel();
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
				DbtConstants.SECTION_SECTION_DETECTION,
				DbtConstants.PARAM_ATTRIBUTE_CLASS);

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

	public void edit(EditorInstruction values) {

		this.values = values;

		DocBookTagFactory dbfactory = values.getTagFactory();
		DocBookElement sect;

		DocBookTransformer transformer = values.getTransformer();
		HtmlElement child = values.getHtmlElement();
		DocBookElement parent = values.getParent();

		if (parent != null) {
			values.setCurrent(parent);
		}

		values.doTraverse(true);

		Element levelParent = null;
		int level = 7;

		if (child instanceof HeaderElement) {
			level = ((HeaderElement) child).getLevel();
		} else {
			level = getSectionLevel(child, transformer.getScript());
		}

		Element root = initMap();
		levelParent = findParentForLevel(child, level);

		if ((levelParent == null)
				&& ((values.getCodeContext() == DocBookTransformer.DocumentElementType.OVERVIEW) || (values
						.getCodeContext() == DocBookTransformer.DocumentElementType.BOOK))) {

			sect = dbfactory.createChapter();
			sect.setRemap(child.getNodeName());

			levelParent = root;

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

					if (sect instanceof RefEntry) {

						RefNameDiv refNameDiv = dbfactory.createRefNameDiv();
						RefName refName = dbfactory.createRefName("");
						RefPurpose refPurpose = dbfactory.createRefPurpose();

						refNameDiv.appendChild(refName);
						refNameDiv.appendChild(refPurpose);

						RefSect1 refSect1 = dbfactory.createRefSect1();

						sect.appendChild(refNameDiv);
						sect.appendChild(refSect1);
						sect = refSect1;
					}

					if (sect instanceof BridgeHead) {

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

			sect = values.getCurrent();
		}

		String id = child.getId();

		if ((id != null) && (id.length() > 0)) {

			sect.setId(transformer.getLinkManager().createUniqueId(id));
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

		if (isRoot(PartIntro.getTag())) {
			map = partIntroMap;
		}

		if (isRoot(Sect1.getTag())) {
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

		if (isRoot(RefSect1.getTag())) {
			map = refSect1Map;
		}

		if (isRoot(RefSect2.getTag())) {
			map = refSect2Map;
		}

		if (isRoot(RefSect3.getTag())) {
			map = refSect3Map;
		}

		if (map == null) {
			map = saveMap;
		}

		return root;
	}

	public boolean isRoot(String tagName) {

		Node root = getDocumentElement();

		if (root instanceof DocBookFragment) {

			Node firstChild = ((DocBookFragment) root).getFirstElement();

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
			root = ((DocBookFragment) root).getFirstElement();
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
			root = ((DocBookFragment) root).getFirstElement();
		}

		if (root instanceof Book
				|| (values.getCodeContext() == DocBookTransformer.DocumentElementType.BOOK)
				|| (values.getCodeContext() == DocBookTransformer.DocumentElementType.OVERVIEW)) {
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

	public DocBookElement findParentForLevel(HtmlElement header, int level) {

		DocBookElement parent = null;
		Class<?> parentClass = null;

		Script script = values.getTransformer().getScript();

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

				int tagLevel = getSectionLevel(header, script);
				int parentLevel = getSectionLevel(
						(HtmlElement) parent.getUserData("html"), script);

				while (tagLevel != -1 && parentLevel != -1
						&& parentLevel >= tagLevel) {

					DocBookElement ancestor = (DocBookElement) NodeImpl
							.findParent(parent, parentClass);

					if (ancestor == null) {
						break;
					}

					parent = ancestor;

					parentLevel = getSectionLevel(
							(HtmlElement) parent.getUserData("html"), script);
				}

				break;
			}
		}

		return parent;
	}

	public DocBookElement createSectionChild(HtmlElement header, Element parent)
			throws OptionException {

		DocBookTagFactory dbfactory = values.getTagFactory();
		DocBookElement section = null;

		if (parent == null || parent instanceof Para
				|| isValidHeader(header) == false) {
			section = dbfactory.createBridgeHead();
		}

		if (parent instanceof Book || parent instanceof Part) {
			section = dbfactory.createChapter();
		}

		if (parent instanceof Chapter || parent instanceof PartIntro) {
			section = dbfactory.createSection();
		}

		if (parent instanceof Article) {
			section = dbfactory.createSection();
		}

		if (parent instanceof Section) {
			section = dbfactory.createSection();
		}

		if (parent instanceof Sect1) {
			section = dbfactory.createSect2();
		}

		if (parent instanceof Sect2) {
			section = dbfactory.createSect3();
		}

		if (parent instanceof Sect3) {
			section = dbfactory.createSect4();
		}

		if (parent instanceof Sect4) {
			section = dbfactory.createSect5();
		}

		if (parent instanceof Sect5) {
			section = dbfactory.createSimpleSect();
		}

		if (parent instanceof Reference) {
			section = dbfactory.createRefEntry();
		}

		if (parent instanceof RefEntry) {
			section = dbfactory.createRefSect1();
		}

		if (parent instanceof RefSect1) {
			section = dbfactory.createRefSect2();
		}

		if (parent instanceof RefSect2) {
			section = dbfactory.createRefSect3();
		}

		if (parent instanceof RefSect3) {
			section = dbfactory.createRefSect4();
		}

		if (parent instanceof RefSect4) {
			section = dbfactory.createRefSect5();
		}

		if (parent instanceof RefSect5) {
			section = dbfactory.createSimpleSect();
		}

		if (section != null) {
			Editor editor = new Editor();
			editor.setValues(values);
			editor.copyCommonAttributes(header, section);
		}

		return section;
	}

	public DocBookElement createSectionPara(Element parent)
			throws OptionException {

		DocBookElement para;

		DocBookTagFactory dbfactory = values.getTagFactory();

		if ((parent != null) && parent instanceof Para) {

			para = (DocBookElement) parent;

		} else {

			para = dbfactory.createPara();

			if (((Para) para).isValidParent(values.getParent())) {
				values.getParent().appendChild(para);
			} else {
				para = values.getParent();
			}
		}

		BridgeHead bridgeHead = dbfactory.createBridgeHead();
		bridgeHead.appendChild(values.getHtmlElement().getTextContent());
		para.appendChild(bridgeHead);

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
}
