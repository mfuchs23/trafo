/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.docbook.DocumentElementType;
import org.dbdoclet.trafo.internal.html.docbook.DocBookTransformer;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;

public class EditorInstruction {

	private DocBookElement current;
	private DocBookElement parent;
	private HtmlElement child;
	private Object anything;
	private DocBookTransformer transformer;
	private CharacterDataImpl characterDataNode;
	private boolean doIgnore = false;
	private boolean doTraverse = true;
	private DocumentElementType codeContext;

	public boolean doIgnore() {

		return doIgnore;
	}

	public void doIgnore(boolean ignore) {

		this.doIgnore = ignore;
	}

	public boolean doTraverse() {

		return doTraverse;
	}

	public void doTraverse(boolean traverse) {

		this.doTraverse = traverse;
	}

	public Object getAnything() {

		return anything;
	}

	public HtmlElement getHtmlElement() {

		return child;
	}

	public DocumentElementType getCodeContext() {
		return codeContext;
	}

	public DocBookElement getCurrent() {

		return current;
	}

	public DocBookElement getParent() {

		return parent;
	}

	public DocBookTagFactory getTagFactory() {

		return transformer.getTagFactory();
	}

	public CharacterDataImpl getCharacterDataNode() {

		return characterDataNode;
	}

	public DocBookTransformer getTransformer() {
		return transformer;
	}

	public void setAnything(Object anything) {

		this.anything = anything;
	}

	public void setChild(HtmlElement child) {

		this.child = child;
	}

	public void setCodeContext(DocumentElementType context) {

		this.codeContext = context;
	}

	public void setCurrent(DocBookElement current) {

		this.current = current;
	}

	public void setParent(DocBookElement parent) {

		this.parent = parent;
	}

	public void setCharacterDataNode(CharacterDataImpl characterDataNode) {

		this.characterDataNode = characterDataNode;
	}

	public void setTransformer(DocBookTransformer transformer) {
		this.transformer = transformer;
	}

	@Override
	public String toString() {

		String buffer = "";

		buffer += ("\nEditor values:\n" + "current.........: " + current + "\n"
				+ "parent..........: " + parent + "\n" + "child...........: "
				+ child + "\n" + "code context....: " + codeContext + "\n"
				+ "do ignore.......: " + doIgnore + "\n" + "do traverse.....: "
				+ doTraverse + "\n");

		return buffer;
	}
}
