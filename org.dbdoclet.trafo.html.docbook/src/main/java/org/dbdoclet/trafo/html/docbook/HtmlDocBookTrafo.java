package org.dbdoclet.trafo.html.docbook;

import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.jive.PanelProvider;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.AbstractTrafoService;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.internal.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.internal.html.docbook.HtmlDocBookPanel;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.ScriptEvent;
import org.dbdoclet.trafo.script.ScriptEvent.Type;
import org.dbdoclet.trafo.script.ScriptListener;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.osgi.service.component.ComponentContext;

public class HtmlDocBookTrafo extends AbstractTrafoService implements
		PanelProvider, ScriptListener {

	private final Log logger = LogFactory.getLog(HtmlDocBookTrafo.class);
	private HtmlDocBookPanel htmlDocBookPanel;
	private DocBookTagFactory dbfactory = new DocBookTagFactory();
	private InputStream in;

	private OutputStream out;

	protected void activate(ComponentContext context) {
		logger.info("Activierung des Bundles " + getId());
	}
	public DocBookTagFactory getTagFactory() {
		return dbfactory;
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

	@Override
	public void scriptChanged(ScriptEvent<?> event) {

		if (event.getType() == Type.INPUT_FILE_CHANGED) {
			System.out.println("Input File Changed");
		}
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {
		
		if (dbfactory != null) {
			this.dbfactory = dbfactory;
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

	public NodeImpl transform(Script script, String buffer, NodeImpl parent)
			throws Exception {
		
		DocBookTransformer trafo = new DocBookTransformer();
		trafo.setTagFactory(dbfactory);
		trafo.setScript(script);
		
		return trafo.transform(buffer, parent, null);
	}

	@Override
	public TrafoResult transform(Script script, ProgressListener listener) {

		TrafoResult result = new TrafoResult();

		try {

			DocBookTransformer trafo = new DocBookTransformer();
			trafo.addProgressListener(listener);
			trafo.setTagFactory(dbfactory);

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

				script.selectSection(DbtConstants.SECTION_HTML);
				script.setTextParameter(DbtConstants.PARAM_HTML_SOURCE_ENCODING,
						htmlDocBookPanel.getSourceEncoding());

				script.selectSection(DbtConstants.SECTION_DOCBOOK);
				script.setTextParameter(DbtConstants.PARAM_LANGUAGE,
						htmlDocBookPanel.getLanguage());
				script.setTextParameter(
						DbtConstants.PARAM_DOCUMENT_ELEMENT,
						htmlDocBookPanel.getDocumentType());
			}

			trafo.setScript(script);
			trafo.transform(in, out);

		} catch (Throwable oops) {
			result.setThrowable(oops);
		}

		return result;

	}
}
