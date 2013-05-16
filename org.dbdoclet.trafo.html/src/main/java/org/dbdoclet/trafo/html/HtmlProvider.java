package org.dbdoclet.trafo.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.Sfv;
import org.dbdoclet.html.parser.HtmlParser;
import org.dbdoclet.html.parser.ParserException;
import org.dbdoclet.html.tokenizer.TokenizerException;
import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.progress.ProgressManager;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.xiphias.dom.CommentImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;
import org.dbdoclet.xiphias.dom.TextImpl;
import org.dbdoclet.xiphias.dom.TransformInstruction;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class HtmlProvider implements IHtmlProvider {

	private static Log logger = LogFactory.getLog(HtmlProvider.class);
	
	private String indent = "";
	private ProgressManager pm;
	public ArrayList<ProgressListener> listeners;
	private HtmlDocument htmlDocument;
	private IEditorFactory editorFactory;

	private IHtmlVisitor visitor;

	public HtmlProvider() {
		listeners = new ArrayList<ProgressListener>();
		pm = new ProgressManager(listeners);
	}
	
	@Override
	public void traverse(IHtmlVisitor visitor) throws Exception {
		
		this.visitor = visitor;
		
		if (htmlDocument == null) {
			return;
		}
		
		editorFactory = visitor.getEditorFactory();
		edit(htmlDocument.getDocumentElement(), visitor.getDocumentElement());
	}

	@Override
	public void parse(InputStream in, String encoding) throws IOException,
			ParserException, TokenizerException {

		HtmlParser parser = new HtmlParser();

		String htmlCode = retrieveHtmlCode(in, encoding);
		htmlDocument = parser.parseDocument(htmlCode);
	}

	private String retrieveHtmlCode(InputStream in, String encoding)
			throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				encoding));

		StringWriter buffer = new StringWriter();
		String line = reader.readLine();

		while (line != null) {
			buffer.append(line);
			buffer.append(Sfv.LSEP);
			line = reader.readLine();
		}

		reader.close();

		return buffer.toString();
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

	private ElementImpl edit(NodeImpl htmlNode, ElementImpl targetNode) {

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
		HtmlElement htmlElement;

		ElementImpl dbElement;
		ElementImpl dbOldParent = targetNode;
		ElementImpl dbChildParent = null;

		Object anything = null;
		IEditor editor;

		ElementImpl dbelem; // for temporay use only!

		int index = 0;

		logger.debug(indent
				+ "\n>>>==================================================");
		logger.debug(indent + " HTML Vaterelement " + htmlNode + ".");
		logger.debug(indent + " DocBook Vaterelement " + targetNode + ".");

		indent += ".";

		while (iterator.hasNext()) {

			index++;

			child = iterator.next();

			pm.fireProgressEvent(new ProgressEvent("Transforming "
					+ child.toString()));

			logger.debug(indent + " HTML element is " + child + ".");

			dbElement = targetNode;
			doTraverse = true;

			if (child instanceof CommentImpl) {

				try {
					
					CommentImpl comment = (CommentImpl) child;

					if (isInstruction(comment)) {

						EditorInstruction values = new EditorInstruction();
						values.setAnything(null);
						values.setChild(null);
						values.setCurrent(targetNode);
						values.setParent(targetNode);
						values.setCharacterDataNode((CommentImpl) child);

						editor = editorFactory.getCommentEditor();

						logger.debug(indent + " Vor der Kommentarbearbeitung: "
								+ child + ".\n");

						values = editor.edit(values);

						logger.debug(indent
								+ " Nach der Kommentarbearbeitung: " + child
								+ ".\n");
						targetNode = values.getParent();

						if (targetNode == null) {
							throw new NullPointerException("[Node #" + index
									+ "]"
									+ "DocBook parent element for element '"
									+ child + "' is null!");
						}

						dbElement = values.getCurrent();
						doTraverse = values.doTraverse();

					} else {

						pm.fireProgressEvent(new ProgressEvent("Comment"));
						targetNode.appendChild(child);
						continue;
					}

				} catch (EditorException oops) {
					logger.debug(indent + " EditorException "
							+ oops.getMessage());
				}
			}

			if (child instanceof TextImpl) {

				logger.debug("Text='" + ((Text) child).toString() + "'");

				try {

					EditorInstruction values = new EditorInstruction();

					values.setAnything(null);
					values.setChild(null);
					values.setCurrent(targetNode);
					values.setParent(targetNode);
					values.setCharacterDataNode((TextImpl) child);

					editor = editorFactory.getTextEditor();

					logger.debug(indent + " Vor der Textbearbeitung: " + child
							+ ".\n");
					values = editor.edit(values);
					logger.debug(indent + " Nach der Textbearbeitung: " + child
							+ ".\n");

					targetNode = values.getParent();

					if (targetNode == null) {

						throw new NullPointerException("[Node #" + index + "]"
								+ "DocBook parent element for element '"
								+ child + "' is null!");
					}

					dbElement = values.getCurrent();
					doTraverse = values.doTraverse();

				} catch (EditorException oops) {

					logger.debug(indent + "EditorException "
							+ oops.getMessage());
				}
			}

			if (child instanceof HtmlElement) {

				try {

					htmlElement = (HtmlElement) child;

//					if (!checkCodeContext(htmlElement)) {
//
//						logger.warn(indent
//								+ " Element is not allowed in this code context("
//								+ documentType + ") '" + htmlElement + "'!");
//
//						continue;
//					}

					editor = editorFactory.getChildEditor(htmlElement);

					logger.debug("Setting editor values.");

					EditorInstruction values = new EditorInstruction();

					values.setAnything(anything);
					values.setChild((HtmlElement) child);
					values.setCurrent(targetNode);
					values.setParent(targetNode);
					values.setCharacterDataNode(null);

					logger.debug(indent + " Vor der Transformation: " + child
							+ ".\n" + "Editor " + editor + "\n" + values);

					if (child.getTransformInstruction() != null) {

						TransformInstruction transformInstruction = child
								.getTransformInstruction();

						ElementImpl parent = values.getParent();
						parent.appendChild(transformInstruction
								.getReplacement());

						Node replacement = transformInstruction
								.getReplacement();
						Node newParent = transformInstruction.getNewParent();

						if (replacement != null
								&& replacement instanceof ElementImpl) {
							values.setCurrent((ElementImpl) replacement);
						}

						if (newParent != null
								&& newParent instanceof ElementImpl) {
							values.setParent((ElementImpl) newParent);
						}

					} else {

						if (beforeEdit(values)) {
							values = editor.edit(values);
							afterEdit(values);
						}
					}

					dbElement = values.getCurrent();
					// editor.copyCommonAttributes((HtmlElement) child,
					// dbElement);

					logger.debug(indent + " Nach der Transformation: " + child
							+ ".\n" + "Editor " + editor + "\n" + values);

					doTraverse = values.doTraverse();
					doIgnore = values.doIgnore();

					targetNode = values.getParent();

					if (targetNode == null) {

						throw new NullPointerException("[Node #" + index + "]"
								+ "DocBook parent element for element " + child
								+ " is null!");
					}

					anything = values.getAnything();

				} catch (EditorFactoryException oops) {

					logger.debug("EditorFactoryException " + oops.getMessage());

				} catch (EditorException oops) {

					logger.debug("EditorException " + oops.getMessage());
				}
			}

			if (doTraverse == true) {

				dbChildParent = edit(child, dbElement);

				logger.debug(indent
						+ "\n<<<==================================================");

				if (doIgnore == true) {
					targetNode = dbChildParent;
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
					+ ", DocBook: " + dbElement + ", Vater: " + targetNode);

			if (targetNode != dbOldParent) {

				logger.debug(indent + "Parent changed. Old parent was "
						+ dbOldParent + ". New parent is " + targetNode + ".");

				dbelem = dbOldParent;
				logger.debug(indent + "Closing old parent " + dbelem
						+ ". HTML Element is " + child + ".");

				dbOldParent = targetNode;
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

	private void afterEdit(EditorInstruction values) {
	}

	private boolean beforeEdit(EditorInstruction values) {
		return visitor.beforeEdit(values);
	}
}

