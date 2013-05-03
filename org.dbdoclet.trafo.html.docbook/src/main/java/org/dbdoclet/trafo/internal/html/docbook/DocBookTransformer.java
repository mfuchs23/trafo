/*
 * $Id$
 *
 * ### Copybright (C) 2006 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.CanceledException;
import org.dbdoclet.Sfv;
import org.dbdoclet.html.parser.HtmlParser;
import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.progress.ProgressManager;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.Abstract;
import org.dbdoclet.tag.docbook.DocBookDocument;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookFragment;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Info;
import org.dbdoclet.tag.docbook.Section;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.html.docbook.DocumentElementType;
import org.dbdoclet.trafo.internal.html.docbook.editor.Editor;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorException;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorFactory;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorFactoryException;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorInstruction;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.NodeSerializer;
import org.dbdoclet.xiphias.XPathServices;
import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.dom.CommentImpl;
import org.dbdoclet.xiphias.dom.NodeCountVisitor;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.NodeListImpl;
import org.dbdoclet.xiphias.dom.TextImpl;
import org.dbdoclet.xiphias.dom.TransformInstruction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class DocBookTransformer {

	private static Log logger = LogFactory.getLog(DocBookTransformer.class);

	public final ArrayList<ProgressListener> listeners = new ArrayList<ProgressListener>();
	private DocBookTagFactory dbfactory;

	private DocumentElementType documentType = DocumentElementType.ARTICLE;

	/**
	 * Einrückung für Debugging-Ausgaben
	 */
	private String indent = "";
	private final LinkManager linkManager;
	private ListDetector listDetector;
	private final ProgressManager pm;
	private Script script;

	public DocBookTransformer() {

		linkManager = new LinkManager();
		pm = new ProgressManager(listeners);
	}

	public void addProgressListener(ProgressListener listener) {

		if (listener == null) {
			throw new IllegalArgumentException("Parameter listener is null!");
		}

		listeners.add(listener);
	}

	public LinkManager getLinkManager() {
		return linkManager;
	}

	public Script getScript() {
		return script;
	}

	public DocBookTagFactory getTagFactory() {

		if (dbfactory == null) {
			dbfactory = new DocBookTagFactory();
		}

		return dbfactory;
	}

	public void setScript(Script script) {

		this.script = script;

		if (script != null) {

			String value = script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK,
					DbtConstants.PARAM_DOCUMENT_ELEMENT, "article");

			documentType = DocumentElementType.valueOf(value.toUpperCase());
		}
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {

		if (dbfactory == null) {
			throw new IllegalArgumentException(
					"The argument dbfactory must not be null!");
		}

		this.dbfactory = dbfactory;
	}

	public void transform(ArrayList<File> inList) throws TrafoException {

		if (inList == null) {
			throw new IllegalArgumentException(
					"The argument inList must not be null!");
		}

		for (File file : inList) {
			transform(file);
		}
	}

	public String transform(File file) throws TrafoException {

		if (file == null) {
			return null;
		}

		String htmlCode = null;

		try {
			htmlCode = FileServices.readToString(file, script.getTextParameter(
					DbtConstants.SECTION_HTML,
					DbtConstants.PARAM_HTML_SOURCE_ENCODING,
					DbtConstants.DEFAULT_SOURCE_ENCODING));
			return transform(htmlCode);
		} catch (IOException oops) {
			throw new TrafoException(oops);
		}
	}

	public String transform(File inFile, File destinationFile)
			throws TrafoException {

		try {

			Script script = createDefaultScript();
			String buffer = transform(inFile);
			FileServices.createParentDir(destinationFile);
			FileServices.writeFromString(destinationFile, buffer, script
					.getTextParameter(DbtConstants.SECTION_DOCBOOK,
							DbtConstants.PARAM_ENCODING, "UTF-8"));
			return buffer;

		} catch (IOException oops) {
			throw new TrafoException(oops);
		}
	}

	public void transform(InputStream in, OutputStream out) throws TrafoException {

		try {

			String encoding = script.getTextParameter(
					DbtConstants.SECTION_HTML,
					DbtConstants.PARAM_HTML_SOURCE_ENCODING, "UTF-8");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, encoding));

			StringWriter buffer = new StringWriter();
			String line = reader.readLine();

			while (line != null) {
				buffer.append(line);
				buffer.append(Sfv.LSEP);
				line = reader.readLine();
			}

			reader.close();

			String docBookCode = transform(buffer.toString());

			encoding = script.getTextParameter(DbtConstants.SECTION_DOCBOOK,
					DbtConstants.PARAM_ENCODING, "UTF-8");

			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,
					encoding));

			BufferedReader docBookReader = new BufferedReader(new StringReader(
					docBookCode));
			line = docBookReader.readLine();
			while (line != null) {
				writer.println(line);
				line = docBookReader.readLine();
			}

			docBookReader.close();
			writer.close();

		} catch (IOException oops) {

			throw new TrafoException(oops);
		}
	}

	public NodeImpl transform(NodeImpl htmlRoot, NodeImpl parent,
			TrafoResult result) throws Exception {

		logger.debug("-> transform(NodeImpl, NodeImpl, TrafoResult) "
				+ htmlRoot);

		if (htmlRoot == null) {
			throw new IllegalArgumentException("The htmlRoot must not be null!");
		}

		if (parent == null) {
			throw new IllegalArgumentException(
					"The argument parent must not be null!");
		}

		TextParam excludeParam = (TextParam) script.getParameter(
				DbtConstants.SECTION_HTML, DbtConstants.PARAM_EXCLUDE);

		if (excludeParam != null) {

			for (String excludeXpath : excludeParam.getValues()) {

				ArrayList<Node> nodeList = XPathServices.getNodes(htmlRoot,
						excludeXpath);

				for (Node node : nodeList) {
					if (node.getParentNode() != null) {
						node.getParentNode().removeChild(node);
					}
				}
			}
		}

		pm.nextStage();
		NodeCountVisitor nodeCounter = new NodeCountVisitor(listeners);
		htmlRoot.traverse(nodeCounter);
		pm.setProgressMaximum(nodeCounter.getNumberOfNodes());
		pm.fireProgressEvent(new ProgressEvent("Preprocess HTML tree...", false));
		PreprocessStage1 preprocessStage1 = new PreprocessStage1(listeners);
		htmlRoot.traverse(preprocessStage1);
		preprocessStage1.finish();

		NodeImpl root = null;

		pm.nextStage();
		pm.fireProgressEvent(new ProgressEvent("Transformation...", false));
		pm.setProgressMaximum(nodeCounter.getNumberOfNodes());

		listDetector = new ListDetector();

		if (parent instanceof DocBookElement) {

			root = edit(htmlRoot, (DocBookElement) parent);
		}

		if (parent instanceof DocBookFragment) {

			Section section = new DocBookTagFactory().createSection();
			parent.appendChild(section);
			root = edit(htmlRoot, section);
		}

		// System.out.println("XML Tree =\n" + parent.treeView());

		while (root.getParentNode() != null) {
			root = root.getTrafoParentNode();
		}

		// logger.debug("Edit - DocBook XML Tree =\n" + root.treeView());
		pm.nextStage();
		nodeCounter = new NodeCountVisitor(listeners);
		pm.fireProgressEvent(new ProgressEvent("Postprocess stage 1...", false));
		root.traverse(nodeCounter);
		pm.setProgressMaximum(nodeCounter.getNumberOfNodes());
		PostprocessStage1 postprocessStage1 = new PostprocessStage1(dbfactory,
				script, listeners);
		root.traverse(postprocessStage1);
		postprocessStage1.finish();

		pm.nextStage();
		pm.fireProgressEvent(new ProgressEvent("Postprocess stage 2...", false));
		root.traverse(nodeCounter.reset());
		pm.setProgressMaximum(nodeCounter.getNumberOfNodes());
		PostprocessStage2 postprocessStage2 = new PostprocessStage2(dbfactory,
				script, listeners);
		root.traverse(postprocessStage2);
		postprocessStage2.finish();

		new PostprocessStage3(dbfactory, postprocessStage1.getSubtables())
				.process();

		boolean addIndex = script.isParameterOn(DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_ADD_INDEX, false);

		if (addIndex == true) {
			root.appendChild(dbfactory.createIndex());
		}

		return root;
	}

	public String transform(String htmlCode) throws TrafoException {

		String buffer;

		try {

			buffer = transformDocument(htmlCode, documentType,
					new TrafoResult());

		} catch (TrafoException oops) {

			logger.fatal(
					"Failed to convert as document. I will try to convert as fragment.",
					oops);

			Throwable cause = oops;

			while (cause.getCause() != null) {
				cause = cause.getCause();
			}

			if (cause != null && cause instanceof CanceledException == false) {
				buffer = transformFragment(htmlCode, new TrafoResult());
			} else {
				throw oops;
			}
		}

		return buffer;
	}

	public String transform(String in, DocBookElement parent, TrafoResult result)
			throws TrafoException {

		if (in == null) {
			throw new IllegalArgumentException(
					"The argument in must not be null!");
		}

		if (result == null) {
			throw new IllegalArgumentException(
					"The argument result must not be null!");
		}

		String xml = "";

		try {

			DocBookElement elem = (DocBookElement) transform(in, parent);

			xml = NodeSerializer.toXML(elem);
			result.append(xml);

			if (xml == null || xml.length() == 0) {
				throw new TrafoException("Transformation returned null!");
			}

			logger.debug("Transformer out=" + xml);

			String destinationEncoding = script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK, DbtConstants.PARAM_ENCODING,
					DbtConstants.DEFAULT_DESTINATION_ENCODING);
			byte[] data = XmlServices.validate(xml, destinationEncoding);
			result.setData(data);

			File file = result.getFile();

			if (file != null) {

				FileOutputStream writer = new FileOutputStream(file);
				writer.write(data);
				writer.close();
			}

			return xml;

		} catch (Exception oops) {

			throw new TrafoException(oops);
		}
	}

	public NodeImpl transform(String buffer, NodeImpl parent) throws Exception {

		return transform(buffer, parent, null);
	}

	public NodeImpl transform(String buffer, NodeImpl parent, HtmlElement skipTo)
			throws Exception {
		return transform(buffer, parent, skipTo, null);
	}

	public NodeImpl transform(String buffer, NodeImpl parent,
			HtmlElement skipTo, TrafoResult result) throws Exception {

		logger.debug("-> transform(String, NodeImpl, HtmlElement, TrafoResult)");

		HtmlParser parser = new HtmlParser();

		if (documentType == DocumentElementType.CHAPTER
				|| documentType == DocumentElementType.SECTION
				|| documentType == DocumentElementType.PARAGRAPH
				|| documentType == DocumentElementType.OVERVIEW) {
			parser.setCodeContext(HtmlParser.CONTEXT_BODY);
		}

		HtmlFragment htmlRoot = null;

		if (skipTo != null) {
			htmlRoot = parser.parseFragment(buffer, skipTo.getNodeName());
		} else {
			htmlRoot = parser.parseFragment(buffer);
		}

		// logger.debug(((HtmlFragment) htmlRoot).toXML());
		// logger.debug("HTML Tree =\n" + ((TrafoNode) htmlRoot).treeView());

		if (htmlRoot == null) {
			return null;
		}

		NodeImpl rc = transform(htmlRoot, parent, result);
		logger.debug("<- transform(String, NodeImpl, HtmlElement, TrafoResult)");
		return rc;
	}

	public String transformDocument(String htmlCode,
			DocumentElementType documentType, TrafoResult result)
			throws TrafoException {

		if (script == null) {
			script = new Script();
		}

		try {

			pm.setStageCount(8);
			pm.nextStage();

			HtmlParser parser = new HtmlParser();

			for (ProgressListener listener : listeners) {
				parser.addProgressListener(listener);
			}

			HtmlDocument htmlDoc = parser.parseDocument(htmlCode);

			if (logger.isTraceEnabled()) {
				NodeSerializer serializer = new NodeSerializer();
				serializer.write(htmlDoc, new File("__trace__herold.html"));
			}

			HtmlElement documentElement = htmlDoc.getDocumentElement();

			String lang = null;

			if (documentElement == null) {
				throw new TrafoException("Couldn't find a document element!");
			}

			documentElement.removeAttribute("xmlns");

			String language = script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK, DbtConstants.PARAM_LANGUAGE,
					null);

			logger.debug("Profile: Parameter language = " + language);

			if (language == null) {

				lang = detectLanguage(documentElement);
				logger.debug("Detected language from HTML = " + lang);

				if (lang == null) {
					lang = Locale.getDefault().getLanguage().toLowerCase();
					logger.debug("Using default language from JVM = " + lang);
				}

			} else {
				lang = language;
			}

			DocBookDocument doc = new DocBookDocument();
			doc.setXmlEncoding(script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK, DbtConstants.PARAM_ENCODING,
					DbtConstants.DEFAULT_DESTINATION_ENCODING));

			DocBookTagFactory dbf = getTagFactory();
			DocBookElement rootElement;

			this.documentType = documentType;

			if (documentType == DocumentElementType.BOOK) {
				rootElement = dbf.createBook();
			} else if (documentType == DocumentElementType.REFERENCE) {
				rootElement = dbf.createReference();
			} else if (documentType == DocumentElementType.PART) {
				rootElement = dbf.createPart();
			} else if (documentType == DocumentElementType.CHAPTER) {
				rootElement = dbf.createChapter();
			} else if (documentType == DocumentElementType.SECTION) {
				rootElement = dbf.createSection();
			} else if (documentType == DocumentElementType.PARAGRAPH) {
				rootElement = dbf.createPara();
			} else {
				rootElement = dbf.createArticle();
			}

			if (lang != null) {
				logger.debug("Setting attribute xml:lang to " + lang);
				rootElement.setAttributeNS(
						"http://www.w3.org/XML/1998/namespace", "xml:lang",
						lang);
			}

			Info info = dbf.createInfo();
			rootElement.appendChild(info);

			String title = script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK, DbtConstants.PARAM_TITLE,
					null);

			if (title != null && title.trim().length() > 0) {
				info.appendChild(dbf.createTitle(title));
			} else {
				generateTitle(htmlDoc, dbf, info);
			}

			String abstractText = script.getTextParameter(
					DbtConstants.SECTION_DOCBOOK, DbtConstants.PARAM_ABSTRACT,
					null);

			if (abstractText != null) {
				Abstract abstractElement = dbf.createAbstract();
				info.appendChild(abstractElement);
				createAbstract(doc, abstractElement, abstractText);
			}

			doc.setDocumentElement(rootElement);

			// setImagePath("./");

			transform(documentElement, rootElement, result);
			StringWriter buffer = serialize(doc);

			logger.debug("<- transformDocument");
			return buffer.toString();

		} catch (TrafoException oops) {
			throw oops;
		} catch (Exception oops) {
			throw new TrafoException(oops);
		}

	}

	public String transformFragment(String htmlCode) throws TrafoException {
		return transformFragment(htmlCode, new TrafoResult());
	}

	public String transformFragment(String htmlCode, TrafoResult result)
			throws TrafoException {

		logger.debug("-> transformFragment " + htmlCode);

		if (script == null) {
			script = new Script();
		}

		script.setEnabled(DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_ADD_INDEX, false);

		try {

			HtmlParser parser = new HtmlParser();

			for (ProgressListener listener : listeners) {
				parser.addProgressListener(listener);
			}

			HtmlFragment htmlRoot = parser.parseFragment(htmlCode);

			if (htmlRoot == null) {
				throw new TrafoException("Couldn't find a html fragment!");
			}

			logger.debug("transform");
			DocBookFragment parent = new DocBookFragment();
			NodeImpl elem = transform(htmlRoot, parent, result);
			StringWriter buffer = serialize(elem);

			logger.debug("<- transformFragment");
			return buffer.toString();

		} catch (Exception oops) {
			throw new TrafoException(oops);
		}

	}

	private void afterEdit(EditorInstruction values) {
	}

	private boolean beforeEdit(EditorInstruction values) {

		listDetector.edit(values);

		SectionDetector sectionDetector = new SectionDetector();
		HtmlElement htmlElement = values.getHtmlElement();
		if (sectionDetector.isSection(htmlElement, getScript())) {
			sectionDetector.edit(values);
			return false;
		}

		return true;
	}

	/**
	 * The method <code>checkCodeContext</code>.
	 * 
	 * @param candidate
	 *            a <code>HTMLElement</code> value
	 * @return a <code>boolean</code> value
	 */
	private boolean checkCodeContext(HtmlElement candidate) {

		if (candidate == null) {

			throw new IllegalArgumentException("Parameter candidate is null!");
		}

		if (documentType == DocumentElementType.SECTION) {

			if (candidate instanceof org.dbdoclet.tag.html.Head
					|| candidate instanceof org.dbdoclet.tag.html.Html
					|| candidate instanceof org.dbdoclet.tag.html.Frameset) {

				return false;
			}
		}

		return true;
	}

	private void createAbstract(Document doc, Abstract abstractElement,
			String abstractText) {

		if (abstractText.trim().startsWith("<")) {
			abstractText = "<abstract>" + abstractText + "</abstract>";
		} else {
			abstractText = "<abstract><para>" + abstractText
					+ "</para></abstract>";
		}

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document docAbstract = documentBuilder.parse(new InputSource(
					new StringReader(abstractText)));

			Element documentElement = docAbstract.getDocumentElement();

			documentElement = (Element) doc.importNode(documentElement, true);

			if (documentElement != null && documentElement.hasChildNodes()) {

				NodeList childList = documentElement.getChildNodes();

				for (int i = 0; i < childList.getLength(); i++) {

					Node child = childList.item(i);
					abstractElement.appendChild(child);
				}
			}

		} catch (Exception oops) {

			logger.error("Parsing abstract failed!", oops);
		}
	}

	private Script createDefaultScript() {

		Script script = new Script();
		return script;
	}

	private String detectLanguage(Element documentElement) {

		String lang = documentElement.getAttribute("xml:lang");

		if (lang == null) {
			lang = documentElement.getAttribute("lang");
		}

		if (lang == null) {

			HtmlElement elem = (HtmlElement) XPathServices.getNode(
					documentElement, "//meta[@http-equiv='Content-Language']");

			if (elem != null) {
				lang = elem.getAttribute("content");
			}
		}
		return lang;
	}

	/**
	 * The method <code>edit</code> is reponsible for the transformation of the
	 * HTML document tree into an DocBook document tree.
	 * 
	 * @param htmlNode
	 *            a <code>Node</code> value
	 * @param dbParent
	 *            a <code>DocBookElement</code> value
	 * 
	 * @return a <code>Element</code>. The root node of the created DocBook
	 *         document tree.
	 */
	private DocBookElement edit(NodeImpl htmlNode, DocBookElement dbParent) {

		logger.debug("-> edit " + htmlNode);

		if (htmlNode == null) {
			logger.error("[DocBookDoclet.edit] - Parameter node is null!");
			return null;
		}

		if (dbParent == null) {
			logger.error("[DocBookDoclet.edit] - Parameter dbParent is null!");
			return null;
		}

		NodeListImpl htmlChildren = htmlNode.getTrafoChildNodes();
		Iterator<NodeImpl> iterator = htmlChildren.iterator();

		boolean doTraverse = true;
		boolean doIgnore = false;

		NodeImpl child = null;
		HtmlElement htmlElement;

		DocBookElement dbElement;
		DocBookElement dbOldParent = dbParent;
		DocBookElement dbChildParent = null;

		Object anything = null;
		Editor editor;

		DocBookElement dbelem; // for temporay use only!

		int index = 0;

		logger.debug(indent
				+ "\n>>>==================================================");
		logger.debug(indent + " HTML Vaterelement " + htmlNode + ".");
		logger.debug(indent + " DocBook Vaterelement " + dbParent + ".");

		indent += ".";

		while (iterator.hasNext()) {

			index++;

			child = iterator.next();

			pm.fireProgressEvent(new ProgressEvent("Transforming "
					+ child.toString()));

			logger.debug(indent + " HTML element is " + child + ".");

			dbElement = dbParent;
			doTraverse = true;

			if (child instanceof CommentImpl) {

				try {
					CommentImpl comment = (CommentImpl) child;

					if (isInstruction(comment)) {

						EditorInstruction values = new EditorInstruction();
						values.setAnything(null);
						values.setChild(null);
						values.setCodeContext(documentType);
						values.setCurrent(dbParent);
						values.setParent(dbParent);
						values.setCharacterDataNode((CommentImpl) child);
						values.setTransformer(this);

						editor = EditorFactory.getCommentEditor();

						logger.debug(indent + " Vor der Kommentarbearbeitung: "
								+ child + ".\n");

						values = editor.edit(values);

						logger.debug(indent
								+ " Nach der Kommentarbearbeitung: " + child
								+ ".\n");
						dbParent = values.getParent();

						if (dbParent == null) {
							throw new NullPointerException("[Node #" + index
									+ "]"
									+ "DocBook parent element for element '"
									+ child + "' is null!");
						}

						dbElement = values.getCurrent();
						doTraverse = values.doTraverse();

					} else {

						pm.fireProgressEvent(new ProgressEvent("Comment"));
						dbParent.appendChild(child);
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
					values.setCodeContext(documentType);
					values.setCurrent(dbParent);
					values.setParent(dbParent);
					values.setCharacterDataNode((TextImpl) child);
					values.setTransformer(this);

					editor = EditorFactory.getTextEditor();

					logger.debug(indent + " Vor der Textbearbeitung: " + child
							+ ".\n");
					values = editor.edit(values);
					logger.debug(indent + " Nach der Textbearbeitung: " + child
							+ ".\n");

					dbParent = values.getParent();

					if (dbParent == null) {

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

					if (!checkCodeContext(htmlElement)) {

						logger.warn(indent
								+ " Element is not allowed in this code context("
								+ documentType + ") '" + htmlElement + "'!");

						continue;
					}

					editor = EditorFactory.getChildEditor(htmlElement);

					logger.debug("Setting editor values.");

					EditorInstruction values = new EditorInstruction();

					values.setAnything(anything);
					values.setChild((HtmlElement) child);
					values.setCodeContext(documentType);
					values.setCurrent(dbParent);
					values.setParent(dbParent);
					values.setCharacterDataNode(null);
					values.setTransformer(this);

					logger.debug(indent + " Vor der Transformation: " + child
							+ ".\n" + "Editor " + editor + "\n" + values);

					if (child.getTransformInstruction() != null) {

						TransformInstruction transformInstruction = child
								.getTransformInstruction();

						DocBookElement parent = values.getParent();
						parent.appendChild(transformInstruction
								.getReplacement());

						Node replacement = transformInstruction
								.getReplacement();
						Node newParent = transformInstruction.getNewParent();

						if (replacement != null
								&& replacement instanceof DocBookElement) {
							values.setCurrent((DocBookElement) replacement);
						}

						if (newParent != null
								&& newParent instanceof DocBookElement) {
							values.setParent((DocBookElement) newParent);
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

					dbParent = values.getParent();

					if (dbParent == null) {

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
					dbParent = dbChildParent;
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
					+ ", DocBook: " + dbElement + ", Vater: " + dbParent);
			dbElement.closed();

			if (dbParent != dbOldParent) {

				logger.debug(indent + "Parent changed. Old parent was "
						+ dbOldParent + ". New parent is " + dbParent + ".");

				dbelem = dbOldParent;
				logger.debug(indent + "Closing old parent " + dbelem
						+ ". HTML Element is " + child + ".");
				dbelem.closed();
				dbelem.isNew(false);

				dbOldParent = dbParent;
			}

		}

		if (indent.length() > 2) {

			indent = indent.substring(0, indent.length() - 2);
		}

		logger.debug(indent + "[Vaterknoten bearbeitet] HTML: " + child
				+ ", Vaterknoten: " + dbParent);
		dbParent.closed();
		dbParent.isNew(false);

		logger.debug("<- edit ");
		return dbParent;
	}

	private void generateTitle(HtmlDocument htmlDoc, DocBookTagFactory dbf,
			Info info) {

		HtmlElement elem = (HtmlElement) XPathServices.getNode(htmlDoc,
				"/html/head/title");

		if (elem == null) {

			for (int i = 1; i <= 6; i++) {

				if (elem == null) {
					elem = (HtmlElement) XPathServices.getNode(htmlDoc,
							"/html/body/h" + i + "[1]");
				}
			}
		}

		if (elem != null) {

			info.appendChild(dbf.createTitle(elem.getTextContent()));

		} else {

			Text text = (Text) XPathServices.getNode(htmlDoc, "//text()[1]");

			if (text != null) {
				info.appendChild(dbf.createTitle(text.getData()));
			} else {
				info.appendChild(dbf.createTitle("Herold"));
			}
		}
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

	private StringWriter serialize(NodeImpl elem) throws Exception {

		pm.nextStage();
		pm.fireProgressEvent(new ProgressEvent("Serializing XML...", false));
		NodeCountVisitor nodeCounter = new NodeCountVisitor(listeners);
		elem.traverse(nodeCounter.reset());
		int nodeCount = nodeCounter.getNumberOfNodes();

		if (elem instanceof Document) {
			nodeCount++;
		}

		pm.setProgressMaximum(nodeCount);

		NodeSerializer serializer = new NodeSerializer();
		serializer.setProgressListeners(listeners);
		StringWriter buffer = new StringWriter();
		serializer.write(elem, buffer);
		return buffer;
	}
}
