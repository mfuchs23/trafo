/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.tag.dita.DitaElement;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.P;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Row;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.Table;
import org.dbdoclet.tag.html.Td;
import org.dbdoclet.tag.html.Th;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditor;
import org.dbdoclet.trafo.html.dita.DocumentElementType;
import org.dbdoclet.trafo.internal.html.dita.LinkManager;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.TextImpl;

public abstract class DitaEditor implements IEditor {

	protected static final String FSEP = System.getProperty("file.separator");
	protected static final Log logger = LogFactory.getLog(DitaEditor.class);

	protected static final String AUTOMATICALLY_INSERTED = "Automatically inserted";
	private NodeImpl current;
	private NodeImpl parent;
	private HtmlElement child;
	private Object anything;
	private CharacterDataImpl characterDataNode;
	private boolean doIgnore;
	private boolean doTraverse;
	private LinkManager linkManager;
	private DocumentElementType documentElementType;
	private DitaTagFactory tagFactory;
	
	// protected DocBookTagFactory dbfactory = new DocBookTagFactory();
	protected Script script;
	
	public void copyCommonAttributes(HtmlElement html, DitaElement ditaElement) {

		logger.debug("Copy common attributes from " + html + " to " + ditaElement);

		if (html == null || ditaElement == null) {
			return;
		}

		String htmlId = html.getId();

		if (htmlId != null) {
			if (linkManager != null) {
				ditaElement.setId(linkManager.createUniqueId(htmlId));
			} else {
				logger.warn("Attribute linkManager must not be null! " + html + ", " + toString());
			}
		}

		if (script.isParameterOn(TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_CREATE_REMAP_ATTRIBUTE, false)) {

			createRemapAttribute(html, ditaElement);
			ditaElement.setLine(html.getLine());
			ditaElement.setColumn(html.getColumn());
		}

		ditaElement.setUserData("html", html, null);
	}
	
	protected void copyCommonAttributes(ElementImpl htmlElement, DocBookElement anchor) {
		// TODO Auto-generated method stub
		
	}
	
	private void createRemapAttribute(HtmlElement html, DitaElement dbk) {
		String remap = String.format("%s:%d:%d", html.getTagName(),
				html.getLine(), html.getColumn());
		dbk.setRemap(remap);
	}

	@Override
	public EditorInstruction edit(EditorInstruction vo) throws EditorException {

		if (vo == null) {
			throw new IllegalArgumentException("Variable vo is null!");
		}

		setValues(vo);
		DitaTagFactory tagFactory = (DitaTagFactory) getTagFactory();

		if (parent instanceof Row) {

			if ((child != null) && !(child instanceof Td)
					&& !(child instanceof Th) && !(child instanceof Table)) {

				P para = tagFactory.createP();
				// parent.appendChild(dbfactory.createEntry().appendChild(para));
				parent = para;
				current = parent;
			}

			if (characterDataNode != null
					&& characterDataNode instanceof TextImpl) {
			
				P para = tagFactory.createP();
				// parent.appendChild(dbfactory.createEntry().appendChild(para));
				parent = para;
				current = parent;
			}
		}

		return finalizeValues();
	}

	protected EditorInstruction finalizeValues() {

		EditorInstruction values = new EditorInstruction(script);

		values.doIgnore(doIgnore);
		values.doTraverse(doTraverse);
		values.setHtmlElement(child);
		values.setCurrent(current);
		values.setParent(parent);
		values.setCharacterDataNode(characterDataNode);

		return values;
	}

	public Object getAnything() {

		return anything;
	}

	public CharacterDataImpl getCharacterDataNode() {
		return characterDataNode;
	}

	public NodeImpl getCurrent() {
		return current;
	}

	public DocumentElementType getDocumentElementType() {
		return documentElementType;
	}

	public HtmlElement getHtmlElement() {

		return child;
	}

	public LinkManager getLinkManager() {
		return linkManager;
	}

	public NodeImpl getParent() {
		return parent;
	}

	// @Override
	public DitaTagFactory getTagFactory() {

		if (tagFactory == null) {
			tagFactory = new DitaTagFactory();
		}
		
		return tagFactory;
	}

	public boolean ignore() {
		return doIgnore;
	}

	public void ignore(boolean newDoIgnore) {

		this.doIgnore = newDoIgnore;
	}

	public void setAnything(Object newAnything) {

		this.anything = newAnything;
	}

	public void setChild(HtmlElement newChild) {
		this.child = newChild;
	}

	public void setCurrent(NodeImpl current) {
		this.current = current;
	}

	public void setLinkManager(LinkManager linkManager) {
		this.linkManager = linkManager;
	}

	public void setParent(NodeImpl newParent) {
		this.parent = newParent;
	}

	public void setTagFactory(DitaTagFactory tagFactory) {
		this.tagFactory = tagFactory;
	}

	public void setTextNode(TextImpl newTextNode) {

		this.characterDataNode = newTextNode;
	}

	public void setValues(EditorInstruction values) {

		script = values.getScript();
		
		if (script == null) {
			throw new IllegalStateException("Der Parameter Script darf nicht null sein!");
		}
		
		child = values.getHtmlElement();
		current = values.getCurrent();
		doIgnore = values.doIgnore();
		doTraverse = values.doTraverse();
		parent = values.getParent();
		characterDataNode = values.getCharacterDataNode();
	}

	@Override
	public String toString() {

		String buffer = "";

		buffer += ("editor[" + getClass().getName() + "]");

		return buffer;
	}

	protected void transferId(HtmlElement html, DocBookElement db) {

		if (html == null) {
			throw new IllegalArgumentException(
					"The argument html must not be null!");
		}

		if (db == null) {
			throw new IllegalArgumentException(
					"The argument db must not be null!");
		}

		String id = html.getId();

		if (id != null && id.length() > 0) {
			db.setId(id);
		}
	}

	public boolean traverse() {

		return doTraverse;
	}

	public void traverse(boolean newDoTraverse) {

		this.doTraverse = newDoTraverse;
	}

	protected String validateAlign(String align) {
		//return dbfactory.validateAlign(align);
		return "left";
	}

	protected String validateSrc(String src) {

		if (src == null) {
			throw new IllegalArgumentException("Parameter src is null!");
		}

		if (src.startsWith("http:") && (src.indexOf("?") > 0)) {
			src = src.substring(0, src.indexOf("?"));
		}

		return src;
	}

	protected boolean isList(NodeImpl parentNode) {
		
		if (parentNode instanceof DocBookElement) {
	
			DocBookElement parent = (DocBookElement) parentNode;
	
			if (parent.isList()) {
				return true;
			}
		}
		
		return false;
	}

	protected DitaElement getDitaElementParent() {

		NodeImpl parent = getParent();
		
		if (parent instanceof DitaElement) {
			return (DitaElement) parent;
		}
		
		return null;
	}

	protected boolean isSection(NodeImpl parentNode) {

		if (parentNode instanceof DocBookElement) {
	
			DocBookElement parent = (DocBookElement) parentNode;
	
			if (parent.isSection()) {
				return true;
			}
		}
		
		return false;
	}

	protected boolean isContentModel(NodeImpl parentNode) {

		if (parentNode instanceof DitaElement) {
	
			DitaElement parent = (DitaElement) parentNode;
	
			if (parent.isContentModel()) {
				return true;
			}
		}
		
		return false;
	}
}
