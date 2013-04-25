package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Subscript;
import org.dbdoclet.tag.docbook.Superscript;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;

public abstract class AbstractInlineEditor extends Editor {

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

		DocBookTagFactory dbfactory = values.getTagFactory();
		traverse(true);

		DocBookElement parent = getParent();

		if (parent.isContentModel()) {

			Para para = dbfactory.createPara();
			para.setFormatType(ElementImpl.FORMAT_INLINE);
			parent.appendChild(para);
			setParent(para);
			parent = para;
		}

		if (parent instanceof Subscript || parent instanceof Superscript) {

			NodeImpl ancestor = parent.getTrafoParentNode();

			if (ancestor != null) {

				ancestor.replaceChild(inlineElement, parent);
				inlineElement.appendChild(parent);
				setCurrent(parent);
				return finalizeValues();
			}
		}

		if (inlineElement.isValidParent(parent) == false) {

			Para candidate = dbfactory.createPara();
			candidate.setParentNode(parent);

			if (candidate.validate()) {

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
