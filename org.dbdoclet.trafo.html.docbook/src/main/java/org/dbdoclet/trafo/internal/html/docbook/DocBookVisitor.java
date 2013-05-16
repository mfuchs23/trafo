package org.dbdoclet.trafo.internal.html.docbook;

import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditorFactory;
import org.dbdoclet.trafo.html.IHtmlVisitor;
import org.dbdoclet.trafo.html.SectionDetector;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.w3c.dom.Node;

public class DocBookVisitor implements IHtmlVisitor {
	
	private ProgressListener listener;
	private DocBookTagFactory dbfactory;
	private Script script;

	public void addProgressListener(ProgressListener listener) {
		this.listener = listener;		
	}

	public void setTagFactory(DocBookTagFactory dbfactory) {
		this.dbfactory = dbfactory;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	@Override
	public void openTag(Node node) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Node node) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeTag(Node node) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ElementImpl getDocumentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEditorFactory getEditorFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean beforeEdit(EditorInstruction values) {
		
		listDetector.edit(values);

		SectionDetector sectionDetector = new SectionDetector();
		HtmlElement htmlElement = values.getHtmlElement();
		if (sectionDetector.isSection(htmlElement, getScript())) {
			sectionDetector.edit(values);
			return false;
		}

		return true;
		// TODO Auto-generated method stub
		
	}
}
