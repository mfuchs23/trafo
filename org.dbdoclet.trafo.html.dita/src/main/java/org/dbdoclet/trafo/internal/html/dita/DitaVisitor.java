package org.dbdoclet.trafo.internal.html.dita;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.dita.Abstract;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditorFactory;
import org.dbdoclet.trafo.html.IHtmlVisitor;
import org.dbdoclet.trafo.html.dita.ListDetector;
import org.dbdoclet.trafo.html.dita.SectionDetector;
import org.dbdoclet.trafo.internal.html.dita.editor.DitaEditorFactory;
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

public class DitaVisitor implements IHtmlVisitor {

	private static Log logger = LogFactory.getLog(DitaVisitor.class);

	private ArrayList<ProgressListener> listeners;
	private LinkManager linkManager;
	private DocBookTagFactory dbfactory = new DocBookTagFactory();
	private DitaTagFactory tagFactory;
	private Script script;
	private ListDetector listDetector;
	private DitaEditorFactory editorFactory;

	public DitaVisitor() {
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
		sectionDetector.setTagFactory(tagFactory);
		sectionDetector.setLinkManager(linkManager);

		HtmlElement htmlElement = values.getHtmlElement();
		if (sectionDetector.isSection(htmlElement)) {
			sectionDetector.edit(values);
			return false;
		}

		return true;
	}

	@Override
	public DocumentFragmentImpl createDocumentFragment(HtmlFragment htmlFragment) {
		
		DocumentFragmentImpl fragment = new DocumentFragmentImpl();
		// ElementImpl documentElement = createDocumentElement();
		// fragment.appendChild(documentElement);
		return fragment;
	}
	
	@Override
	public DocumentImpl createDocument(HtmlDocument htmlDoc) {


		ElementImpl topic = createDocumentElement();
		DocumentImpl document = new DocumentImpl();

		String title = script.getTextParameter(TrafoConstants.SECTION_DITA,
				TrafoConstants.PARAM_TITLE, null);

		if (title != null && title.trim().length() > 0) {
			topic.appendChild(tagFactory.createTitle(title));
		} else {
			generateTitle(htmlDoc, tagFactory, topic);
		}

		String abstractText = script
				.getTextParameter(TrafoConstants.SECTION_DOCBOOK,
						TrafoConstants.PARAM_ABSTRACT, null);

		if (abstractText != null) {
			Abstract abstractElement = tagFactory.createAbstract();
			topic.appendChild(abstractElement);
			createAbstract(document, abstractElement, abstractText);
		}

		document.setDocumentElement(topic);
		topic.setDocument(document);
		
		return document;
	}

	private ElementImpl createDocumentElement() {
		
		ElementImpl documentElement = tagFactory.createTopic();
		documentElement.setId("topic");

		String language = script.getTextParameter(TrafoConstants.SECTION_DITA,
				TrafoConstants.PARAM_LANGUAGE, null);

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
			documentElement.setAttributeNS(
					XmlConstants.NAMESPACE_XML, "xml:lang",
					language);
		}
		
		return documentElement;
	}

	@Override
	public IEditorFactory getEditorFactory() {

		if (editorFactory == null) {
			editorFactory = new DitaEditorFactory();
			editorFactory.setLinkManager(linkManager);
			editorFactory.setScript(script);
			editorFactory.setTagFactory(tagFactory);
		}

		return editorFactory;
	}

	@Override
	public Script getScript() {
		return script;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setTagFactory(DitaTagFactory tagFactory) {
		this.tagFactory = tagFactory;
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

	private void generateTitle(HtmlDocument htmlDoc, DitaTagFactory dbf,
			ElementImpl parent) {

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

			parent.appendChild(dbf.createTitle(elem.getTextContent()));

		} else {

			Text text = (Text) XPathServices.getNode(htmlDoc, "//text()[1]");

			if (text != null) {
				parent.appendChild(dbf.createTitle(text.getData()));
			} else {
				parent.appendChild(dbf.createTitle("Herold"));
			}
		}
	}

	private String detectLanguage(Element documentElement) {

		String lang = documentElement.getAttributeNS(XmlConstants.NAMESPACE_XML, "lang");

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
