/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html;

import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.dom.CharacterDataImpl;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class EditorInstruction {

	private ElementImpl current;
	private ElementImpl parent;
	private HtmlElement htmlElement;
	private Object anything;
	private CharacterDataImpl characterDataNode;
	private boolean doIgnore = false;
	private boolean doTraverse = true;
	private Script script;

	public EditorInstruction(Script script) {
		this.script = script;
	}

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
		return htmlElement;
	}

	public ElementImpl getCurrent() {
		return current;
	}

	public ElementImpl getParent() {
		return parent;
	}

	public CharacterDataImpl getCharacterDataNode() {

		return characterDataNode;
	}

	public void setAnything(Object anything) {

		this.anything = anything;
	}

	public void setHtmlElement(HtmlElement htmlElement) {
		this.htmlElement = htmlElement;
	}

	public void setCurrent(ElementImpl current) {

		this.current = current;
	}

	public void setParent(ElementImpl parent) {

		this.parent = parent;
	}

	public void setCharacterDataNode(CharacterDataImpl characterDataNode) {

		this.characterDataNode = characterDataNode;
	}

	@Override
	public String toString() {

		String buffer = "";

		buffer += ("\nEditor values:\n" + "current.........: " + current + "\n"
				+ "parent..........: " + parent + "\n" + "child...........: "
				+ htmlElement + "\n" +"do ignore.......: " + doIgnore + "\n"
				+ "do traverse.....: " + doTraverse + "\n");

		return buffer;
	}

	public Script getScript() {
		return script;
	}
}
