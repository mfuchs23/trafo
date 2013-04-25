/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.AttributeAlign;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.DocBookVersion;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Row;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.Table;
import org.dbdoclet.tag.html.Td;
import org.dbdoclet.tag.html.Th;
import org.dbdoclet.trafo.html.docbook.DocBookTransformer;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;
import org.dbdoclet.xiphias.dom.TextImpl;

public class Editor {

	protected static final String FSEP = System.getProperty("file.separator");
	protected static final Log logger = LogFactory.getLog(Editor.class);
	protected static final String AUTOMATICALLY_INSERTED = "Automatically inserted";

	private DocBookElement current;
	private DocBookElement parent;
	private HtmlElement child;
	private Object anything;
	private DocBookTransformer transformer;
	private CharacterDataImpl characterDataNode;
	private boolean doIgnore;
	private boolean doTraverse;
	private DocBookTransformer.DocumentElementType codeContext;

	public void copyCommonAttributes(HtmlElement html, DocBookElement dbk) {

		logger.debug("Copy common attributes from " + html + " to " + dbk);

		if (html == null || dbk == null) {
			return;
		}

		String htmlId = html.getId();

		if (htmlId != null) {
			dbk.setId(transformer.getLinkManager().createUniqueId(htmlId));
		}

		Script script = getScript();

		if (script.isParameterOn(DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_CREATE_CONDITION_ATTRIBUTE, false)) {

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

		if (script.isParameterOn(DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_CREATE_REMAP_ATTRIBUTE, false)) {

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
		DocBookTagFactory dbfactory = transformer.getTagFactory();

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

	public Object getAnything() {

		return anything;
	}

	public CharacterDataImpl getCharacterDataNode() {
		return characterDataNode;
	}

	public DocBookTransformer.DocumentElementType getCodeContext() {
		return codeContext;
	}

	public DocBookElement getCurrent() {

		return current;
	}

	public HtmlElement getHtmlElement() {

		return child;
	}

	public DocBookElement getParent() {
		return parent;
	}

	public DocBookTagFactory getTagFactory() {

		if (transformer == null) {
			throw new IllegalStateException(
					"The field \"transformer\" must not be null!");
		}

		return transformer.getTagFactory();
	}

	public Script getScript() {
		return transformer.getScript();
	}

	public DocBookTransformer getTransformer() {
		return transformer;
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

	public void setAnything(Object newAnything) {

		this.anything = newAnything;
	}

	public void setChild(HtmlElement newChild) {

		this.child = newChild;
	}

	public void setCodeContext(
			DocBookTransformer.DocumentElementType newCodeContext) {

		this.codeContext = newCodeContext;
	}

	public void setCurrent(DocBookElement newCurrent) {
		this.current = newCurrent;
	}

	public void setParent(DocBookElement newParent) {
		this.parent = newParent;
	}

	public void setTextNode(TextImpl newTextNode) {

		this.characterDataNode = newTextNode;
	}

	public void setTransformer(DocBookTransformer transformer) {
		this.transformer = transformer;
	}

	@Override
	public String toString() {

		String buffer = "";

		buffer += ("editor[" + getClass().getName() + "]");

		return buffer;
	}

	public boolean traverse() {

		return doTraverse;
	}

	public void traverse(boolean newDoTraverse) {

		this.doTraverse = newDoTraverse;
	}

	private DocBookVersion getDocBookVersion() {

		if (transformer == null) {
			throw new IllegalStateException(
					"The field transformer must not be null!");
		}

		DocBookTagFactory dbfactory = transformer.getTagFactory();

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

	protected EditorInstruction finalizeValues() {

		EditorInstruction values = new EditorInstruction();

		values.doIgnore(doIgnore);
		values.doTraverse(doTraverse);
		values.setAnything(anything);
		values.setChild(child);
		values.setCodeContext(codeContext);
		values.setCurrent(current);
		values.setParent(parent);
		values.setCharacterDataNode(characterDataNode);
		values.setTransformer(transformer);

		return values;
	}

	public void setValues(EditorInstruction values) {

		anything = values.getAnything();
		child = values.getHtmlElement();
		codeContext = values.getCodeContext();
		current = values.getCurrent();
		doIgnore = values.doIgnore();
		doTraverse = values.doTraverse();
		parent = values.getParent();
		characterDataNode = values.getCharacterDataNode();
		transformer = values.getTransformer();
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

	protected String validateAlign(String align) {

		if (align == null) {
			return align;
		}

		align = align.toLowerCase();

		try {
			AttributeAlign.valueOf(align.toUpperCase());
		} catch (IllegalArgumentException oops) {
			return AttributeAlign.CENTER.toString().toLowerCase();
		}

		return align;
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
