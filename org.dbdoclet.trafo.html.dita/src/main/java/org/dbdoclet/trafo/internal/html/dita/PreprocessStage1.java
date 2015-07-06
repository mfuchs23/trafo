package org.dbdoclet.trafo.internal.html.dita;

import java.util.ArrayList;

import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.xiphias.dom.AbstractNodeVisitor;
import org.w3c.dom.Node;

public class PreprocessStage1 extends AbstractNodeVisitor {

	private ArrayList<Node> removeList;

	public PreprocessStage1(ArrayList<ProgressListener> listeners) {
		super(listeners);
		removeList = new ArrayList<Node>();
	}

	@Override
	public void accept(Node node) {

		fireProgressEvent(node.toString(), ProgressEvent.STAGE_ACTION);
		
		if (node instanceof HtmlElement) {
			
			HtmlElement htmlElement = (HtmlElement) node;
			htmlElement.init();			
		}
	}

	public void finish() {
		removeNodes(removeList);
	}

	@Override
	public void openTag(Node node) throws Exception {
		//
	}

	@Override
	public void closeTag(Node node) throws Exception {
		//
	}
}
