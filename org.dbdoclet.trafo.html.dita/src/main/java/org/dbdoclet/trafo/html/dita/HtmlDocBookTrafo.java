package org.dbdoclet.trafo.html.dita;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.jive.PanelProvider;
import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.progress.ProgressManager;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlDocument;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.AbstractTrafoService;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.html.HtmlProvider;
import org.dbdoclet.trafo.internal.html.dita.DocBookVisitor;
import org.dbdoclet.trafo.internal.html.dita.HtmlDocBookPanel;
import org.dbdoclet.trafo.internal.html.dita.PostprocessStage1;
import org.dbdoclet.trafo.internal.html.dita.PostprocessStage2;
import org.dbdoclet.trafo.internal.html.dita.PostprocessStage3;
import org.dbdoclet.trafo.internal.html.dita.PreprocessStage1;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.ScriptEvent;
import org.dbdoclet.trafo.script.ScriptEvent.Type;
import org.dbdoclet.trafo.script.ScriptListener;
import org.dbdoclet.xiphias.NodeSerializer;
import org.dbdoclet.xiphias.dom.DocumentImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeCountVisitor;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.osgi.service.component.ComponentContext;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

public class HtmlDocBookTrafo extends AbstractTrafoService implements
		PanelProvider, ScriptListener {

	private final Log logger = LogFactory.getLog(HtmlDocBookTrafo.class);
	private HtmlDocBookPanel htmlDocBookPanel;
	private DocBookTagFactory dbfactory = new DocBookTagFactory();
	private Script script;
	private InputStream in;
	private OutputStream out;
	private ArrayList<ProgressListener> listeners = new ArrayList<ProgressListener>();

	protected void activate(ComponentContext context) {
		logger.info("Activierung des Bundles " + getId());
	}

	@Override
	public String getId() {
		return "html2docbook";
	}

	@Override
	public JPanel getPanel() {

		htmlDocBookPanel = new HtmlDocBookPanel();
		return htmlDocBookPanel;
	}

	public Script getScript() {
		return script;
	}

	public DocBookTagFactory getTagFactory() {
		return dbfactory;
	}

	@Override
	public void scriptChanged(ScriptEvent<?> event) {

		if (event.getType() == Type.INPUT_FILE_CHANGED) {
			System.out.println("Input File Changed");
		}
	}

	@Override
	public void setInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public void setOutputStream(OutputStream out) {
		this.out = out;
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {

		if (dbfactory != null) {
			this.dbfactory = dbfactory;
		}
	}

	@Override
	public TrafoResult transform(Script script) {

		TrafoResult result = new TrafoResult();

		try {

			String encoding = script.getTextParameter(
					TrafoConstants.SECTION_HTML, TrafoConstants.PARAM_ENCODING,
					"UTF-8");

			if (htmlDocBookPanel != null) {

				String profileName = htmlDocBookPanel.getProfile();

				if (profileName != null && profileName.trim().length() > 0) {

					String profileText = ResourceServices.getResourceAsString(
							"profiles/" + profileName + ".her",
							HtmlDocBookTrafo.class.getClassLoader());

					if (profileText != null) {
						TrafoScriptManager mgr = new TrafoScriptManager();
						mgr.parseScript(script, profileText);
					}
				}

				script.selectSection(TrafoConstants.SECTION_HTML);
				script.setTextParameter(TrafoConstants.PARAM_ENCODING,
						htmlDocBookPanel.getSourceEncoding());

				script.selectSection(TrafoConstants.SECTION_DOCBOOK);
				script.setTextParameter(TrafoConstants.PARAM_LANGUAGE,
						htmlDocBookPanel.getLanguage());
				script.setTextParameter(TrafoConstants.PARAM_DOCUMENT_ELEMENT,
						htmlDocBookPanel.getDocumentType());
			}

			DocBookVisitor visitor = new DocBookVisitor();
			visitor.addProgressListeners(listeners);
			visitor.setTagFactory(dbfactory);
			visitor.setScript(script);

			HtmlProvider htmlProvider = new HtmlProvider(script);

			NodeImpl htmlDoc = null;
			ElementImpl documentElement = null;

			String htmlCode = retrieveHtmlCode(in, encoding);
			boolean isFragment = htmlProvider.isFragment(htmlCode);
			if (isFragment) {
				htmlDoc = htmlProvider.parseFragment(htmlCode);
			} else {
				htmlDoc = htmlProvider.parseDocument(htmlCode);
			}

			ProgressManager pm = new ProgressManager(listeners);
			pm.nextStage();
			NodeCountVisitor nodeCounter = new NodeCountVisitor(listeners);
			htmlDoc.traverse(nodeCounter);
			pm.setProgressMaximum(nodeCounter.getNumberOfNodes());
			pm.fireProgressEvent(new ProgressEvent("Preprocess HTML tree...",
					false));
			PreprocessStage1 preprocessStage1 = new PreprocessStage1(listeners);
			htmlDoc.traverse(preprocessStage1);
			preprocessStage1.finish();

			pm.nextStage();
			pm.fireProgressEvent(new ProgressEvent("Transformation...", false));
			pm.setProgressMaximum(nodeCounter.getNumberOfNodes());

			if (isFragment) {
				DocumentFragment fragment = htmlProvider.traverse(
						(HtmlFragment) htmlDoc, visitor);
				documentElement = (ElementImpl) fragment;
			} else {
				Document document = htmlProvider.traverse(
						(HtmlDocument) htmlDoc, visitor);
				documentElement = (ElementImpl) document.getDocumentElement();
			}

			pm.nextStage();
			nodeCounter = new NodeCountVisitor(listeners);
			pm.fireProgressEvent(new ProgressEvent("Postprocess stage 1...",
					false));
			documentElement.traverse(nodeCounter);
			pm.setProgressMaximum(nodeCounter.getNumberOfNodes());

			PostprocessStage1 postprocessStage1 = new PostprocessStage1(
					dbfactory, script, listeners);
			documentElement.traverse(postprocessStage1);
			postprocessStage1.finish();

			pm.nextStage();
			pm.fireProgressEvent(new ProgressEvent("Postprocess stage 2...",
					false));
			documentElement.traverse(nodeCounter.reset());
			pm.setProgressMaximum(nodeCounter.getNumberOfNodes());

			PostprocessStage2 postprocessStage2 = new PostprocessStage2(
					dbfactory, script, listeners);
			documentElement.traverse(postprocessStage2);
			postprocessStage2.finish();

			new PostprocessStage3(dbfactory, postprocessStage1.getSubtables())
					.process();

			boolean addIndex = script.isParameterOn(
					TrafoConstants.SECTION_DOCBOOK,
					TrafoConstants.PARAM_ADD_INDEX, false);

			if (addIndex == true) {
				documentElement.appendChild(dbfactory.createIndex());
			}

			encoding = script.getTextParameter(TrafoConstants.SECTION_DOCBOOK,
					TrafoConstants.PARAM_ENCODING, "UTF-8");

			if (isFragment) {
				result.setRootNode(documentElement);
			} else {
				DocumentImpl document = documentElement.getDocument();
				document.setXmlEncoding(encoding);
				result.setRootNode(document);
			}

			if (out != null) {

				NodeSerializer serializer = new NodeSerializer();
				serializer.addProgressListeners(listeners);
				OutputStreamWriter writer = new OutputStreamWriter(out,
						encoding);

				if (isFragment) {
					serializer.write(documentElement, writer);
				} else {
					DocumentImpl document = documentElement.getDocument();
					document.setXmlEncoding(encoding);
					serializer.write(document, writer);
				}

				writer.close();
			}

		} catch (Throwable oops) {
			result.setThrowable(oops);
		}

		return result;

	}

	private String retrieveHtmlCode(InputStream in, String encoding)
			throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream(); 

		byte[] buffer = new byte[4096];
		
		int n = in.read(buffer);
		while ( n != -1) {
			out.write(buffer, 0, n);
			n = in.read(buffer);
		}

		in.close();
		out.close();
		
		return new String(out.toByteArray(), encoding);
	}

	@Override
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
	public void removeProgressListener(ProgressListener listener) {

		if (listener == null) {
			return;
		}

		listeners.remove(listener);
	}
}
