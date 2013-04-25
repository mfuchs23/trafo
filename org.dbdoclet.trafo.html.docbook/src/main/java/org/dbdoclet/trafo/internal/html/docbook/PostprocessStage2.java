package org.dbdoclet.trafo.internal.html.docbook;

import java.util.ArrayList;

import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.SectionElement;
import org.dbdoclet.trafo.SectionNumberRemover;
import org.dbdoclet.trafo.SpaceNormalizer;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.AbstractNodeVisitor;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Node;

public class PostprocessStage2 extends AbstractNodeVisitor {

	private final DocBookTagFactory dbfactory;
	private final ArrayList<Node> removeList;
	private final Script script;

	public PostprocessStage2(DocBookTagFactory dbfactory, Script script,
			ArrayList<ProgressListener> listeners) {

		super(listeners);
		this.dbfactory = dbfactory;
		this.script = script;

		removeList = new ArrayList<Node>();
	}

	public void finish() {
		removeNodes(removeList);
	}

	@Override
	public void accept(Node node) throws Exception {

		fireProgressEvent(node.toString(), ProgressEvent.STAGE_ACTION);

		if (node instanceof ElementImpl) {

			NodeImpl elem = (NodeImpl) node;

			if (elem instanceof SectionElement) {

				SectionElement sect = (SectionElement) elem;
				NodeImpl title = sect.findChildElement("title");

				if (title != null) {

					SectionNumberRemover snr = new SectionNumberRemover();
					snr.setRegex(script.getTextParameter(
							DbtConstants.SECTION_SECTION_DETECTION,
							DbtConstants.PARAM_SECTION_NUMBERING_PATTERN, null));
					title.traverse(snr);

					if (script.isParameterOn(DbtConstants.SECTION_DOCBOOK,
							DbtConstants.PARAM_TITLE_NORMALIZE_SPACE,
							false)) {

						SpaceNormalizer sn = new SpaceNormalizer();
						title.traverse(sn);
					}
				}

				String titleText = sect.getTitle();

				if (titleText.length() == 0
						&& sect.hasContentChildren() == false) {
					removeList.add(sect);
					return;
				}

				if (titleText.length() > 0
						&& sect.hasContentChildren() == false) {
					sect.appendChild(dbfactory.createPara(""));
					return;
				}
			}
		}
	}

	@Override
	public void openTag(Node node) throws Exception {
		// Auto-generated method stub
	}

	@Override
	public void closeTag(Node node) throws Exception {
		// Auto-generated method stub
	}
}
