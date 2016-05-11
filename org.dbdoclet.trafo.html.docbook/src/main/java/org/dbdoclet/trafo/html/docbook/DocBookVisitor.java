package org.dbdoclet.trafo.html.docbook;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.docbook.Abstract;
import org.dbdoclet.tag.docbook.DocBookFragment;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Info;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditorFactory;
import org.dbdoclet.trafo.html.IHtmlVisitor;
import org.dbdoclet.trafo.html.docbook.editor.DocBookEditorFactory;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XPathServices;
import org.dbdoclet.xiphias.XmlConstants;
import org.dbdoclet.xiphias.dom.DocumentFragmentImpl;
import org.dbdoclet.xiphias.dom.DocumentImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class DocBookVisitor implements IHtmlVisitor {

	private static Log logger = LogFactory.getLog(DocBookVisitor.class);

	private ArrayList<ProgressListener> listeners;
	private LinkManager linkManager;
	private DocBookTagFactory dbfactory;
	private Script script;
	private ListDetector listDetector;
	private DocBookEditorFactory docBookEditorFactory;

	public DocBookVisitor() {
		listDetector = new ListDetector();
		linkManager = new LinkManager();
	}

	public void addProgressListener(ProgressListener listener) {

		if (listener == null) {
			return;
		}

		if (listeners == null) {
			listeners = new ArrayList<ProgressListener>();
		}

		listeners.add(listener);
	}

	@Override
	public boolean beforeEdit(EditorInstruction values) {

		listDetector.edit(values, dbfactory);

		SectionDetector sectionDetector = new SectionDetector();
		sectionDetector.setScript(script);
		sectionDetector.setTagFactory(dbfactory);
		sectionDetector.setLinkManager(linkManager);

		HtmlElement htmlElement = values.getHtmlElement();
		if (sectionDetector.isSection(htmlElement)) {
			sectionDetector.edit(values, dbfactory);
			return false;
		}

		return true;
	}

	@Override
	public DocumentFragmentImpl createDocumentFragment(HtmlFragment htmlFragment) {

		DocumentFragmentImpl fragment = new DocBookFragment();
		ElementImpl documentElement = createDocumentElement();
		fragment.setUserData("documentElement", documentElement, null);
		return fragment;
	}

	@Override
	public DocumentImpl createDocument(HtmlDocument htmlDoc) {

		DocumentImpl document = new DocumentImpl();
		ElementImpl documentElement = createDocumentElement();

		Info info = dbfactory.createInfo();
		documentElement.appendChild(info);

		String title = script.getTextParameter(TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_TITLE, null);

		if (title != null && title.trim().length() > 0) {
			info.appendChild(dbfactory.createTitle(title));
		} else {
			generateTitle(htmlDoc, dbfactory, info);
		}

		String abstractText = script.getTextParameter(
				TrafoConstants.SECTION_DOCBOOK, TrafoConstants.PARAM_ABSTRACT,
				null);

		if (abstractText != null) {
			Abstract abstractElement = dbfactory.createAbstract();
			info.appendChild(abstractElement);
			createAbstract(document, abstractElement, abstractText);
		}

		document.setDocumentElement(documentElement);
		documentElement.setDocument(document);

		return document;
	}

	public ElementImpl createDocumentElement() {

		ElementImpl documentElement = dbfactory.createSection();
		String tagName = "article";

		if (script != null) {

			tagName = script.getTextParameter(TrafoConstants.SECTION_DOCBOOK,
					TrafoConstants.PARAM_DOCUMENT_ELEMENT, "article");
			tagName = tagName.toLowerCase();
		}

		documentElement = dbfactory.createElementByName(tagName);
		
		if (documentElement == null) {
			throw new IllegalStateException("Can't create document element " + tagName);
		}
		documentElement.setNamespaceURI(XmlConstants.NAMESPACE_DOCBOOK);
		documentElement.setAttribute("xmlns", XmlConstants.NAMESPACE_DOCBOOK);
		documentElement.setAttribute("version", "5.0");
		documentElement.setAttribute("xmlns:xl", XmlConstants.NAMESPACE_XLINK);
		documentElement.setAttribute("xmlns:xi",
				XmlConstants.NAMESPACE_XINCLUDE);

		String language = script.getTextParameter(
				TrafoConstants.SECTION_DOCBOOK, TrafoConstants.PARAM_LANGUAGE,
				null);

		logger.debug("Profile: Parameter language = " + language);

		if (language == null) {

			language = detectLanguage(documentElement);
			logger.debug("Detected language from HTML = " + language);

			if (language == null) {
				language = Locale.getDefault().getLanguage().toLowerCase();
				logger.debug("Using default language from JVM = " + language);
			}

		}

		if (language != null) {
			documentElement.setAttributeNS(XmlConstants.NAMESPACE_XML,
					"xml:lang", language);
		}

		return documentElement;
	}

	@Override
	public IEditorFactory getEditorFactory() {

		if (docBookEditorFactory == null) {
			docBookEditorFactory = new DocBookEditorFactory();
			docBookEditorFactory.setLinkManager(linkManager);
			docBookEditorFactory.setScript(script);
			docBookEditorFactory.setTagFactory(dbfactory);
		}

		return docBookEditorFactory;
	}

	@Override
	public Script getScript() {
		return script;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {
		this.dbfactory = dbfactory;
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

	private void generateTitle(HtmlDocument htmlDoc, DocBookTagFactory dbf,
			Info info) {

		ElementImpl elem = (ElementImpl) XPathServices.getNode(htmlDoc,
				"/html/head/title");

		if (elem == null) {

			for (int i = 1; i <= 6; i++) {

				if (elem == null) {
					elem = (ElementImpl) XPathServices.getNode(htmlDoc,
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

	private String detectLanguage(Element documentElement) {

		String lang = documentElement.getAttributeNS(
				XmlConstants.NAMESPACE_XML, "lang");

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

	public void addProgressListeners(ArrayList<ProgressListener> newListeners) {

		if (newListeners == null) {
			return;
		}

		if (listeners == null) {
			listeners = new ArrayList<ProgressListener>();
		}

		listeners.addAll(newListeners);
	}
}
