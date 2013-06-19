package org.dbdoclet.trafo.internal.html.docbook;

import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditorFactory;
import org.dbdoclet.trafo.html.IHtmlVisitor;
import org.dbdoclet.trafo.html.docbook.DbtConstants;
import org.dbdoclet.trafo.html.docbook.DocumentElementType;
import org.dbdoclet.trafo.html.docbook.ListDetector;
import org.dbdoclet.trafo.html.docbook.SectionDetector;
import org.dbdoclet.trafo.internal.html.docbook.editor.DocBookEditorFactory;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class DocBookVisitor implements IHtmlVisitor {

	private ProgressListener listener;
	private LinkManager linkManager;
	private DocBookTagFactory dbfactory;
	private Script script;
	private ListDetector listDetector;
	private ElementImpl documentElement;
	private DocBookEditorFactory docBookEditorFactory;

	public DocBookVisitor() {
		listDetector = new ListDetector();
		linkManager = new LinkManager();
	}

	public void addProgressListener(ProgressListener listener) {
		this.listener = listener;
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
	public ElementImpl getDocumentElement() {

		if (documentElement == null) {

			DocumentElementType documentType = DocumentElementType
					.valueOf("BOOK");

			if (script != null) {

				String value = script.getTextParameter(
						DbtConstants.SECTION_DOCBOOK,
						DbtConstants.PARAM_DOCUMENT_ELEMENT, "book");

				documentType = DocumentElementType.valueOf(value.toUpperCase());
			}

			switch (documentType) {
			case ARTICLE:
				documentElement = dbfactory.createArticle();
				break;
			case BOOK:
				documentElement = dbfactory.createBook();
				break;
			case CHAPTER:
				documentElement = dbfactory.createChapter();
				break;
			case REFERENCE:
				documentElement = dbfactory.createReference();
				break;
			case OVERVIEW:
				documentElement = dbfactory.createSection();
				break;
			case PARAGRAPH:
				documentElement = dbfactory.createPara();
				break;
			case PART:
				documentElement = dbfactory.createPart();
				break;
			case SECTION:
				documentElement = dbfactory.createSection();
				break;
			default:
				documentElement = dbfactory.createBook();

			}
			
			documentElement.setNamespaceURI(DocBookElement.DOCBOOK_NAMESPACE);
			documentElement.setAttribute("xmlns", DocBookElement.DOCBOOK_NAMESPACE);
			documentElement.setAttribute("xmlns:xl", DocBookElement.XLINK_NAMESPACE);
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
}
