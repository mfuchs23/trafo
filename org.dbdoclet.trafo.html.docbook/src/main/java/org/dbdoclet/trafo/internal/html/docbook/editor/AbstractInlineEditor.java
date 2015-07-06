package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Subscript;
import org.dbdoclet.tag.docbook.Superscript;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public abstract class AbstractInlineEditor extends DocBookEditor {

	private DocBookElement inlineElement;

	public DocBookElement getInlineElement() {
		return inlineElement;
	}

	public void setInlineElement(DocBookElement inlineElement) {
		this.inlineElement = inlineElement;
	}

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		setCurrent(inlineElement);
		copyCommonAttributes(getHtmlElement(), inlineElement);

		BaseTagFactory dbfactory = getTagFactory();
		traverse(true);

		NodeImpl parent = getParent();

		if (parent instanceof Subscript || parent instanceof Superscript) {

			NodeImpl ancestor = parent.getTrafoParentNode();

			if (ancestor != null) {

				ancestor.replaceChild(inlineElement, parent);
				inlineElement.appendChild(parent);
				setCurrent(parent);
				return finalizeValues();
			}
		}

		if (inlineElement.isValidParent(script.getTransformPosition(), parent) == false) {

			Para candidate = dbfactory.createPara();

			if (candidate.isValidParent(script.getTransformPosition(), parent)) {

				setParent(candidate);
				parent.appendChild(candidate);
				candidate.appendChild(getCurrent());
			}

		} else {

			getCurrent().setParentNode(parent);
			parent.appendChild(getCurrent());
		}

		return finalizeValues();
	}

}
