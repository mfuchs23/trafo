package org.dbdoclet.trafo.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.html.parser.HtmlParser;
import org.dbdoclet.html.parser.ParserException;
import org.dbdoclet.html.tokenizer.TokenizerException;
import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.progress.ProgressManager;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.AttributeRule;
import org.dbdoclet.trafo.script.Namespace;
import org.dbdoclet.trafo.script.NodeRule;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.Section;
import org.dbdoclet.xiphias.XPathServices;
import org.dbdoclet.xiphias.annotation.MapToAttributeAnnotation;
import org.dbdoclet.xiphias.annotation.MapToNodeAnnotation;
import org.dbdoclet.xiphias.dom.CommentImpl;
import org.dbdoclet.xiphias.dom.DocumentFragmentImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;
import org.dbdoclet.xiphias.dom.TextImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class HtmlProvider implements IHtmlProvider {

	private static Log logger = LogFactory.getLog(HtmlProvider.class);

	public ArrayList<ProgressListener> listeners;
	private IEditorFactory editorFactory;
	private String indent = "";
	private ProgressManager pm;
	private Script script;
	private IHtmlVisitor visitor;

	public HtmlProvider(Script script) {
		this.script = script;
		listeners = new ArrayList<ProgressListener>();
		pm = new ProgressManager(listeners);
	}

	public boolean isFragment(String htmlCode) throws IOException,
			TokenizerException {

		HtmlParser parser = new HtmlParser();
		return parser.isFragment(htmlCode);
	}

	@Override
	public HtmlDocument parseDocument(String htmlCode) throws IOException,
			ParserException, TokenizerException {

		HtmlParser parser = new HtmlParser();

		if (listeners != null && listeners.size() > 0) {
			parser.addProgressListener(listeners.get(0));
		}

		HtmlDocument htmlDocument = parser.parseDocument(htmlCode);
		ElementImpl documentElement = (ElementImpl) htmlDocument
				.getDocumentElement();
		documentElement.removeAttribute("xmlns");
		parseAfter(documentElement);
		return htmlDocument;
	}

	@Override
	public HtmlFragment parseFragment(String htmlCode) throws IOException,
			ParserException, TokenizerException {

		HtmlParser parser = new HtmlParser();
		if (listeners != null && listeners.size() > 0) {
			parser.addProgressListener(listeners.get(0));
		}

		HtmlFragment htmlFragment = parser.parseFragment(htmlCode);
		parseAfter(htmlFragment);

		return htmlFragment;
	}

	@Override
	public void setProgressListeners(ArrayList<ProgressListener> listeners) {
		this.listeners = listeners;
	}

	@Override
	public Document traverse(HtmlDocument htmlDocument, IHtmlVisitor visitor)
			throws Exception {

		this.visitor = visitor;

		if (htmlDocument == null) {
			return null;
		}

		editorFactory = visitor.getEditorFactory();
		Document doc = visitor.createDocument(htmlDocument);
		edit((ElementImpl) htmlDocument.getDocumentElement(),
				(ElementImpl) doc.getDocumentElement());

		return doc;
	}

	@Override
	public DocumentFragment traverse(HtmlFragment htmlFragment,
			IHtmlVisitor visitor) {
		this.visitor = visitor;

		if (htmlFragment == null) {
			return null;
		}

		editorFactory = visitor.getEditorFactory();
		DocumentFragmentImpl fragment = visitor
				.createDocumentFragment(htmlFragment);
		edit((ElementImpl) htmlFragment, fragment);

		return fragment;
	}

	private void afterEdit(EditorInstruction values) {
	}

	private boolean beforeEdit(EditorInstruction values) {
		return visitor.beforeEdit(values);
	}

	private NodeImpl edit(NodeImpl htmlNode, NodeImpl targetNode) {

		logger.debug("-> edit " + htmlNode);

		if (htmlNode == null) {
			logger.error("[DocBookDoclet.edit] - Parameter node is null!");
			return null;
		}

		if (targetNode == null) {
			logger.error("[DocBookDoclet.edit] - Parameter dbParent is null!");
			return null;
		}

		NodeListImpl htmlChildren = htmlNode.getTrafoChildNodes();
		Iterator<NodeImpl> iterator = htmlChildren.iterator();

		boolean doTraverse = true;
		boolean doIgnore = false;

		NodeImpl child = null;

		NodeImpl oldParent = targetNode;

		logger.debug(indent
				+ "\n>>>==================================================");
		logger.debug(indent + " HTML Vaterelement " + htmlNode + ".");
		logger.debug(indent + " DocBook Vaterelement " + targetNode + ".");

		indent += ".";

		while (iterator.hasNext()) {

			child = iterator.next();

			pm.fireProgressEvent(new ProgressEvent("Transforming "
					+ child.toString()));

			logger.debug(indent + " HTML element is " + child + ".");

			NodeImpl element = targetNode;
			doTraverse = true;
			EditorInstruction editorInstruction = null;

			if (child instanceof CommentImpl) {
				editorInstruction = editComment(child, targetNode);
			}

			if (child instanceof TextImpl) {
				editorInstruction = editText(child, targetNode);
			}

			if (child instanceof HtmlElement) {
				editorInstruction = editElement(child, targetNode);
			}

			if (editorInstruction == null) {
				continue;
			}

			element = editorInstruction.getCurrent();
			doTraverse = editorInstruction.doTraverse();
			doIgnore = editorInstruction.doIgnore();
			targetNode = editorInstruction.getParent();

			if (targetNode == null) {

				throw new NullPointerException("[Node]"
						+ " DocBook parent element for element " + child
						+ " is null!");
			}

			
			if (doTraverse == true) {

				NodeImpl childParent = edit(child, element);

				logger.debug(indent
						+ "\n<<<==================================================");

				if (doIgnore == true) {
					targetNode = childParent;
				}

			} else {

				int num = HtmlParser.computeSize(child);

				for (int i = 0; i < num; i++) {
					pm.fireProgressEvent(new ProgressEvent("Transforming node "
							+ htmlNode.getNodeName()));
				}

				// System.out.println("doTraverse == false " + num + "  ");
			}

			logger.debug(indent + "[Teilbaum bearbeitet] HTML: " + child
					+ ", DocBook: " + element + ", Vater: " + targetNode);

			if (targetNode != oldParent) {

				logger.debug(indent + "Parent changed. Old parent was "
						+ oldParent + ". New parent is " + targetNode + ".");

				NodeImpl dbelem = oldParent;
				logger.debug(indent + "Closing old parent " + dbelem
						+ ". HTML Element is " + child + ".");

				oldParent = targetNode;
			}

		}

		if (indent.length() > 2) {

			indent = indent.substring(0, indent.length() - 2);
		}

		logger.debug(indent + "[Vaterknoten bearbeitet] HTML: " + child
				+ ", Vaterknoten: " + targetNode);

		logger.debug("<- edit ");
		return targetNode;
	}

	private EditorInstruction editComment(NodeImpl child, NodeImpl targetNode) {

		try {

			CommentImpl comment = (CommentImpl) child;

			if (isInstruction(comment)) {

				EditorInstruction values = new EditorInstruction(
						visitor.getScript());
				values.setHtmlElement(null);
				values.setCurrent(targetNode);
				values.setParent(targetNode);
				values.setCharacterDataNode((CommentImpl) child);

				IEditor editor = editorFactory.getCommentEditor();

				logger.debug(indent + " Vor der Kommentarbearbeitung: " + child
						+ ".\n");

				values = editor.edit(values);

				logger.debug(indent + " Nach der Kommentarbearbeitung: "
						+ child + ".\n");

				targetNode = values.getParent();

				if (targetNode == null) {
					throw new NullPointerException("[Node]"
							+ "DocBook parent element for element '" + child
							+ "' is null!");
				}

				return values;

			} else {

				pm.fireProgressEvent(new ProgressEvent("Comment"));
				targetNode.appendChild(child);
				return null;
			}

		} catch (EditorException oops) {
			logger.debug(indent + " EditorException " + oops.getMessage());
		}

		return null;
	}

	private EditorInstruction editElement(NodeImpl child, NodeImpl targetNode) {

		try {

			HtmlElement htmlElement = (HtmlElement) child;

			IEditor editor = editorFactory.getChildEditor(htmlElement);

			logger.debug("Setting editor values.");

			EditorInstruction values = new EditorInstruction(
					visitor.getScript());

			values.setHtmlElement((HtmlElement) child);
			values.setCurrent(targetNode);
			values.setParent(targetNode);
			values.setCharacterDataNode(null);

			logger.debug(indent + " Vor der Transformation: " + child + ".\n"
					+ "Editor " + editor + "\n" + values);

			MapToNodeAnnotation mapToAnnotation = child
					.getAnnotation(MapToNodeAnnotation.class);

			if (mapToAnnotation != null) {

				NodeImpl parent = values.getParent();
				String mapTo = mapToAnnotation.getMapTo();
				Element mapToElement = editor.getTagFactory().createElement(
						mapTo);
				parent.appendChild(mapToElement);
				values.setCurrent((ElementImpl) mapToElement);

			} else {

				if (beforeEdit(values)) {
					values = editor.edit(values);
					afterEdit(values);
				}
			}

			NodeImpl current = values.getCurrent();
			List<MapToAttributeAnnotation> mapToAttributeList = child
					.getAnnotations(MapToAttributeAnnotation.class);
			
			mapToAttributeList.stream().forEach(
					annotation -> {
						String attrValue = htmlElement.getAttribute(annotation
								.getAttribute());
						if (attrValue != null && current != null && current instanceof Element) {
							((Element) current).setAttribute(annotation.getMapTo(), attrValue);
						}
					});

			logger.debug(indent + " Nach der Transformation: " + child + ".\n"
					+ "Editor " + editor + "\n" + values);

			return values;

		} catch (EditorFactoryException oops) {

			logger.fatal("EditorFactoryException", oops);

		} catch (EditorException oops) {

			logger.fatal("EditorException", oops);
		}

		return null;
	}

	private EditorInstruction editText(NodeImpl child, NodeImpl targetNode) {

		logger.debug("Text='" + ((Text) child).toString() + "'");

		try {

			EditorInstruction values = new EditorInstruction(
					visitor.getScript());

			values.setHtmlElement(null);
			values.setCurrent(targetNode);
			values.setParent(targetNode);
			values.setCharacterDataNode((TextImpl) child);

			IEditor editor = editorFactory.getTextEditor();

			logger.debug(indent + " Vor der Textbearbeitung: " + child + ".\n");
			values = editor.edit(values);

			logger.debug(indent + " Nach der Textbearbeitung: " + child + ".\n");

			targetNode = values.getParent();

			if (targetNode == null) {

				throw new NullPointerException("[Node] "
						+ "DocBook parent element for element '" + child
						+ "' is null!");
			}

			return values;

		} catch (EditorException oops) {

			logger.fatal("EditorException", oops);
		}

		return null;
	}

	private boolean isInstruction(CommentImpl comment) {

		if (comment == null) {
			return false;
		}

		String text = comment.getData();

		if (text == null) {
			return false;
		}

		text = text.trim();

		text = StringServices.cutPrefix(text, "<!--");
		text = StringServices.cutSuffix(text, "-->");

		text = text.trim();

		if (text.startsWith("[:dbdoclet:]")) {
			return true;
		}

		return false;
	}

	private void parseAfter(NodeImpl contextNode) {

		Namespace namespace = script.getNamespace();
		Section section = namespace.findSection(TrafoConstants.SECTION_HTML);

		if (section != null) {

			TextParam excludeParam = section
					.findTextParameter(TrafoConstants.PARAM_EXCLUDE);

			if (excludeParam != null) {

				for (String excludeXpath : excludeParam.getValues()) {

					ArrayList<Node> nodeList = XPathServices.getNodes(
								contextNode, excludeXpath);

					for (Node node : nodeList) {
						if (node.getParentNode() != null) {
							node.getParentNode().removeChild(node);
						}
					}
				}
			}
		}

		for (NodeRule nodeRule : namespace.getNodeRules()) {

			String xpath = nodeRule.getName();
			ArrayList<Node> nodes = XPathServices.getNodes(contextNode, xpath);

			for (Node node : nodes) {
				if (node instanceof HtmlElement) {
					HtmlElement htmlElement = (HtmlElement) node;
					TextParam paramMapTo = nodeRule.findTextParameter("map-to");

					MapToNodeAnnotation annotation = new MapToNodeAnnotation();
					annotation.setMapTo(paramMapTo.getValue());
					htmlElement.addAnnotation(annotation);
				}
			}
		}

		for (AttributeRule attributeRule : namespace.getAttributeRules()) {

			String xpath = attributeRule.getName();
			ArrayList<Node> nodes = XPathServices.getNodes(contextNode, xpath);

			for (Node node : nodes) {
				if (node instanceof Attr) {

					Attr attribute = (Attr) node;
					HtmlElement htmlElement = (HtmlElement) attribute
							.getOwnerElement();
					TextParam paramMapTo = attributeRule
							.findTextParameter("map-to");
					MapToAttributeAnnotation annotation = new MapToAttributeAnnotation();
					annotation.setMapTo(paramMapTo.getValue());
					annotation.setAttribute(attribute.getName());
					htmlElement.addAnnotation(annotation);
				}
			}
		}
	}
}
