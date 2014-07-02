package org.dbdoclet.trafo.html.dita;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dbdoclet.option.OptionException;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.dita.DitaElement;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.P;
import org.dbdoclet.tag.dita.Title;
import org.dbdoclet.tag.html.HeadingElement;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.dita.LinkManager;
import org.dbdoclet.trafo.internal.html.dita.editor.DefaultEditor;
import org.dbdoclet.trafo.internal.html.dita.editor.HeadingEditor;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.DocumentFragmentImpl;
import org.dbdoclet.xiphias.dom.DocumentImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SectionDetector {

	private EditorInstruction values;
	private Script script;
	private DocumentElementType documentElementType;
	private DitaTagFactory tagFactory;
	private LinkManager linkManager;

	public boolean isSection(HtmlElement element) {
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

	public void edit(EditorInstruction values) {

		this.values = values;

		ElementImpl sect;

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

		levelParent = findParentForLevel(child, level);

		if ((levelParent == null)
				&& ((documentElementType == DocumentElementType.OVERVIEW) || (documentElementType == DocumentElementType.BOOK))) {

			sect = tagFactory.createTopic();
			// levelParent = (NodeImpl) root;

		} else {

			sect = createSectionChild(child, levelParent);
		}

		if (sect != null) {

			if (sect instanceof P) {

				values.doTraverse(false);

				// Try to force a new paragraph after the header.
				if (parent instanceof P && (parent.getParentNode() != null)) {
					values.setParent((NodeImpl) parent.getParentNode());
				}

				values.setCurrent(values.getParent());

			} else {

				if (levelParent != null) {

					levelParent.appendChild(sect);

					Title title = tagFactory.createTitle();
					sect.appendChild(title);
					values.setParent(sect);
					values.setCurrent(title);
				}
			}

		} else {

			sect = (ElementImpl) values.getCurrent();
		}

		String id = child.getId();

		if ((id != null) && (id.length() > 0)) {
			sect.setId(linkManager.createUniqueId(id));
		}
	}

	public boolean isRoot(String tagName) {

		Node root = getDocumentElement();

		if (root instanceof DocumentFragmentImpl) {

			Node firstChild = ((DocumentFragmentImpl) root).getFirstChildElement();

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

	private Element getDocumentElement() {

		NodeImpl rootNode = values.getCurrent().getRoot();

		Element root = null;

		if (rootNode instanceof DocumentImpl) {

			DocumentImpl doc = (DocumentImpl) rootNode;
			root = doc.getDocumentElement();

		} else if (rootNode instanceof Element) {

			root = (Element) rootNode;

		} else {
			throw new IllegalStateException(
					"Root node must be of type DocumentImpl or Element."
							+ "Found root node of type "
							+ rootNode.getClass().getName() + "!");
		}

		return root;
	}

	public NodeImpl findParentForLevel(ElementImpl header, int level) {

		NodeImpl parent = null;

		if (level < 0) {
			level = 0;
		}

		parent = values.getCurrent();
		
		return parent;
	}

	public DitaElement createSectionChild(HtmlElement header,
			NodeImpl levelParent) throws OptionException {

		DitaElement section = null;

		if (levelParent == null || levelParent instanceof P
				|| isValidHeader(header) == false) {
			section = tagFactory.createSection();
		} else {
			section = tagFactory.createTopic();
		}

		if (section != null) {
			DefaultEditor editor = new DefaultEditor();
			editor.setLinkManager(linkManager);
			editor.setTagFactory(tagFactory);
			editor.setValues(values);
			editor.copyCommonAttributes(header, section);
		}

		return section;
	}

	private boolean isValidHeader(HtmlElement header) {

		boolean rc = header
				.validateParentPath(HeadingEditor.validHtmlParentPathMap);
		return rc;
	}

	public void setScript(Script script) {

		this.script = script;

		if (script != null) {

			String value = script.getTextParameter(
					TrafoConstants.SECTION_DOCBOOK,
					TrafoConstants.PARAM_DOCUMENT_ELEMENT, "article");

			documentElementType = DocumentElementType.valueOf(value
					.toUpperCase());
		}
	}

	public void setTagFactory(DitaTagFactory tagFactory) {
		this.tagFactory = tagFactory;
	}

	public void setLinkManager(LinkManager linkManager) {
		this.linkManager = linkManager;
	}
}
