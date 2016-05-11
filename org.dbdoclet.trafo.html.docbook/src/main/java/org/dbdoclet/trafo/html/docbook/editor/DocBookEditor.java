/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.ValidationResult;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookSchemaValidator;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.DocBookVersion;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Row;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.Table;
import org.dbdoclet.tag.html.Td;
import org.dbdoclet.tag.html.Th;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.IEditor;
import org.dbdoclet.trafo.html.docbook.DocumentElementType;
import org.dbdoclet.trafo.html.docbook.LinkManager;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.dbdoclet.xiphias.dom.TextImpl;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public abstract class DocBookEditor implements IEditor {

	protected static final String FSEP = System.getProperty("file.separator");
	protected static final Log logger = LogFactory.getLog(DocBookEditor.class);

	protected static final String AUTOMATICALLY_INSERTED = "Automatically inserted";
	private NodeImpl current;
	private NodeImpl parent;
	private HtmlElement child;
	private CharacterDataImpl characterDataNode;
	private boolean doIgnore;
	private boolean doTraverse;
	private LinkManager linkManager;
	private DocumentElementType documentElementType;
	private DocBookTagFactory tagFactory;
	protected Script script;

	protected void checkNode(Node node) {

		try {
			DocBookSchemaValidator validator = DocBookSchemaValidator
					.getInstance();
			ValidationResult result = validator.validate(
					script.getTransformPosition(), node);
			if (result.isValid() == false) {
				logger.debug(result.getMessage());
			}
		} catch (SAXException oops) {
			logger.fatal("Checking Node failed!", oops);
		}
	}

	public void copyCommonAttributes(HtmlElement html, DocBookElement dbk) {

		logger.debug("Copy common attributes from " + html + " to " + dbk);

		if (html == null || dbk == null) {
			return;
		}

		String htmlId = html.getId();

		if (htmlId != null) {
			if (linkManager != null) {
				dbk.setId(linkManager.createUniqueId(htmlId));
			} else {
				logger.warn("Attribute linkManager must not be null! " + html
						+ ", " + toString());
			}
		}

		if (script.isParameterOn(TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_CREATE_CONDITION_ATTRIBUTE, false)) {

			StringBuilder buffer = new StringBuilder();

			String htmlClass = html.getCssClass();

			if (htmlClass != null) {
				buffer.append(htmlClass);
				buffer.append(",");
			}

			String htmlTitle = html.getTitle();

			if (htmlTitle != null) {
				buffer.append(htmlTitle);
				buffer.append(",");
			}

			String condition = buffer.toString();

			if (condition.trim().length() > 0) {
				condition = StringServices.cutSuffix(condition, ",");
				dbk.setCondition(condition);
			}
		}

		if (script.isParameterOn(TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_CREATE_REMAP_ATTRIBUTE, false)) {

			createRemapAttribute(html, dbk);
			dbk.setLine(html.getLine());
			dbk.setColumn(html.getColumn());
		}

		dbk.setUserData("html", html, null);
	}

	private void createRemapAttribute(HtmlElement html, DocBookElement dbk) {
		String remap = String.format("%s:%d:%d", html.getTagName(),
				html.getLine(), html.getColumn());
		dbk.setRemap(remap);
	}

	public EditorInstruction edit(EditorInstruction vo) throws EditorException {

		if (vo == null) {
			throw new IllegalArgumentException("Variable vo is null!");
		}

		setValues(vo);
		BaseTagFactory dbfactory = getTagFactory();

		if (parent instanceof Row) {

			if ((child != null) && !(child instanceof Td)
					&& !(child instanceof Th) && !(child instanceof Table)) {

				Para para = dbfactory.createPara();
				parent.appendChild(dbfactory.createEntry().appendChild(para));
				parent = para;
				current = parent;
			}

			if (characterDataNode != null
					&& characterDataNode instanceof TextImpl) {

				Para para = dbfactory.createPara();
				parent.appendChild(dbfactory.createEntry().appendChild(para));
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

		checkNode(current);

		return values;
	}

	public CharacterDataImpl getCharacterDataNode() {
		return characterDataNode;
	}

	public NodeImpl getCurrent() {
		return current;
	}

	private DocBookVersion getDocBookVersion() {

		DocBookTagFactory dbfactory = getTagFactory();

		if (dbfactory == null) {
			throw new IllegalStateException(
					"The field dbfactory must not be null!");
		}

		DocBookVersion docBookVersion = dbfactory.getDocBookVersion();

		if (docBookVersion == null) {
			throw new IllegalStateException(
					"The field docBookVersion must not be null!");
		}

		return docBookVersion;
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

	public DocBookTagFactory getTagFactory() {

		if (tagFactory == null) {
			tagFactory = new DocBookTagFactory();
		}

		return tagFactory;
	}

	public boolean ignore() {
		return doIgnore;
	}

	public void ignore(boolean newDoIgnore) {

		this.doIgnore = newDoIgnore;
	}

	public boolean isDocBook5() {

		DocBookVersion docBookVersion = getDocBookVersion();

		if (docBookVersion == DocBookVersion.V5_0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDocBookVersion(DocBookVersion version) {

		if (version == null) {
			throw new IllegalArgumentException(
					"The argument version must not be null!");
		}

		DocBookVersion docBookVersion = getDocBookVersion();

		if (docBookVersion.equals(version)) {
			return true;
		} else {
			return false;
		}
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

	protected boolean isSection(NodeImpl parentNode) {

		if (parentNode instanceof DocBookElement) {

			DocBookElement parent = (DocBookElement) parentNode;

			if (parent.isSection()) {
				return true;
			}
		}

		return false;
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

	public void setTagFactory(DocBookTagFactory tagFactory) {
		this.tagFactory = tagFactory;
	}

	public void setTextNode(TextImpl newTextNode) {

		this.characterDataNode = newTextNode;
	}

	public void setValues(EditorInstruction values) {

		script = values.getScript();

		if (script == null) {
			throw new IllegalStateException(
					"Der Parameter Script darf nicht null sein!");
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
		return tagFactory.validateAlign(align);
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
}
